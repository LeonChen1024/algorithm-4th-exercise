package edu.princeton.cs.exercise.chapter2_4;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.4.31 Fast insert. Develop a compare-based implementation of the MinPQ API such that insert uses
 * ~ log log N compares and delete the minimum uses ~2 log N compares. Hint : Use binary search on
 * parent pointers to find the ancestor in swim().
 *
 * <p>快速插入.开发一个基于对比实现的 MinPQ API 使得插入使用 ~loglogN 的对比,并且删除最小值使用 ~2logN 的对比.<br>
 * 提示:在swim()中对父节点使用二分搜索来找到先祖节点
 *
 * @author LeonChen
 * @since 11/7/20
 */
class E02_04_31 {

  /**
   * 首先swim() 的上浮路径是可以确定的, 每个父节点都是子元素索引k的 k/2 ,所以可以直接将路径的节点都计算出来<br>
   * 再使用二分法来找到合适的节点,但是不能直接交换,应该要将这个节点链路的每一层都向下沉,然后再把上浮的节点放到<br>
   * 空出的位置上. 原本的对比次数就是 logN 二分法再一缩减,最后就是 loglogN . 但是删除最小值需要下沉,下沉的<br>
   * 时候就没办法使用二分法了,因为每个节点下沉都会有两个路径可选.依然是 ~2logN.
   *
   * @param args
   */
  public static void main(String[] args) {
    BiMinPQ<Integer> pq = new BiMinPQ<Integer>();
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      if (!item.equals("-")) pq.insert(Integer.valueOf(item));
      else if (!pq.isEmpty()) StdOut.print(pq.delMin() + " ");
    }
    StdOut.println("(" + pq.size() + " left on pq)");
  }

  public static class BiMinPQ<Key> extends MinPQ<Key> {

    @Override
    protected void swim(int k) {

      int index = k;

      List<Integer> ps = new ArrayList<>();
      while (index >= 1) {
        ps.add(index);
        index /= 2;
      }

      int lo = 0;

      int hi = ps.size() - 1;
      int mid = 0;
      while (lo <= hi) {
        mid = lo + (hi - lo) / 2;
        if (greater(k, ps.get(mid))) {
          hi = mid - 1;
        } else {
          lo = mid + 1;
        }
      }
      for (int i = 1; i < lo; i++) {
        exch(ps.get(i - 1), ps.get(i));
      }
    }
  }
}
