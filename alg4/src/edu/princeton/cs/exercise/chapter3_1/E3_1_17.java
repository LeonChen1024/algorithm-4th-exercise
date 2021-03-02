package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;

/**
 * 3.1.17 Implement the floor() method for BinarySearchST.
 * <p>
 * 给 BinarySearchST 实现 floor() 方法
 *
 * @author LeonChen
 * @since 2/10/20
 */
class E3_1_17 {


    /**
     * 见官方实现,有该 key 则返回改值,没有则向下一位即可,已经是第0 个则没有符合要求的值
     *
     * @param args
     */
    public static void main(String[] args) {

        BinarySearchST binarySearchST = new BinarySearchST();
    }

//    public Key floor(Key key) {
//        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
//        int i = rank(key);
//        if (i < n && key.compareTo(keys[i]) == 0) return keys[i];
//        if (i == 0) return null;
//        else return keys[i - 1];
//    }

}
