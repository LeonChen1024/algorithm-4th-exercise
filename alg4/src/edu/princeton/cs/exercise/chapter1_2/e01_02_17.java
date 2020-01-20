package edu.princeton.cs.exercise.chapter1_2;

/**
 * 1.2.17 Robust implementation of rational numbers. Use assertions to develop an implementation of
 * Rational (see Exercise 1.2.16) that is immune to overflow.
 *
 * <p>有理数的健壮实现.是用断言来开发一个有理数(见练习1.2.16)可以避免溢出.
 *
 * @author LeonChen
 * @since 12/12/19
 */
class e01_02_17 {

  public static void main(String[] args) {
    Rational rational1 = new Rational(2, 8);
    Rational rational2 = new Rational(1, 3);
    Rational result1 = rational1.plus(rational2);
    Rational result2 = rational1.minus(rational2);
    Rational result3 = rational1.times(rational2);
    Rational result4 = rational1.devides(rational2);
    System.out.println("plus:" + result1);
    System.out.println("minus:" + result2);
    System.out.println("times:" + result3);
    System.out.println("devides:" + result4);
  }

  public static class Rational {

    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator) {

      if (denominator == 0) throw new RuntimeException("Denominator is zero");

      int g = gcd(numerator, denominator);

      if (denominator < 0) g = -g;

      this.numerator = numerator / g;
      this.denominator = denominator / g;
    }

    public static int gcd(int m, int n) {
      int rem = 0;
      int M = m;
      int N = n;
      if (m < n) {
        M = n;
        N = m;
      }
      rem = M % N;
      if (0 == rem) return N;
      return gcd(N, rem);
    }

    // 参考jdk中的Math 类中的溢出判断
    /** 加法溢出判断 */
    public static void addExact(int x, int y) {
      int r = x + y;

      // 两个正数或者两个负数相加的时候才会溢出，一正一负是不会溢出的。溢出后符号位会于预期相反
      // (x ^ r) & (y ^ r)将 r与x和y分别异或。异或的规则是同0异1。我们只看符号位，符号位和两个数都不相同则说明发生了
      // 溢出
      assert (!(((x ^ r) & (y ^ r)) < 0)) : "加法溢出";
    }

    /** 减法溢出判断 */
    public static void subtractExact(int x, int y) {
      int r = x - y;

      // 减法必须是两个数不同的时候才会发生,最后的结果符号位会与x不同.通过异或得到符号位的异同.
      assert (!(((x ^ y) & (x ^ r)) < 0)) : "减法溢出";
    }

    /** 乘法溢出判断 */
    public static void multiplyExact(int x, int y) {
      long r = (long) x * (long) y;
      // 通过强转成long型将符号位后移,再转回来,观察是否相同.
      assert (!((int) r != r)) : "乘法溢出";
    }

    public Rational plus(Rational b) {
      multiplyExact(this.numerator, b.denominator);
      multiplyExact(b.numerator, this.denominator);
      multiplyExact(this.denominator, b.denominator);

      addExact(this.numerator * b.denominator, b.numerator * this.denominator);

      int numerator = this.numerator * b.denominator + b.numerator * this.denominator;
      int denominator = this.denominator * b.denominator;
      Rational rational = new Rational(numerator, denominator);

      return rational;
    }

    public Rational minus(Rational b) {
      multiplyExact(this.numerator, b.denominator);
      multiplyExact(b.numerator, this.denominator);
      multiplyExact(this.denominator, b.denominator);

      subtractExact(this.numerator * b.denominator, b.numerator * this.denominator);

      int numerator = this.numerator * b.denominator - b.numerator * this.denominator;
      int denominator = this.denominator * b.denominator;
      Rational rational = new Rational(numerator, denominator);
      return rational;
    }

    public Rational times(Rational b) {
      multiplyExact(this.numerator, b.numerator);
      multiplyExact(b.denominator, this.denominator);

      int numerator = this.numerator * b.numerator;
      int denominator = this.denominator * b.denominator;
      Rational rational = new Rational(numerator, denominator);
      return rational;
    }

    public Rational devides(Rational b) {
      // 除以一个数，等于乘以这个数的倒数。
      Rational temp = new Rational(b.denominator, b.numerator);
      this.times(temp);
      return temp;
    }

    public boolean equals(Rational b) {
      if (this == b) return true;
      if (b == null) return false;
      if (this.getClass() != b.getClass()) return false;
      return (numerator == b.numerator && denominator == b.denominator);
    }

    public String toString() {
      return this.numerator + "/" + this.denominator;
    }
  }
}
