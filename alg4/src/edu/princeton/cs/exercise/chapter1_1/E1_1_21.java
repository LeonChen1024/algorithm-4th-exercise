package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.21 Write a program that reads in lines from standard input with each line containing a name
 * and two integers and then uses printf() to print a table with a column of the names, the
 * integers, and the result of dividing the first by the second, accurate to three decimal places.
 * You could use a program like this to tabulate batting averages for baseball players or grades
 * for students.
 * <p>
 * 编写一个程序从标准输入中读取行,每行包含一个名字和两个整数然后使用 printf() 来打印一个表格使用 名字和 整数作为列,结果是使用第一个
 * 数除以第二个数,精度取 3 位小数.你可以使用一个像这个程序来计算棒球选手的平均击打或者学生的等级
 */
class E1_1_21 {


    /**
     *
     */
    public static void main(String[] args) {
        int inputPerRow = 3;
        int index = 0;
        String[] strs = new String[inputPerRow];
        while (index < inputPerRow) strs[index++] = StdIn.readLine();
        for (int i = 0; i < strs.length; ++i) {
            String[] arr = strs[i].split("\\s+");
            double temp = Double.parseDouble(arr[1]) / Double.parseDouble(arr[2]);
            StdOut.printf("%-10s   %-10s   %-10s   %-13.3f\n", arr[0], arr[1], arr[2], temp);
        }
    }
}
