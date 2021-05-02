package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;

/**
 * 3.1.16 Implement the delete() method for BinarySearchST.
 * <p>
 * 为 BinarySearchST实现 delete()方法
 *
 * @author LeonChen
 * @since 2/10/21
 */
class E3_1_16 {


    /**
     * 见官方实现,总的来说就是找到该元素,然后将后面的元素前移
     *
     * @param args
     */
    public static void main(String[] args) {

        BinarySearchST binarySearchST = new BinarySearchST();
    }
//    public void delete(Key key) {
//        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
//        if (isEmpty()) return;
//
//        // compute rank
//        int i = rank(key);
//
//        // key not in table
//        if (i == n || keys[i].compareTo(key) != 0) {
//            return;
//        }
//
//        for (int j = i; j < n - 1; j++) {
//            keys[j] = keys[j + 1];
//            vals[j] = vals[j + 1];
//        }
//
//        n--;
//        keys[n] = null;  // to avoid loitering
//        vals[n] = null;
//
//        // resize if 1/4 full
//        if (n > 0 && n == keys.length / 4) resize(keys.length / 2);
//
//        assert check();
//    }


}
