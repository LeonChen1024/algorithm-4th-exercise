package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.29 Equal keys. Add to BinarySearch a static method rank() that takes a key and a sorted
 * array of int values (some of which may be equal) as arguments and returns the number of
 * elements that are smaller than the key and a similar method count() that returns the number of
 * elements equal to the key. Note : If i and j are the values returned by rank(key, a) and
 * count(key, a) respectively, then a[i..i+j-1] are the values in the array that are equal to key.
 * <p>
 * 相等键.添加一个静态方法 rank() 到 BinarySearch 中,它接收一个 key 和一个有序 int 数组(可能有相等的值) 作为参数并且返回小于 key
 * 的元素数量,还有一个类似的方法count() 返回等于 key 的元素数量.注意: 如果 i 和 j 是rank(key, a)和count(key, a) 的返回值,
 * 那么a[i..i+j-1]是数组中等于 key 的元素
 */
class E1_1_29 {


    private static void test1_1_29(int key, int[] a) {
        StdOut.println(rank29(key, a));
        StdOut.println(count(key, a));
    }

    private static int count(int key, int[] a) {
        int start = rank29(key, a);
        if (start < 0) {
            return -1;
        }
        int i = start;
        int num = 0;
        while (a[i] == key) {
            i++;
            num++;
        }
        return num;
    }

    private static int rank29(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                while (mid > 0 && a[mid] == key) {
                    mid--;
                }
                return mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        test1_1_29(5, new int[]{1, 2, 3, 4, 5, 5, 5, 5, 5, 7, 8, 8, 9, 9, 9});
    }
}
