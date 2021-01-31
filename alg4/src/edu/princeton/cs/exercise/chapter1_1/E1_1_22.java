package edu.princeton.cs.exercise.chapter1_1;


/**
 * 1.1.22 Write a version of BinarySearch that uses the recursive rank() given on page 25 and
 * traces the method calls. Each time the recursive method is called, print the argument values lo
 * and hi, indented by the depth of the recursion. Hint: Add an argument to the recursive method
 * that keeps track of the depth.
 * <p>
 * 编写一个版本的 BinarySearch 使用正文 25 页的递归 rank() 方法并且跟踪方法调用.每次调用递归方法的时候,打印参数 lo 和 hi 的值,
 * 还有递归的深度.提示: 添加一个参数给递归方法来跟踪深度
 */
class E1_1_22 {


    public static int rank(int key, int[] a, int lo, int hi, int deep) {
        if (hi < lo) return -1;
        int mid = lo + (hi - lo) / 2;
        for (int i = 0; i < deep; i++) System.out.print(" ");
        System.out.println("lo: " + lo + " hi: " + hi);
        if (key < a[mid]) return rank(key, a, lo, mid - 1, deep + 1);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi, deep + 1);
        else return mid;
    }


    /**
     *
     */
    public static void main(String[] args) {
        rank(3, new int[]{1, 2, 2, 2, 3, 5, 7, 9, 11, 13, 44, 56}, 0, 12, 1);

    }
}
