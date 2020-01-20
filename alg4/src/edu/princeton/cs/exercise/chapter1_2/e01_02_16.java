package edu.princeton.cs.exercise.chapter1_2;

/**
 * 1.2.16 Rational numbers. Implement an immutable data type Rational for rational numbers that
 * supports addition, subtraction, multiplication, and division. <code>public class Rational
 *
 * Rational(int numerator. int denominator)
 * Rational plus(Rational b) sum of this number and b
 * Rational minus(Rational b) difference of this number and b
 * Rational times(Rational b) product of this number and b
 * Rational divides(Rational b) quotient of this number and b boolean
 * equals(Rational that) is this number equal to that ?
 * String toString() string representation
 *
 * </code> You do not have to worry about testing for overflow (see Exercise 1.2.17),but use as
 * instance variables two long values that represent the numerator and denominator to limit the
 * possibility of overflow. Use Euclid’s algorithm (see page 4) to ensure that the numerator and
 * denominator never have any common factors. Include a test client that exercises all of your
 * methods.
 *
 * <p>有理数.实现一个不可变的数据类型 Rational 给有理数,并支持加减乘除. <code>public class Rational
 *  *
 *  * Rational(int numerator. int denominator)
 *  * Rational plus(Rational b) sum of this number and b
 *  * Rational minus(Rational b) difference of this number and b
 *  * Rational times(Rational b) product of this number and b
 *  * Rational divides(Rational b) quotient of this number and b boolean
 *  * equals(Rational that) is this number equal to that ?
 *  * String toString() string representation
 *  *
 *  * </code> 你不需要担心有关溢出的测试(参见 1.2.17) ,只需要使用两个 long 型的实例变量来代表分子和分母来限制可能的溢出<br>
 * .使用欧几里得算法(第4页)来确保分子和分母不会有公因子.包含一个客户端实践了所有你的方法.
 *
 * @author LeonChen
 * @since 12/10/19
 */
class e01_02_16 {

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

    public Rational plus(Rational b) {
      int numerator = this.numerator * b.denominator + b.numerator * this.denominator;
      int denominator = this.denominator * b.denominator;
      Rational rational = new Rational(numerator, denominator);
      return rational;
    }

    public Rational minus(Rational b) {
      int numerator = this.numerator * b.denominator - b.numerator * this.denominator;
      int denominator = this.denominator * b.denominator;
      Rational rational = new Rational(numerator, denominator);
      return rational;
    }

    public Rational times(Rational b) {
      int numerator = this.numerator * b.numerator;
      int denominator = this.denominator * b.denominator;
      Rational rational = new Rational(numerator, denominator);
      return rational;
    }

    public Rational devides(Rational b) {
      int numerator = this.numerator * b.denominator;
      int denominator = this.denominator * b.numerator;
      Rational rational = new Rational(numerator, denominator);
      return rational;
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
