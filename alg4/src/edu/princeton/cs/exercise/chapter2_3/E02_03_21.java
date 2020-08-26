package edu.princeton.cs.exercise.chapter2_3;

/**
 * 2.3.21 Lower bound for sorting with equal keys. Complete the first part of the proof of
 * Proposition M by following the logic in the proof of Proposition I and using the observation that
 * there are N! / f_1!f_2! . . . f_k! different ways to arrange keys with k different values, where
 * the i th value appears with frequency f_i (= Np_i , in the notation of Proposition M),with
 * f_1+...+f_k=N.
 *
 * <p>相同键排序的更低边界.按照假说I的证明逻辑完成假说 M 的第一部分的证明并且使用观察到这里有 <br>
 * N! / f_1!f_2! . . . f_k! 种不同的方式可以分配k个不同的键,其中 第i个值出现的次数 <br>
 * f_i (= Np_i , 假说 M 中的科学计数法),f_1+...+f_k=N.
 *
 * @author LeonChen
 * @since 8/19/20
 */
class E02_03_21 {

  /**
   * <code>
   *     根据命题 I 可得  h≥log2N!
   *     现在求证 h ≥log_2 (N!/f_1 !f_2 !⋯f_k !) ≥ log_2 N!
   *     如果主键互不重复，此时 k=N，且 f_1=f_2=⋯=f_k=1,可得  f_1 !f_2 !⋯f_k !=1
   * 当主键有重复时，此时 k<N，为使 f_1+f_2+⋯+f_k=N ，至少存在一个 f_m≥2.
   * 可得  f1!f2!⋯fk!>1  --->  N!/f_1 !f_2 !⋯f_k !< N! ---> h≥log_2 (N!f_1 !f_2 !⋯f_k !)≥log_2 N!
   * </code>
   */
  public static void main(String[] args) {}
}
