package edu.princeton.cs.exercise.chapter1_4;

/**
 * 1.4.6 Give the order of growth (as a function of N ) of the running times of each of the
 * following code fragments:<br>
 * a. <code>
 *  int sum=0;
 *  for(int n=N;n>0;n/=2)
 *    for(int i=0;i<n;i++)
 *      sum++;
 * </code><br>
 * b. <code>
 *     int sum=0;
 *     for(int i=1; i<N;i*=2)
 *       for(int j=0;j<i;j++)
 *         sum++;
 * </code><br>
 * c. <code>
 *     int sum=0;
 *     for(int i=1;i<N;i*=2)
 *       for(int j=0;j<N;j++)
 *         sum++;
 * </code>
 *
 * @author LeonChen
 * @since 3/4/20
 */
class e01_04_06 {

  /**
   * a. O(N) . for(int n=N;n>0;n/=2) is log_2^N loop , for(int i=0;i<n;i++) is n loop, 最大位数就是N.<br>
   * b. O(N) . 和a类似.<br>
   * c. O(N log(N)) . 因为N不变,所以每次循环都回有 N次 sum++;
   *
   * @param args
   */
  public static void main(String[] args) {}
}
