package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.exercise.chapter2_1.E02_01_35;
import edu.princeton.cs.exercise.chapter2_1.E02_01_36;

/**
 * 2.3.30 Corner cases. Test quicksort on large nonrandom arrays of the kind described in Exercises
 * 2.1.35 and 2.1.36 both with and without the initial random shuffle. How does shuffling affect its
 * performance for these arrays?
 *
 * <p>边界情况。使用一个如练习 2.1.35 和 2.1.36 中描述的巨大的非随机数组中使用或者不使用初始打乱的快排.<br>
 * 对于这些数组来说打乱会怎么影响性能.
 *
 * @author LeonChen
 * @since 8/28/20
 */
class E02_03_30 {

  private static final String DISCRETE_ARR = "DISCRETE_ARR";
  private static final String GAUSSIAN_ARR = "GAUSSIAN_ARR";
  private static final String GEOMETRIC_ARR = "GEOMETRIC_ARR";
  private static final String POISSON_ARR = "POISSON_ARR";
  private static final String ARR_TYPE_RANDOM = "ARR_TYPE_RANDOM";
  private static final String ARR_TYPE_H0H1 = "ARR_TYPE_H0H1";
  private static final String ARR_TYPE_RECURSIVE_HALF = "ARR_TYPE_RECURSIVE_HALF";
  private static final String ARR_TYPE_H0HR = "ARR_TYPE_H0HR";
  /**
   * <code>
   * arr type is DISCRETE_ARR , n is 1000, with shuffling is 0.002 , without time is 0.0
   * arr type is DISCRETE_ARR , n is 2000, with shuffling is 0.0 , without time is 0.0
   * arr type is DISCRETE_ARR , n is 4000, with shuffling is 0.001 , without time is 0.001
   * arr type is DISCRETE_ARR , n is 8000, with shuffling is 0.002 , without time is 0.0
   * arr type is DISCRETE_ARR , n is 16000, with shuffling is 0.002 , without time is 0.001
   * arr type is DISCRETE_ARR , n is 32000, with shuffling is 0.004 , without time is 0.002
   * arr type is DISCRETE_ARR , n is 64000, with shuffling is 0.005 , without time is 0.003
   * arr type is DISCRETE_ARR , n is 128000, with shuffling is 0.012 , without time is 0.007
   * arr type is DISCRETE_ARR , n is 256000, with shuffling is 0.043 , without time is 0.014
   * arr type is DISCRETE_ARR , n is 512000, with shuffling is 0.047 , without time is 0.044
   * arr type is GAUSSIAN_ARR , n is 1000, with shuffling is 0.003 , without time is 0.001
   * arr type is GAUSSIAN_ARR , n is 2000, with shuffling is 0.004 , without time is 0.002
   * arr type is GAUSSIAN_ARR , n is 4000, with shuffling is 0.005 , without time is 0.003
   * arr type is GAUSSIAN_ARR , n is 8000, with shuffling is 0.008 , without time is 0.008
   * arr type is GAUSSIAN_ARR , n is 16000, with shuffling is 0.022 , without time is 0.015
   * arr type is GAUSSIAN_ARR , n is 32000, with shuffling is 0.018 , without time is 0.005
   * arr type is GAUSSIAN_ARR , n is 64000, with shuffling is 0.039 , without time is 0.025
   * arr type is GAUSSIAN_ARR , n is 128000, with shuffling is 0.082 , without time is 0.056
   * arr type is GAUSSIAN_ARR , n is 256000, with shuffling is 0.196 , without time is 0.12
   * arr type is GAUSSIAN_ARR , n is 512000, with shuffling is 0.338 , without time is 0.173
   * arr type is GEOMETRIC_ARR , n is 1000, with shuffling is 0.0 , without time is 0.0
   * arr type is GEOMETRIC_ARR , n is 2000, with shuffling is 0.0 , without time is 0.0
   * arr type is GEOMETRIC_ARR , n is 4000, with shuffling is 0.0 , without time is 0.0
   * arr type is GEOMETRIC_ARR , n is 8000, with shuffling is 0.001 , without time is 0.0
   * arr type is GEOMETRIC_ARR , n is 16000, with shuffling is 0.001 , without time is 0.0
   * arr type is GEOMETRIC_ARR , n is 32000, with shuffling is 0.002 , without time is 0.002
   * arr type is GEOMETRIC_ARR , n is 64000, with shuffling is 0.004 , without time is 0.004
   * arr type is GEOMETRIC_ARR , n is 128000, with shuffling is 0.009 , without time is 0.008
   * arr type is GEOMETRIC_ARR , n is 256000, with shuffling is 0.019 , without time is 0.017
   * arr type is GEOMETRIC_ARR , n is 512000, with shuffling is 0.042 , without time is 0.037
   * arr type is POISSON_ARR , n is 1000, with shuffling is 0.001 , without time is 0.0
   * arr type is POISSON_ARR , n is 2000, with shuffling is 0.0 , without time is 0.0
   * arr type is POISSON_ARR , n is 4000, with shuffling is 0.001 , without time is 0.0
   * arr type is POISSON_ARR , n is 8000, with shuffling is 0.001 , without time is 0.0
   * arr type is POISSON_ARR , n is 16000, with shuffling is 0.002 , without time is 0.001
   * arr type is POISSON_ARR , n is 32000, with shuffling is 0.003 , without time is 0.002
   * arr type is POISSON_ARR , n is 64000, with shuffling is 0.006 , without time is 0.005
   * arr type is POISSON_ARR , n is 128000, with shuffling is 0.011 , without time is 0.01
   * arr type is POISSON_ARR , n is 256000, with shuffling is 0.024 , without time is 0.019
   * arr type is POISSON_ARR , n is 512000, with shuffling is 0.053 , without time is 0.043
   * arr type is ARR_TYPE_RANDOM , n is 1000, with shuffling is 0.001 , without time is 0.001
   * arr type is ARR_TYPE_RANDOM , n is 2000, with shuffling is 0.0 , without time is 0.001
   * arr type is ARR_TYPE_RANDOM , n is 4000, with shuffling is 0.001 , without time is 0.001
   * arr type is ARR_TYPE_RANDOM , n is 8000, with shuffling is 0.002 , without time is 0.002
   * arr type is ARR_TYPE_RANDOM , n is 16000, with shuffling is 0.004 , without time is 0.005
   * arr type is ARR_TYPE_RANDOM , n is 32000, with shuffling is 0.006 , without time is 0.004
   * arr type is ARR_TYPE_RANDOM , n is 64000, with shuffling is 0.01 , without time is 0.008
   * arr type is ARR_TYPE_RANDOM , n is 128000, with shuffling is 0.029 , without time is 0.021
   * arr type is ARR_TYPE_RANDOM , n is 256000, with shuffling is 0.083 , without time is 0.058
   * arr type is ARR_TYPE_RANDOM , n is 512000, with shuffling is 0.254 , without time is 0.119
   * arr type is ARR_TYPE_H0H1 , n is 1000, with shuffling is 0.0 , without time is 0.0
   * arr type is ARR_TYPE_H0H1 , n is 2000, with shuffling is 0.0 , without time is 0.0
   * arr type is ARR_TYPE_H0H1 , n is 4000, with shuffling is 0.001 , without time is 0.0
   * arr type is ARR_TYPE_H0H1 , n is 8000, with shuffling is 0.001 , without time is 0.0
   * arr type is ARR_TYPE_H0H1 , n is 16000, with shuffling is 0.001 , without time is 0.001
   * arr type is ARR_TYPE_H0H1 , n is 32000, with shuffling is 0.002 , without time is 0.002
   * arr type is ARR_TYPE_H0H1 , n is 64000, with shuffling is 0.005 , without time is 0.004
   * arr type is ARR_TYPE_H0H1 , n is 128000, with shuffling is 0.009 , without time is 0.008
   * arr type is ARR_TYPE_H0H1 , n is 256000, with shuffling is 0.018 , without time is 0.019
   * arr type is ARR_TYPE_H0H1 , n is 512000, with shuffling is 0.043 , without time is 0.039
   * arr type is ARR_TYPE_RECURSIVE_HALF , n is 1000, with shuffling is 0.0 , without time is 0.0
   * arr type is ARR_TYPE_RECURSIVE_HALF , n is 2000, with shuffling is 0.0 , without time is 0.0
   * arr type is ARR_TYPE_RECURSIVE_HALF , n is 4000, with shuffling is 0.0 , without time is 0.001
   * arr type is ARR_TYPE_RECURSIVE_HALF , n is 8000, with shuffling is 0.001 , without time is 0.0
   * arr type is ARR_TYPE_RECURSIVE_HALF , n is 16000, with shuffling is 0.001 , without time is 0.001
   * arr type is ARR_TYPE_RECURSIVE_HALF , n is 32000, with shuffling is 0.003 , without time is 0.002
   * arr type is ARR_TYPE_RECURSIVE_HALF , n is 64000, with shuffling is 0.004 , without time is 0.005
   * arr type is ARR_TYPE_RECURSIVE_HALF , n is 128000, with shuffling is 0.01 , without time is 0.009
   * arr type is ARR_TYPE_RECURSIVE_HALF , n is 256000, with shuffling is 0.02 , without time is 0.022
   * arr type is ARR_TYPE_RECURSIVE_HALF , n is 512000, with shuffling is 0.042 , without time is 0.051
   * arr type is ARR_TYPE_H0HR , n is 1000, with shuffling is 0.0 , without time is 0.0
   * arr type is ARR_TYPE_H0HR , n is 2000, with shuffling is 0.0 , without time is 0.0
   * arr type is ARR_TYPE_H0HR , n is 4000, with shuffling is 0.0 , without time is 0.0
   * arr type is ARR_TYPE_H0HR , n is 8000, with shuffling is 0.0 , without time is 0.001
   * arr type is ARR_TYPE_H0HR , n is 16000, with shuffling is 0.001 , without time is 0.002
   * arr type is ARR_TYPE_H0HR , n is 32000, with shuffling is 0.003 , without time is 0.003
   * arr type is ARR_TYPE_H0HR , n is 64000, with shuffling is 0.006 , without time is 0.007
   * arr type is ARR_TYPE_H0HR , n is 128000, with shuffling is 0.015 , without time is 0.014
   * arr type is ARR_TYPE_H0HR , n is 256000, with shuffling is 0.047 , without time is 0.043
   * arr type is ARR_TYPE_H0HR , n is 512000, with shuffling is 0.126 , without time is 0.07
   * </code> 大部分情况下,随机选取分割项效率更高
   */
  public static void main(String[] args) {

    String[] arrTypes =
        new String[] {
          DISCRETE_ARR,
          GAUSSIAN_ARR,
          GEOMETRIC_ARR,
          POISSON_ARR,
          ARR_TYPE_RANDOM,
          ARR_TYPE_H0H1,
          ARR_TYPE_RECURSIVE_HALF,
          ARR_TYPE_H0HR
        };
    for (int i = 0; i < arrTypes.length; i++) {
      String type = arrTypes[i];

      for (int n = 1000; n < 1000000; n = n * 2) {
        Comparable[] a = null;
        switch (type) {
          case DISCRETE_ARR:
            a = E02_01_35.getDiscreteArr(n);
            break;
          case GAUSSIAN_ARR:
            a = E02_01_35.getGaussianArr(n);
            break;
          case GEOMETRIC_ARR:
            a = E02_01_35.getGeometricArr(n);
            break;
          case POISSON_ARR:
            a = E02_01_35.getPoissonArr(n);
            break;
          case ARR_TYPE_RANDOM:
            a = E02_01_36.getRandomArr(n);
            break;
          case ARR_TYPE_H0H1:
            a = E02_01_36.getH0H1Arr(n);
            break;
          case ARR_TYPE_RECURSIVE_HALF:
            a = E02_01_36.getRecursiveHalfArr(n);
            break;
          case ARR_TYPE_H0HR:
            a = E02_01_36.getH0HrArr(n);
            break;
        }

        Comparable[] b = new Comparable[n];
        System.arraycopy(a, 0, b, 0, a.length);

        Stopwatch timer = new Stopwatch();
        Quick.sort(a);
        double withShufflingTime = timer.elapsedTime();

        timer = new Stopwatch();
        sortWithoutShuffling(b);
        double randomPtime = timer.elapsedTime();

        StdOut.println(
            "arr type is "
                + type
                + " , n is "
                + n
                + ", with shuffling is "
                + withShufflingTime
                + " , without time is "
                + randomPtime);
      }
    }
  }

  private static void sortWithoutShuffling(Comparable[] b) {
    Quick.sort(b, 0, b.length - 1);
  }
}
