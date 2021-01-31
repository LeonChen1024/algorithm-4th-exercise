package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.26 Sorting three numbers. Suppose that the variables a, b, c, and t are all of the same
 * numeric primitive type. Show that the following code puts a, b, and c in ascending order:
 * <code>
 * if(a>b){t=a;a=b;b=t;}
 * if(a>c){t=a;a=c;c=t;}
 * if(b>c){t=b;b=c;c=t;}
 * </code>
 * <p>
 * 对 3 个数进行排序.假设变量 a,b,c 还有 t 都是原始数字类型.证明下面的代码将 a,b,c 按照升序排列
 */
class E1_1_26 {

    private static void test1_1_26(int a, int b, int c) {
        // 与冒泡类似,通过不停比较将最小的排在当前计算位
        int t;
        if (a > b) {
            // 保证a是 a,b中的最小的
            t = a;
            a = b;
            b = t;
        }
        if (a > c) {
            // 保证 a是 a,c 中最小的
            t = a;
            a = c;
            c = t;
        }
        if (b > c) {
            // 保证b 比c小
            t = b;
            b = c;
            c = t;
        }

        StdOut.println("a = " + a + " ,b = " + b + " ,c = " + c);
    }


    /**
     *
     */
    public static void main(String[] args) {
        test1_1_26(3, 2, 4);
    }
}
