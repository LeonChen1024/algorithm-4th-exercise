package edu.princeton.cs.exercise.chapter2_2;

/**
 * 2.2.4 Does the abstract in-place merge produce proper output if and only if the two input
 * subarrays are in sorted order? Prove your answer, or provide a counterexample.
 *
 * <p>就地合并排序是否只有在两个子数组有序的情况下才会输出正确的数据?证明你的答案,或者提供一个反例
 *
 * @author LeonChen
 * @since 6/19/20
 */
class E02_02_04 {

  /**
   * 是的, 因为两个子数组的每一次对比之后都会将一个值的位置确定下来,所以如果原来两个子数组不是有序的话,就会造成<br>
   * 输出结果顺序出错.比如 , 3,2,1 和 2,6,8 . 最后输出的结果会是 2,3,2,1,6,8
   */
  public static void main(String[] args) {}
}
