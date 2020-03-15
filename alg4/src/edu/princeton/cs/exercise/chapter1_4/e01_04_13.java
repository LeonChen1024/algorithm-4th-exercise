package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Transaction;
import edu.princeton.cs.exercise.chapter1_3.e01_03_01;

/**
 * 1.4.13 Using the assumptions developed in the text, give the amount of memory needed to represent
 * an object of each of the following types: <br>
 * a. Accumulator <br>
 * b. Transaction<br>
 * c.FixedCapacityStackOfStrings with capacity C and N entries<br>
 * d. Point2D <br>
 * e. Interval1D <br>
 * f. Interval2D <br>
 * g. Double
 *
 * <p>使用文中的假设,给出下面每个对象类型所需要的内存大小.: <br>
 * a. Accumulator <br>
 * b. Transaction<br>
 * c.FixedCapacityStackOfStrings with capacity C and N entries<br>
 * d. Point2D <br>
 * e. Interval1D <br>
 * f. Interval2D <br>
 * g. Double
 *
 * @author LeonChen
 * @since 3/10/20
 */
class e01_04_13 {

  public static void main(String[] args) {
    /**
     * P93页. <br>
     * 首先 overhead 占用16个字节,total 8个字节, N 4个.再加上64位系统的内存对齐 +4个(凑成8的整数倍).<br>
     * 总共16+8+4+4 = 32字节.
     */
    Accumulator accumulator = new Accumulator();

    /**
     * overhead 16字节. String who : 40字节 () + 8字节引用; double amount :8个字节. Date when; 引用8字节,Date : int
     * month; <br>
     * int day; int year; 各4字节.加上 overhead 16字节 加上对齐4字节
     *
     * <p>总共 16+ 40+8 +8 +8+4+4+4+16+4 = 112字节
     */
    Transaction transaction = new Transaction("");
    Date date = new Date("");
    String s = new String();

    /**
     * private String[] a; 8字节String[]引用. 40N 字节String内容 8N 字节引用.<br>
     * private int N; 4字节<br>
     * 内存对齐 4字节. <br>
     * 总共 16+48N
     */
    e01_03_01.FixedCapacityStackOfStrings fixedCapacityStackOfStrings =
        new e01_03_01.FixedCapacityStackOfStrings(1);

    /**
     * overhead 16bytes <br>
     * final double x; 8bytes;<br>
     * final double y; 8bytes<br>
     * 16+8+8 = 32 bytes
     */
    Point2D point2D = new Point2D(0, 0);

    /**
     * overhead 16bytes<br>
     * double min; 8bytes;<br>
     * double max; 8 bytes<br>
     * 16+8+8 = 32bytes
     */
    Interval1D interval1D = new Interval1D(0, 0);

    /**
     * overhead 16bytes;<br>
     * final Interval1D x; 8 bytes 引用, 32bytes object .<br>
     * final Interval1D y; 8 bytes 引用, 32bytes object .<br>
     * 16+8+32+8+32 = 56bytes
     */
    Interval2D interval2D = new Interval2D(interval1D, interval1D);

    /**
     * overhead 16bytes<br>
     * private final double value; 8bytes<br>
     * 16+8 = 24bytes
     */
    Double d = new Double(0);
  }

  /**
   * String objects. We account for memory inJava’s String objectsinthesame way as for any other
   * object, except that aliasing is common for strings. The standard String implementation has four
   * instance variables: a reference to a character array (8 bytes) and three int values (4 bytes
   * each). The first int value is an offset into the character ar- ray; the second is a count (the
   * string length). In terms of the instance variable names in the drawing on the facing page, the
   * string that is represented consists of the characters value[offset] through value[offset +
   * count - 1]. The third int value in String objects is a hash code that saves recomputation in
   * certain circumstances that need not concern us now. Therefore, each String object uses a total
   * of 40 bytes (16 bytes for object overhead plus 4 bytes for each of the three int instance
   * variables plus 8 bytes for the array reference plus 4 bytes of padding). This space requirement
   * is in addition to the space needed for the characters themselves, which are in the array. The
   * space needed for the characters is accounted for separately because the char array is often
   * shared among strings. Since String objects are immutable, this arrangement allows the imple-
   * mentation to save memory when String objects have the same underlying value[].
   */
  public static class Accumulator {
    private double total;
    private int N;

    public void addDataValue(double val) {
      N++;
      total += val;
    }

    public double mean() {
      return total / N;
    }

    public String toString() {
      return "Mean(" + N + "values):" + String.format("%7.5f", mean());
    }
  }
}
