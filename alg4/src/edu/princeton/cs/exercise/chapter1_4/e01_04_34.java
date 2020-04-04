package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.34 Hot or cold. Your goal is to guess a secret integer between 1 and N.You repeatedly guess
 * integers between 1 and N. After each guess you learn if your guess equals the secret integer (and
 * the game stops). Otherwise, you learn if the guess is hotter (closer to) or colder (farther from)
 * the secret number than your previous guess. Design an algorithm that finds the secret number in
 * at most ~2lgN guesses. Then design an algorithm that finds the secret number in at most ~ 1lgN
 * guesses.
 *
 * <p>热还是冷. 你的目标是猜出一个1到N之间的秘密整数.你重复的在数字1到N之间猜数字.在每一次你猜完之后你会知道你是否<br>
 * 猜中了这个秘密数字(是则游戏结束). 否则,你将会知道你的猜测相对于上次猜测距离秘密数字是更热(更接近)或者更冷(更远).<br>
 * 设计一个算法在最多 ~2lgN 次猜测中找到这个秘密数字.然后设计一个算法在最多 ~1lgN 次猜测中找到这个秘密数字.
 *
 * @author LeonChen
 * @since 3/27/20
 */
class e01_04_34 {

  /**
   * 首先,我们可以使用二分法,但是由于题目中只会告知这次的猜测与上次的猜测相比更热还是更冷,所以我们需要做一些变形<br>
   * 每一次猜测的时候我们都要根据和上一次的冷热对比来确定当前查找方向是否正确,(可以假设默认朝右边猜测)如果不正确,<br>
   * 需要及时调整. 因为距离只能是绝对值,所以可能在调整方向的时候实际上也是错误的,最差的情况下是每次调整完方向<br>
   * 判断依然出现了错误,时间复杂度大约是 ~2lgN<br>
   * 然后我们可以考虑如何优化他,上面的方式通过不断的二分对比来解决,但是他每次的二分都不是一个确定的方向,我们可以<br>
   * 考虑这样来得到一个肯定的方向,比如,二分的时候,我们得到 [lo,mid] 和 [mid,hi] 此时,我们将这两个区间再次二分<br>
   * 得到两边的中间点 lom 和 him,此时判断这两个中点的热度,谁更热,目标就在哪个区间.一次类推,由于得到的中点可以复用<br>
   * 复杂度是lgN
   *
   * @param args
   */
  public static void main(String[] args) {

    int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    int target = 15;
    guessNum2lgN(arr, target);
    guessNumlgN(arr, target);
  }

  private static void guessNumlgN(int[] arr, int target) {
    int start = 0;
    int end = arr.length - 1;

    while (start <= end) {
      int mid = start + (end - start) / 2;
      int guess1 = start + (mid - start) / 2;
      int guess2 = mid + (end - mid) / 2;

      int distan1 = Math.abs(guess1 - target);
      int distan2 = Math.abs(guess2 - target);
      if (distan1 < distan2) {
        end = mid;
      } else if (distan1 > distan2) {
        start = mid;
      } else {
        mid = guess1 + distan1 - 1;
        StdOut.println("the secret number is " + arr[mid]);
        return;
      }
    }
  }

  private static void guessNum2lgN(int[] arr, int target) {
    int lastStart = 0;
    int lastEnd = arr.length - 1;
    int start = 0;
    int end = arr.length - 1;
    int lastDistance = Integer.MAX_VALUE;

    while (start <= end) {
      int mid = start + (end - start) / 2;
      int distance = Math.abs(arr[mid] - target);
      if (arr[mid] == target) {
        StdOut.println("the secret number is " + arr[mid]);
        return;
      } else if (distance > lastDistance) {
        // 更冷,调整方向.
        start = lastStart;
        end = lastStart + (lastEnd - lastStart) / 2;
        lastDistance = distance;
      } else {
        // 方向正确
        lastStart = start;
        lastEnd = end;

        start = mid + 1;
        lastDistance = distance;
      }
    }
  }
}
