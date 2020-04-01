package edu.princeton.cs.exercise.chapter1_4;

/**
 * 1.4.32 Amortized analysis. Prove that, starting from an empty stack, the number of array accesses
 * used by any sequence of M operations in the resizing array implementation of Stack is
 * proportional to M.
 *
 * <p>均摊分析.求证,从一个空的使用可扩容数组的栈开始,在可扩容数组种M次操作对数据的访问是M的倍数.
 *
 * @author LeonChen
 * @since 3/26/20
 */
class e01_04_32 {

  /**
   * 首先我们可以假设push的时候才会去触发扩容,导致操作次数增加,可以得出下表 <code>
   *
   *   栈操作次数   o   1  2  3  4  5  6  7  8  9 10
   *   数组容量    as  1  2  4  4  8  8  8  8 16 16
   *   数组操作次  ao  1  2  3  1  5  1  1  1  9  1
   * </code> 每次扩容的时候,ao = as+1. 也就是说当数组大小扩容到 2^(n+1)的时候 , 要经过 n 次栈操作也就是o = n<br>
   * 扩容总共需要 2^0+1 + 2^1 +1 + ... + 2^n +1 , 加上非扩容所需 n-1 次,总共约 3n 次.
   *
   * @param args
   */
  public static void main(String[] args) {}
}
