package edu.princeton.cs.exercise.chapter2_2;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.2.21 Triplicates . Given three lists of N names each, devise a linearithmic algorithm to
 * determine if there is any name common to all three lists, and if so, return the first such name.
 *
 * <p>三个数组. 给定3个列表每个有N个名字,设计一个线性对数级的算法来判断是否有任意的名字是三个列表中相同的,如果有<br>
 * 返回第一个这样的名字
 *
 * @author LeonChen
 * @since 7/15/20
 */
class E02_02_21 {

  /**
   * 先对3份列表进行归并排序 O(nlogn), 然后遍历一个列表用二分法查找另外两个列表中是否有相同的名字 O(nlogn)
   *
   * @param args
   */
  public static void main(String[] args) {
    Comparable[] a1 = new Comparable[] {"leon", "david", "bill", "leo"};
    Comparable[] a2 = new Comparable[] {"anne", "jack", "leo", "golf"};
    Comparable[] a3 = new Comparable[] {"ten", "judy", "leo", "zord"};

    Merge.sort(a1);
    Merge.sort(a2);
    Merge.sort(a3);

    boolean haveCommon = false;
    for (Comparable c1 : a1) {

      for (Comparable c2 : a2) {
        if (c2.compareTo(c1) == 0) {
          for (Comparable c3 : a3) {
            if (c3.compareTo(c2) == 0) {
              haveCommon = true;
              StdOut.println("the one of common name is " + c3);
            } else if (c3.compareTo(c2) > 0) {
              continue;
            }
          }
        } else if (c2.compareTo(c1) > 0) {
          continue;
        }
      }
    }

    if (!haveCommon) {
      StdOut.println("there is not common name ");
    }
  }
}
