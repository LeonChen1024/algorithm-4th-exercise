package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.25 Throwing two eggs from a building. Consider the previous question, but now suppose you
 * only have two eggs, and your cost model is the number of throws. Devise a strategy to determine F
 * such that the number of throws is at most 2√N, then find a way to reduce the cost to ~c√F. This
 * is analogous to a situation where search hits (egg intact) are much cheaper than misses (egg
 * broken).
 *
 * <p>从一个建筑扔两个蛋.思考前一个问题,但是现在假设你只有两个鸡蛋,并且你的消耗模型是你扔的次数.设计一个策略来<br>
 * 求出F使得扔的次数最多是 2√N, 然后找到一种方式将消耗降低到 ~c√F.这和查找命中(蛋没碎)比未命中(蛋碎了)的成本<br>
 * 小得多的情形类似
 *
 * @author LeonChen
 * @since 3/20/20
 */
class e01_04_25 {

  /**
   * 首先,我们可以将楼分成 √N 组,每组有 √N 层,从下往上在每组的最顶层扔鸡蛋,碎了即在该组内一层层往上试验,最多 √N 层.<br>
   * 时间复杂度就是 2√N<br>
   * 然后我们可以考虑使用 ~c√F 的方法.一样也是要用平方的方式.我们可以从 1^2,2^2,3^2....√F^2 即可找到这个 F.<br>
   * 由于从 1 到√F 是 √F 次扔鸡蛋.而 (√F -1)^2 到 √F^2 的中间要扔 F- (F-2√F+1) = 2√F+1 次<br>
   * 所以总的是 3√F +1 ~ c√F
   *
   * @param args
   */
  public static void main(String[] args) {
    // 我们使用false代表没碎,true代表碎了
    boolean[] floors =
        new boolean[] {false, false, false, false, false, false, false, true, true, true, true};

    //    boolean[] floors =
    //        new boolean[] {false, false, false, false, false, false, false, false, false, false,
    // false};
    throwEggsSqrN(floors);
    throwEggsSqrF(floors);
  }

  private static void throwEggsSqrF(boolean[] floors) {
    int n = 1;
    int groupTop = (int) Math.pow(n, 2);

    while (!floors[groupTop]) {
      // 还没摔碎,检验是否还有下一组,并且是否会超过顶层.
      if (n > (int) Math.sqrt(floors.length - 1)) {
        // 已经超过最高的一组,都没碎,那么就是全 false
        StdOut.println("there is not F");
        return;
      }
      n++;
      if (Math.pow(n, 2) > (floors.length - 1)) {
        groupTop = floors.length - 1;
      } else {
        groupTop = (int) Math.pow(n, 2);
      }
    }
    // 摔碎了一个,接下来遍历这个组的每一层
    for (int i = (int) Math.pow(n - 1, 2); i < groupTop; i++) {
      if (floors[i]) {
        StdOut.println("F is index " + i);
        return;
      }
    }
  }

  private static void throwEggsSqrN(boolean[] floors) {

    int floorGroup = 1;
    int floorGroupNums = (int) Math.sqrt(floors.length - 1);
    int groupTop = floorGroup * floorGroupNums;

    while (!floors[groupTop]) {
      // 还没摔碎,检验是否还有下一组,并且是否会超过顶层.
      if (floorGroup > floorGroupNums) {
        // 已经超过最高的一组,都没碎,那么就是全 false
        StdOut.println("there is not F");
        return;
      }
      floorGroup++;

      if (floorGroup * floorGroupNums >= floors.length - 1) {
        groupTop = floors.length - 1;
      } else {
        groupTop = floorGroup * floorGroupNums;
      }
    }

    // 摔碎了一个,接下来遍历这个组的每一层
    for (int i = (floorGroup - 1) * floorGroupNums; i < groupTop; i++) {
      if (floors[i]) {
        StdOut.println("F is index " + i);
        return;
      }
    }
  }
}
