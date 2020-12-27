package edu.princeton.cs.exercise.chapter2_5;

/**
 * 2.5.1 Consider the following implementation of the compareTo() method for String. How does the
 * third line help with efficiency? <code>
 *   public int compareTo(String that)
 *   {
 *     if (this == that) return 0; // this line
 *     int n = Math.min(this.length(), that.length());
 *     for (int i = 0; i < n; i++) {
 *       if (this.charAt(i) < that.charAt(i)) return -1;
 *       else if (this.charAt(i) > that.charAt(i)) return +1;
 *     }
 *     return this.length() - that.length();
 *   }</code>
 *
 * <p>思考下面给String的 compareTo() 的实现.思考第三行代码是怎么优化性能的?
 *
 * @author LeonChen
 * @since 12/14/20
 */
class E02_05_01 {

  /**
   * 因为String有常量池的概念,所以大多数情况下相同的字符串都是引用到相同的常量上,使用这个对象判断,可以省去大量的<br>
   * 字符对比的工作量
   */
  public static void main(String[] args) {}
}
