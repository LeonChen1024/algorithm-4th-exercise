package edu.princeton.cs.exercise.chapter3_1;

/**
 * 3.1.20 Complete the proof of Proposition B (show that it holds for all values of N).
 * Hint : Start by showing that C(N) is monotonic: C(N) <= C(N+1) for all N > 0.
 * <p>
 * 完成主张 B 的证明(证明它持有了所有 N 的值). 提示: 可以先证明 C(N) 是单调的:对于所有的 N>0 来说C(N)<=C(N+1).
 *
 * @author LeonChen
 * @since 2/13/20
 */
class E3_1_20 {


    /**
     * C(N)是在一个大小为 N 的符号表中搜索一个 key 的对比次数.
     * 我们可以得出 C(0)= 0, C(1)=1,并且对于 N>0 我们可以得出 C(N)<=C(FLOOR(N/2))+1
     * 通过一次对比可以得出是查询哪个子数组.不论是查询左子数组还是右子数组,该子数组的大小不会超过 FLOOR(N/2)
     * 由上公式可得 C(N) <= C(N / 2) + 1 , C(N / 2) <= C(N / 4) + 1, 将C(N / 2) 的值代入得
     * C(N) <= C(N / 4) + 1 + 1 这样递推下去 得到
     * C(N) <= C(N / 8) + 1 + 1 + 1
     * C(N) <= C(N / 16) + 1 + 1 + 1 + 1
     * C(N) <= C(N / 2^k) + 1 + 1 + 1 + 1 + ... + 1
     * 直到
     * C(N) <= 1 + 1 + 1 + 1 + 1 + ... + 1
     * C(N) <= k + 1
     * 其中 2^k = N
     * k<=lgN+1
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}
