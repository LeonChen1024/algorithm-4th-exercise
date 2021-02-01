package edu.princeton.cs.exercise.chapter1_1;


/**
 * 1.1.34 Filtering. Which of the following require saving all the values from standard input (in
 * an array, say), and which could be implemented as a filter using only a fixed number of
 * variables and arrays of fixed size (not dependent on N)? For each, the input comes from
 * standard input and consists of N real numbers between 0 and 1.<br>
 * ■ Print the maximum and minimum numbers. <br>
 * ■ Print the median of the numbers.<br>
 * ■ Print the k th smallest value, for k less than 100. <br>
 * ■ Print the sum of the squares of the numbers. <br>
 * ■ Print the average of the N numbers. <br>
 * ■ Print the percentage of numbers greater than the average.<br>
 * ■ Print the N numbers in increasing order.<br>
 * ■ Print the N numbers in random order.<br>
 *
 * <p>以下哪些需要从输入中保存值,比如保存到数组.哪些可以使用固定数量的变量和固定大小的数组(与N无关)? 对于每一项,输入都是N 个真实数字大小在0~1之间. ■ Print the
 * 在这里我们使用 all 代表要保存所有的值, fix 代表可以使用固定值.
 *
 * <p>■ 打印最大的数字和最小的数字<br>
 * fix,根据不断的读取输入更新最大和最小的数字即可,其他的数字可以忽略.
 *
 * <p>■ 打印中间值.<br>
 * all,必须要保存全部的值才能计算,只保留部分可能会出现将中间值提前清除的可能
 *
 * <p>■ 打印第k个最小值,k小于100. <br>
 * fix,可以保存最小的100个输入即可
 *
 * <p>■ 打印出所有数字的平方和<br>
 * fix,直接累加即可
 *
 * <p>■ 打印出N个数的平均值. <br>
 * fix,按照输入计算即可,无需保留所有.
 *
 * <p>■ 打印出大于平均数的数字数量的百分比.<br>
 * all,只有在全部遍历完之后才能确定平均数是多少,才有办法将之前的数字进行分类.
 *
 * <p>■ 使用升序打印N个数字.<br>
 * all,只有全部遍历后才有办法排序打印
 *
 * <p>■ 用随机顺序打印N个数字.<br>
 * all,全部保存后才能做到真随机.
 */
class E1_1_34 {


    public static void main(String[] args) {
    }
}
