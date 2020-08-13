package edu.princeton.cs.exercise.chapter2_3;

/**
 * 2.3.12 Show, in the style of the trace given with the code, how the entropy-optimal sort first
 * partitions the array B A B A B A B A C A D A B R A .
 *
 * <p>使用代码中分析的风格图展示出数组 B A B A B A B A C A D A B R A 的熵最优排序
 *
 * @author LeonChen
 * @since 8/6/20
 */
class E02_03_12 {

  /**
   * 也就是三路快排的分析图 <code>
   * lt  i  gt   0  1  2  3  4  5  6  7  8  9  10  11  12  13  14
   *  0  1  14   B  A  B  A  B  A  B  A  C  A   D   A   B   R   A
   *  1  2  14   A  B  B  A  B  A  B  A  C  A   D   A   B   R   A
   *  1  3  14   A  B  B  A  B  A  B  A  C  A   D   A   B   R   A
   *  2  4  14   A  A  B  B  B  A  B  A  C  A   D   A   B   R   A
   *  2  5  14   A  A  B  B  B  A  B  A  C  A   D   A   B   R   A
   *  3  6  14   A  A  A  B  B  B  B  A  C  A   D   A   B   R   A
   *  3  7  14   A  A  A  B  B  B  B  A  C  A   D   A   B   R   A
   *  4  8  14   A  A  A  A  B  B  B  B  C  A   D   A   B   R   A
   *  4  8  13   A  A  A  A  B  B  B  B  A  A   D   A   B   R   C
   *  5  9  13   A  A  A  A  A  B  B  B  B  A   D   A   B   R   C
   *  6 10  13   A  A  A  A  A  A  B  B  B  B   D   A   B   R   C
   *  6 10  12   A  A  A  A  A  A  B  B  B  B   R   A   B   D   C
   *  6 10  11   A  A  A  A  A  A  B  B  B  B   B   A   R   D   C
   *  6 11  11   A  A  A  A  A  A  B  B  B  B   B   A   R   D   C
   *  7 12  11   A  A  A  A  A  A  A  B  B  B   B   B   R   D   C
   *  7 12  11   A  A  A  A  A  A  A  B  B  B   B   B   R   D   C
   *
   *
   * </code>
   */
  public static void main(String[] args) {}
}
