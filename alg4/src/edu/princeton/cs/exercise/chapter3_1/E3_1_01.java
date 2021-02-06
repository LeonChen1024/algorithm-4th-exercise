package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.1.1 Write a client that creates a symbol table mapping letter grades to numerical
 * scores, as in the table below, then reads from standard input a list of letter grades and
 * computes and prints the GPA (the average of the numbers corresponding to the grades).
 * <code>
 * A+     A     A-     B+     B     B-     C+     C     C-     D     F
 * 4.33    4.00  3.67   3.33   3.00  2.67   2.33   2.00  1.67   1.00  0.00</code>
 * <p>
 * 编写一个客户端创建一个符号表映射等级到分数值,如下表,然后从标准输入中读取等级列表并且计算打印出 GPA(等级
 * 对应的分数的平均值)
 *
 * @author LeonChen
 * @since 1/28/20
 */
class E3_1_01 {

    /**
     *
     */
    public static void main(String[] args) {
    }

    /**
     * 官方解答
     */
    public static class GPA {
        public static void main(String[] args) {

            // create symbol table of grades and values
            ST<String, Double> grades = new ST<String, Double>();
            grades.put("A", 4.00);
            grades.put("B", 3.00);
            grades.put("C", 2.00);
            grades.put("D", 1.00);
            grades.put("F", 0.00);
            grades.put("A+", 4.33);
            grades.put("B+", 3.33);
            grades.put("C+", 2.33);
            grades.put("A-", 3.67);
            grades.put("B-", 2.67);


            // read grades from standard input and compute gpa
            int n = 0;
            double total = 0.0;
            for (n = 0; !StdIn.isEmpty(); n++) {
                String grade = StdIn.readString();
                double value = grades.get(grade);
                total += value;
            }
            double gpa = total / n;
            StdOut.println("GPA = " + gpa);
        }
    }


}
