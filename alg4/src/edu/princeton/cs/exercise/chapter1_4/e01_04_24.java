package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.24 Throwing eggs from a building. Suppose that you have an N-story building and plenty of
 * eggs. Suppose also that an egg is broken if it is thrown off floor F or higher, and unhurt
 * otherwise. First, devise a strategy to determine the value of F such that the number of broken
 * eggs is ~lg N when using ~lg N throws, then find a way to reduce the cost to ~2lgF.
 *
 * <p>从一个建筑扔鸡蛋.假设你有一个 N层的建筑和很多的鸡蛋.同时假设一个鸡蛋从 F 或者更高的楼层上丢下会碎,<br>
 * 而在其他层则不会.首先,设计一种策略来推断出 F 的值,可以扔 ~lgN 次鸡蛋和损坏 ~lgN个鸡蛋.然后找到一种方式<br>
 * 将这个消耗降低到 ~2lgF
 *
 * @author LeonChen
 * @since 3/20/20
 */
class e01_04_24 {

  /**
   * 首先我们可以使用 二分法 找到这个F.mid 碎的话证明F在 mid 及以下,hi设为 mid, mid 不碎的时候,证明F在mid+1及以上<br>
   * lo设为mid+1,直到找到临界点 lo=hi 的时候; 这时的hi就是F.<br>
   * 然后我们考虑将消耗降低到 ~2lgF. 同样考虑2的幂次寻找, 2^0,2^1,2^2,2^3.... . 直到找到会碎的那层,或者到了上界<br>
   * 然后在这次的层数和上个层数中间再进行寻找.直到找到临界点.
   *
   * @param args
   */
  public static void main(String[] args) {
    // 我们使用false代表没碎,true代表碎了
    boolean[] floors =
        new boolean[] {false, false, false, false, false, false, false, true, true, true, true};

    throwEggsLgN(floors);

    throwEggs2LgF(floors);
  }

  private static void throwEggs2LgF(boolean[] floors) {
    int power = 0;
    int lo = 0;
    int hi = floors.length - 1;
    int index = 0;
    int oldInd = 0;

    while (lo < hi) {
      oldInd = index;
      index = (int) (lo + Math.pow(2, power));
      if (floors[index]) {
        hi = index;
        lo = oldInd + 1;
        power = 0;
      } else {
        power++;
      }
    }

    if (floors[hi]) {
      StdOut.println("F is " + hi);
    }
  }

  private static void throwEggsLgN(boolean[] floors) {

    int lo = 0;
    int hi = floors.length - 1;
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      if (floors[mid]) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }

    if (floors[hi]) {
      StdOut.println("F is " + hi);
    }
  }
}
