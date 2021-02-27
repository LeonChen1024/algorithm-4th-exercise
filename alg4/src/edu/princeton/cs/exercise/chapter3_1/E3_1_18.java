package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;

/**
 * 3.1.18 Prove that the rank() method in BinarySearchST is correct.
 * <p>
 * 证明 BinarySearchST 中的 rank()方法是正确的
 *
 * @author LeonChen
 * @since 2/11/20
 */
class E3_1_18 {


    /**
     * 返回的 key 是目标值,使用的方法就是二分法,当找到相等的 key 的时候,显然是目标 key.
     * <p>
     * 当目标键不存在的时候,lo 可以代表小于 key 的键数量.
     * <p>
     * 由于 lo 只有在 keys[mid]< key 的时候才会移动,所以 keys[lo-1] 一定是小于 key 的,同理 hi 只有在
     * keys[mid] > key 的时候才会移动,所以 keys[hi+1] 一定是大于 key 的. 而 hi 和 lo 每次只会比 mid 多
     * 移动一位,所以可知当不存在key 的时候,上一次一定是 mid = lo 或者 mid = hi ,这种情况只会出现在 lo 和 hi
     * 相邻的时候. 也就是说如果是 mid = lo, 那么此时 mid 大于 key ,导致 hi = mid - 1,结束循环.可知,
     * keys[lo-1]一定是小于 key 的,所以 lo 的值 正好是小于 key 的数量. 如果是 mid = hi, 此时 mid 小于
     * key ,导致lo = mid +1结束循环,可知 lo 正好是小于 key 的数量
     *
     * @param args
     */
    public static void main(String[] args) {

        BinarySearchST binarySearchST = new BinarySearchST();
    }

//    public int rank(Key key) {
//        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
//
//        int lo = 0, hi = n - 1;
//        while (lo <= hi) {
//            int mid = lo + (hi - lo) / 2;
//            compares++;
//            int cmp = key.compareTo(keys[mid]);
//            if (cmp < 0) hi = mid - 1;
//            else if (cmp > 0) lo = mid + 1;
//            else return mid;
//        }
//        return lo;
//    }
}
