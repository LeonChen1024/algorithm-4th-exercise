package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.exercise.chapter1_3.e01_03_30;

/**
 * 1.4.33 Memory requirements on a 32-bit machine. Give the memory requirements for Integer, Date,
 * Counter, int[], double[], double[][], String, Node, and Stack (linked-list representation) for a
 * 32-bit machine. Assume that references are 4 bytes, object overhead is 8 bytes, and padding is to
 * a multiple of 4 bytes.
 *
 * <p>32位机器上的内存使用.给出Integer, Date,Counter, int[], double[], double[][], String, Node, 和 Stack
 * (使用linked-list表示) 在32位机器上的内存要求.假设引用是4bytes,对象overhead 是8bytes,内存对齐到 4byte的倍数.
 *
 * @author LeonChen
 * @since 3/26/20
 */
class e01_04_33 {

  /**
   * <code>
   *     Integer :
   *     object overhead = 8 bytes
   *     int value = 4 bytes
   *     total = 8 + 4 = 12
   *
   *     Date:
   *     object overhead = 8 bytes
   *     int day = 4 bytes
   *     int month = 4 bytes
   *     int year = 4 bytes
   *     total = 8 + 4 + 4 + 4 = 20 bytes
   *
   *     Counter:
   *     object overhead  =  8 bytes
   *     String name  =  4 bytes
   *     int count  =  4 bytes
   *     total 8 + 4 + 4 = 16 bytes
   *
   *     int[]:
   *     object overhead  =  8 bytes
   *     int length  =  4 bytes
   *     N ints  =  4N bytes
   *     total= 8 + 4 + 4N = 4N + 12 bytes
   *
   *     double[]
   *     object overhead  =  8 bytes
   *     int length  =  4 bytes
   *     N doubles  =  8N bytes
   *     total = 8 + 4 + 8N = 8N + 12 bytes
   *
   *     double[M][N]
   *     object overhead  =  8 bytes
   *     int M array length  =  4 bytes
   *     M double[N] references  =  4N bytes
   *     M double[N] overheads  =  8N bytes
   *     M double[N] length = 4N bytes
   *     MN double values  =  8MN
   *     total =   12 +16N+ 8MN = 8MN + 16N + 12 bytes
   *
   *     String
   *     object overhead  =  8 bytes
   *     char[] reference (value)  =  4 bytes
   *     int hash  =  4 bytes
   *     char[] overhead  =  8 bytes
   *     char[] int length  =  4 bytes
   *     char[] values  =  2N bytes
   *     total =  8 + 4 + 4 + 12 + 2N = 2N + 28 bytes
   *
   *     Node
   *     object overhead  =  8 bytes
   *     extra overhead for reference to the enclosing instance  =  4 bytes
   *     Item reference (item)  =  4 bytes
   *     Node reference (next)  =  4 bytes
   *     total =  8 + 4 + 4 + 4 = 20 bytes
   *
   *
   *     Stack (linked-list representation) - this example assumes a Stack of Integers
   *     object overhead  =  8 bytes
   *     Node reference (first)  =  4 bytes
   *     int value (N)  =  4 bytes
   *     N Nodes  =  20N bytes
   *     N Integers  =  12N bytes
   *     total =  8 + 4 + 4 + 20N + 12N = 32N + 16 bytes
   *
   * </code>
   *
   * @param args
   */
  public static void main(String[] args) {
    //    look detail of a value in below
    Integer integer = new Integer(3);
    Date date = new Date(1, 2, 3);
    Counter counter = new Counter("23");
    int[] ints = new int[3];
    double[] doubles = new double[2];
    double[][] doubles1 = new double[2][2];
    String s = new String();
    e01_03_30.Node node = new e01_03_30.Node();
    Stack stack = new Stack();
  }
}
