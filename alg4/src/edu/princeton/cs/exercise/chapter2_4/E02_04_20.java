package edu.princeton.cs.exercise.chapter2_4;

/**
 * 2.4.20 Prove that sink-based heap construction uses fewer than 2N compares and fewer than N
 * exchanges.
 *
 * <p>证明基于下沉的堆构造使用了小于2N次对比和小于N次交换
 *
 * @author LeonChen
 * @since 9/24/20
 */
class E02_04_20 {

  /**
   * 首先由于下沉的特性叶子节点不需要对比.使得只需要从 N/2 的节点开始操作即可.而每次操作最多需要先对比两个子节点<br>
   * 的最大值,然后和自己对比,最多两次对比,一次交换.假设最底层节点的高度是0,那么目标节点的交换次数最多为自身高度<br>
   * 也就是说每一层的节点交换次数最多为 该层节点数*该层高度.可得树高度 H = log(N+1)-1.每层的节点数量最多为<br>
   * 2^h. 由于底层不用操作, 所以交换次数最多为 <code>
   *     h+2(h−1)+22(h−2)+⋯+2h(0) = (k=0...h-1)2^k(h-k) = h(k=0...h-1)2^k - (k=0...h-1)k*2^k
   *     第一项是等比数列求和,第二项通过 + 一个这个项 再 - 两个这个项 利用错位相减得到结果
   *     可得 h2^h - h - (k=0...h-1)k*2^k =  h2^h - h + (k=0...h-1)k*2^k - 2(k=0...h-1)k*2^k
   *     = h2^h - h + 2^h -2 - (h-1)2^h = 2^(h+1) -h -2 = N-h-1 < N
   * </code>因为交换次数 <N 所以对比次数 <2N
   *
   * <p>也可以参考官方 https://algs4.cs.princeton.edu/24pq/
   */
  public static void main(String[] args) {}
}
