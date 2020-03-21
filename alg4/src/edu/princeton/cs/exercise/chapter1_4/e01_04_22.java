package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.22 Binary search with only addition and subtraction. [Mihai Patrascu] Write a program that,
 * given an array of N distinct int values in ascending order, determines whether a given integer is
 * in the array. You may use only additions and subtractions and a constant amount of extra memory.
 * The running time of your program should be proportional to log N in the worst case.
 *
 * <p>Answer : Instead of searching based on powers of two (binary search), use Fibonacci numbers
 * (which also grow exponentially). Maintain the current search range to be the interval [i, i +F_k]
 * and keep F_k and F_(k–1) in two variables. At each step compute F_(k–2) via subtraction, check
 * element i+F_(k–2) , and update the current range to either [i, i + F_(k–2)] or [i+F_(k–2) ,
 * i+F_(k–2)+F_(k–1)].
 *
 * <p>只用加减实现的二分查找.[Mihai Patrascu] 编写一个程序,给定一个包含 N个不同的递增int数的数组,判断给定的<br>
 * 数字是否在数组中.你应该只需要用到加减法和一个常量级的额外内存.你程序的运行时间应该在最差的情况下保证 logN的时间复杂度.
 *
 * <p>答案: 使用 Fibonacci 数字(同样是指数级的增长)来取代基于二的幂的二分查找.保证当前搜索范围在 [i, i +F_k] <br>
 * 并且将 F_k 和 F_(k–1)保存在两个变量中.在每个步骤中通过减法计算 F_(k–2) ,检查 i+F_(k–2) 的元素,<br>
 * 并且更新当前范围到 [i, i + F_(k–2)] 或者 [i+F_(k–2),i+F_(k–2)+F_(k–1)].
 *
 * @author LeonChen
 * @since 3/18/20
 */
class e01_04_22 {

  /**
   * 斐波那契数列以如下被以递推的方法定义：F(1)=1，F(2)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 3，n ∈ N*）.
   *
   * @param args
   */
  public static void main(String[] args) {

    int[] keys = new int[] {1, 4, 6, 9, 12, 15, 17, 23, 24, 34, 37, 38, 45, 47, 58};

    binaryWithFibonacci(4, keys);
  }

  private static void binaryWithFibonacci(int target, int[] keys) {
    int temp = 0;
    int fkm1 = 0;
    int fk = 1;

    // 找到初始的fk
    while (fk < keys.length - 1) {
      temp = fk;
      fk = fk + fkm1;
      fkm1 = temp;
    }

    int lo = 0;
    int hi = keys.length - 1;

    while (lo < hi) {
      int mid = lo + fk - fkm1;
      if (keys[mid] < target) {
        lo = lo + fk - fkm1 + 1;
      } else if (keys[mid] > target) {
        hi = lo + fk - fkm1 - 1;
      } else {
        StdOut.println("value is in the index " + mid);
        return;
      }
      // 更新斐波那契数
      temp = fkm1;
      fkm1 = fk - fkm1;
      fk = temp;
    }
    StdOut.println("value is not in array");
  }
}
