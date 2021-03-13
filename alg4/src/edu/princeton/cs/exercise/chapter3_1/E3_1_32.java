package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.exercise.util.ArrayGenUtil;

/**
 * 3.1.32 Exercise driver. Write an exercise driver program that uses the methods in our
 * ordered symbol-table API on difficult or pathological cases that might turn up in practical
 * applications. Simple examples include key sequences that are already in order, key
 * sequences in reverse order, key sequences where all keys are the same, and keys consisting
 * of only two distinct values.
 * <p>
 * 实验驱动,编写一个实验驱动程序使用有序符号表 API 处理实际应用中可能出现的复杂或者病态的情况.简单的练习包含了
 * key 序列已经是有序的了,key 序列是倒序的,key 序列全是一样的,以及 key 序列只有两种值
 *
 * @author LeonChen
 * @since 2/27/20
 */
class E3_1_32 {
    private static final String TYPE_ORDERED = "ordered";
    private static final String TYPE_REVERSE_ORDERED = "reverseOrdered";
    private static final String TYPE_SAME_KEY = "sameKey";
    private static final String TYPE_KEYS_2_VALUE = "keys2Value";

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] types = new String[]{TYPE_ORDERED, TYPE_REVERSE_ORDERED, TYPE_SAME_KEY, TYPE_KEYS_2_VALUE};
        for (String type : types) {
            StdOut.println("type = " + type);
            Comparable[] arr = genArrays(type, 10000);
            Stopwatch stopwatch = new Stopwatch();
            BinarySearchST st = new BinarySearchST();
            for (int i = 0; i < arr.length; i++) {
                st.put(arr[i], i);
            }
            StdOut.println("use time " + stopwatch.elapsedTime());
        }
    }

    private static Comparable[] genArrays(String inputType, int arraySize) {
        Comparable[] array;

        switch (inputType) {
            case TYPE_ORDERED:
                array = ArrayGenUtil.genOrderedArr(arraySize);
                break;
            case TYPE_REVERSE_ORDERED:
                array = ArrayGenUtil.genReverseOrderedArr(arraySize);
                break;
            case TYPE_SAME_KEY:
                array = ArrayGenUtil.genArrAllSameKey(arraySize);
                break;
            case TYPE_KEYS_2_VALUE:
                array = ArrayGenUtil.genRandomArr2Values(arraySize);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + inputType);
        }

        return array;
    }


}
