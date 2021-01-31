package edu.princeton.cs.exercise.chapter1_1;


/**
 * 1.1.25 Use mathematical induction to prove that Euclid’s algorithm computes the greatest common
 * divisor of any pair of nonnegative integers p and q.
 * <p>
 * 使用数学归纳法来证明欧几里得算法对非负整数 p 和 q计算最大公约数
 */
class E1_1_25 {


    /**
     *
     */
    public static void main(String[] args) {
        // a可以表示成a = kb + r（a，b，k，r皆为正整数，且r<b），则r = a mod b
        // 假设d是a,b的一个公约数，记作d|a,d|b，即a和b都可以被d整除。
        // 而r = a - kb，两边同时除以d，r/d=a/d-kb/d=m，由等式右边可知m为整数，因此d|r
        // 因此d也是b,a mod b的公约数
        // 假设d是b,a mod b的公约数, 则d|b,d|(a-k*b),k是一个整数。
        // 进而d|a.因此d也是a,b的公约数
        // 因此(a,b)和(b,a mod b)的公约数是一样的，a mod b 的最大公约数也就是 a,b 的最大公约数，得证。
    }
}
