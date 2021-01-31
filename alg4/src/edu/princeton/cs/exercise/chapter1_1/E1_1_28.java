package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.28 Remove duplicates. Modify the test client in BinarySearch to remove any duplicate keys
 * in the whitelist after the sort.
 * <p>
 * 去除重复项.修改BinarySearch测试端来删除白名单中重复的键.
 */
class E1_1_28 {


    private static void test1_1_28(int[] a) {
        if (a.length == 0) return;
        int j = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[j] != a[i]) {
                a[++j] = a[i];
            }
        }

        int[] result = new int[j + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = a[i];
            StdOut.println(result[i]);
        }
    }

    public static void main(String[] args) {
        test1_1_28(new int[]{1, 2, 3, 4, 5, 5, 5, 5, 5, 7, 8, 8, 9, 9, 9});
    }
}
