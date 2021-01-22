package edu.princeton.cs.exercise.chapter2_5;

/**
 * 2.5.30 Boerner’s theorem. True or false: If you sort each column of a matrix, then sort
 * each row, the columns are still sorted. Justify your answer.
 * <p>
 * 博纳理论.对还是错: 如果你排序矩阵中的每个列,然后对每行进行排序,列仍然是有序的.证明你的答案
 *
 * @author LeonChen
 * @since 1/10/20
 */
class E02_05_30 {


    /**
     * 这个理论是对的.列已经是有序的了,此时针对行排序,第一行当出现交换的时候可知较小的数字a交换到的列依然是有序的,
     * 较大的数字b的列此时可能并不是有序的,但是我们可以得出,如果此时b 的列并不是有序的,那么第二行的 b 列的值一定
     * 是小于第二行的 a 列,这个时候这两的位置也要进行交换,那么此时就会保证了 b 列也是有序的,依次类推,得证
     */
    public static void main(String[] args) {

    }


}
