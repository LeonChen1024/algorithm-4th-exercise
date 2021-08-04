package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.8 How many empty lists do you expect to see when you insert N keys into a hash
 * table with SeparateChainingHashST, for N=10, 10^2, 10^3, 10^4, 10^5, and 10^6? Hint :
 * See Exercise 2.5.31.
 * <p>
 * 当你插入 N 个键到一个 hashtable SeparateChainingHashST 中的时候预计会看到多少个空数组,其中
 * N=10, 10^2, 10^3, 10^4, 10^5, 和 10^6 ,提示: 见练习 2.5.31
 *
 * @author LeonChen
 * @since 7/28/21
 */
class E3_4_08 {

    /**
     * @formatter:off
     * 根据 2.5.31 得到的不同的键数量为  M(1 - e^(-a)) . 设 a=1
     * N = 10
     * 10(1 - e^(-1)) ~ 6
     * 空列表= 10-6 =4
     *
     * N = 10^2
     * 10^2(1 - e^(-1)) ~ 63
     * 空列表 = 10^2-63 = 37
     *
     * N = 10^3
     * 10^3(1 - e^(-1)) ~ 632
     * 空列表 = 10^3 - 632 = 368
     *
     * N = 10^4
     * 10^4(1 - e^(-1)) ~ 6321
     * 空列表 = 10^4 - 6321 = 3679
     *
     * N = 10^5
     * 10^5(1 - e^(-1)) ~ 63212
     * 空列表 = 10^5 - 63212 = 36788
     *
     * N = 10^6
     * 10^6(1 - e^(-1)) ~ 632120
     * 空列表 = 10^6 - 632120 = 367880
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
