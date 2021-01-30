package edu.princeton.cs.exercise;

import edu.princeton.cs.algs4.*;

/**
 * 第一章习题
 *
 * @author LeonChen
 * @since 11/1/19
 */
public class chapter1 {

    public static void main(String[] args) {
//        test1_1_1();
        //    test1_1_2();
        //    test1_1_3();
        //    test1_1_4();
        //    test1_1_5();
        //    test1_1_6();
        //    test1_1_7();
        //    test1_1_8();
        //    test1_1_9();
        //    test1_1_10();
        //    test1_1_11();
        //    test1_1_12();
        //    test1_1_13();
        //    test1_1_14(9);
        //    test1_1_15(new int[] {2, 2, 2, 3, 4, 1, 1}, 5);
        //    test1_1_16();
        //    test1_1_17();
        //    test1_1_18();
        //    test1_1_19();
        //    test1_1_20();
        //    test1_1_21();
        //    test1_1_22();

        //    test1_1_24();
        //    test1_1_25();
        //    test1_1_26(3, 2, 4);
        //    test1_1_27();
        //    test1_1_28(new int[] {1, 2, 3, 4, 5, 5, 5, 5, 5, 7, 8, 8, 9, 9, 9});
        //    test1_1_29(5, new int[] {1, 2, 3, 4, 5, 5, 5, 5, 5, 7, 8, 8, 9, 9, 9});
        //    test1_1_30(new int[] {1, 3, 5, 6, 9});
        //    test1_1_31(6, 0.3);
        //    test1_1_32(6, 0.3, 2.4);
        //    test1_1_33();
        //    test1_1_34();
        //    test1_1_35();
        //    test1_1_36(8, 800);
        //    test1_1_37(8, 800);
        //    test1_1_38();
        test1_1_39(100);
    }

    /**
     * 1.1.39 Random matches. Write a BinarySearch client that takes an int value T as command-line
     * argument and runs T trials of the following experiment for N = 10^3, 10^4, 10^5, and 10^6:
     * generate two arrays of N randomly generated positive six-digit int values, and find the number
     * of values that appear in both arrays. Print a table giving the average value of this quantity
     * over the T trials for each value of N.
     *
     * <p>随机匹配.编写一个二分搜索来接收一个整数T ,并运行 T 次试验 ,其中 N = 10^3, 10^4, 10^5, and 10^6 .
     * 生成两个大小为N大数组,其中填充六位数为上限大随机数,并且找到在两个数组中都出现的值.打印出这个数量的平均值对应 T次试验和每一个N的值.
     */
    private static void test1_1_39(int T) {
        int[] Ns =
                new int[]{
                        (int) Math.pow(10, 3), (int) Math.pow(10, 4), (int) Math.pow(10, 5), (int) Math.pow(10, 6)
                };

        for (int N : Ns) {
            int[] as = new int[N];
            int[] bs = new int[N];
            int count = 0;
            for (int j = 0; j < T; j++) {
                //

                // 随机生成两个N数量的数组
                for (int i = 0; i < N; i++) {
                    as[i] = StdRandom.uniform(1000000);
                    bs[i] = StdRandom.uniform(1000000);
                }

                for (int a : as) {

                    if (BinarySearch.indexOf(bs, a) != -1) {
                        count++;
                    }
                }
            }
            StdOut.println("T = " + T + " , N = " + N + " , sameCount = " + count);
        }
    }

    /**
     * 1.1.38 Binary search versus brute-force search. Write a program BruteForceSearch that uses the
     * brute-force search method given on page 48 and compare its running time on your computer with
     * that of BinarySearch for largeW.txt and largeT.txt.
     *
     * <p>二分搜索VS 暴力循环搜索 . 编写一个暴力循环搜索程序使用p48上的暴力循环方式来和 二分搜索 的方式进行对比,使用 largeW.txt and largeT.txt.
     */
    private static void test1_1_38() {
        //    BinarySearch 类里面已经实现了这两个时间的对比,按照 tod o 的提示进行打开注释即可.

    }

    /**
     * 1.1.37 Bad shuffling. Suppose that you choose a random integer between 0 and N-1 in our
     * shuffling code instead of one between i and N-1. Show that the resulting order is not equally
     * likely to be one of the N! possibilities. Run the test of the previous exercise for this
     * version.
     *
     * <p>差的乱序,假设你在shuffling 代码中选择一个随机的 0到 N-1 的数字来取代 i 到 N-1.展示导致的结果顺序不会与N! 的可能性类似. 使用前一个联系的代码来测试这个版本
     */
    private static void test1_1_37(int M, int N) {
        // 使用 badShuffling 替换shuffling 即可
        int[] a = new int[M];

        int[][] result = new int[M][M];

        // 打乱N次测试
        for (int i = 0; i < N; i++) {
            // 初始化
            for (int k = 0; k < M; k++) {
                a[k] = k;
            }

            StdRandom.badShuffle(a);
            for (int j = 0; j < a.length; j++) {
                result[a[j]][j]++;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                StdOut.print(result[i][j] + "|");
            }
            StdOut.println("");
        }
    }

    /**
     * 1.1.36 Empirical shuffle check. Run computational experiments to check that our shuffling code
     * on page 32 works as advertised. Write a program ShuffleTest that takes command-line arguments M
     * and N, d oes N shuffles of an array of size M that is initialized with a[i] = i before each
     * shuffle, and prints an M-by-M table such that row i gives the number of times i wound up in
     * position j for all j. All entries in the array should be close to N/M.
     *
     * <p>乱序检查。通过实验检查32页中的乱序代码是否能够产生预期的效果。编写一个程序ShuffleTest, 接受命令行参数 M 和 N，将大小为M 的数组打乱 N
     * 次且在每次打乱之前都将数组重新初始化为a[i] = i。打印一个 M×M 的表格，对于所有的列 j，行 i 表示的是 i 在打乱后落到 j 的位置的次数。数组中的所有元素的值都应该接近于
     * N/M。
     */
    private static void test1_1_36(int M, int N) {
        int[] a = new int[M];

        int[][] result = new int[M][M];

        // 打乱N次测试
        for (int i = 0; i < N; i++) {
            // 初始化
            for (int k = 0; k < M; k++) {
                a[k] = k;
            }

            StdRandom.shuffle(a);
            for (int j = 0; j < a.length; j++) {
                result[a[j]][j]++;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                StdOut.print(result[i][j] + "|");
            }
            StdOut.println("");
        }
    }

    /**
     * 1.1.35 Dice simulation. The following code computes the exact probability distribution for the
     * sum of two dice: <code>
     * int SIDES = 6;
     * double[] dist = new double[2 * SIDES + 1];
     * for (int i = 1; i <= SIDES; i++) for (int j = 1; j <= SIDES; j++) dist[i + j] += 1.0;
     * for (int k = 2; k <= 2 * SIDES; k++) dist[k] /= 36.0;
     * </code> The value dist[i] is the probability that the dice sum to k. Run experiments to
     * validate this calculation simulating N dice throws, keeping track of the frequencies of
     * occurrence of each value when you compute the sum of two random integers between 1 and 6. How
     * large does N have to be before your empirical results match the exact results to three decimal
     * places?
     *
     * <p>色子模拟.以下代码可以模拟两个色子值和的分布. dist[i] 是两个色子和为k的概率.运行这个程序来证实这个试验模拟N次投掷,
     * 当你在计算两个随机整数和的时候追踪每个值出现的频率.N要多大才能保证经验结果和准确结果保持三位小数的相同.
     */
    private static void test1_1_35() {

        double[] dist = standardProbability();

        // 模拟随机投掷
        double[] rhitNum = new double[13];
        int n = 1;

        while (!compareDist(dist, rhitNum, n) && n++ > 0) {

            rhitNum = randomHitNum(rhitNum);
        }

        for (int i = 0; i < 13; i++) {
            StdOut.println("random " + i + " " + rhitNum[i] / n);
        }
        StdOut.println(n);
    }

    /**
     * 对比两者概率是否在3位精度要求内
     *
     * @param dist
     * @param rhitNum
     * @param n
     * @return
     */
    private static boolean compareDist(double[] dist, double[] rhitNum, int n) {

        for (int i = 0; i < 13; i++) {
            if (Math.abs((rhitNum[i] / n) - dist[i]) >= 0.001) {
                //        StdOut.println();
                return false;
            }
        }

        return true;
    }

    /**
     * 随机投掷两次色子和次数 .
     *
     * <p>注意随机时候应该用随机到的数字+1,而不是把随机的上限+1
     */
    private static double[] randomHitNum(double[] rhitNum) {
        rhitNum[(StdRandom.uniform(6) + 1) + (StdRandom.uniform(6) + 1)] += 1.0;
        return rhitNum;
    }

    /**
     * 投掷两次色子和的标准概率.
     */
    private static double[] standardProbability() {

        int SIDES = 6; // 一个色子总共有6种可能
        double[] dist = new double[2 * SIDES + 1]; // 最大的可能是12,所以需要13个位置.
        for (int i = 1; i <= SIDES; i++)
            for (int j = 1; j <= SIDES; j++) dist[i + j] += 1.0; // 计算每种和出现的次数
        for (int k = 2; k <= 2 * SIDES; k++) {
            dist[k] /= 36.0; // 计算在所有可能中的概率
            StdOut.println(k + " probability= " + dist[k]);
        }

        return dist;
    }

    /**
     * 1.1.34 Filtering. Which of the following require saving all the values from standard input (in
     * an array, say), and which could be implemented as a filter using only a fixed number of
     * variables and arrays of fixed size (not dependent on N)? For each, the input comes from
     * standard input and consists of N real numbers between 0 and 1.<br>
     * ■ Print the maximum and minimum numbers. <br>
     * ■ Print the median of the numbers.<br>
     * ■ Print the k th smallest value, for k less than 100. <br>
     * ■ Print the sum of the squares of the numbers. <br>
     * ■ Print the average of the N numbers. <br>
     * ■ Print the percentage of numbers greater than the average.<br>
     * ■ Print the N numbers in increasing order.<br>
     * ■ Print the N numbers in random order.<br>
     *
     * <p>以下哪些需要从输入中保存值,比如保存到数组.哪些可以使用固定数量的变量和固定大小的数组(与N无关)? 对于每一项,输入都是N 个真实数字大小在0~1之间. ■ Print the
     * 在这里我们使用 all 代表要保存所有的值, fix 代表可以使用固定值.
     *
     * <p>■ 打印最大的数字和最小的数字<br>
     * fix,根据不断的读取输入更新最大和最小的数字即可,其他的数字可以忽略.
     *
     * <p>■ 打印中间值.<br>
     * all,必须要保存全部的值才能计算,只保留部分可能会出现将中间值提前清除的可能
     *
     * <p>■ 打印第k个最小值,k小于100. <br>
     * fix,可以保存最小的100个输入即可
     *
     * <p>■ 打印出所有数字的平方和<br>
     * fix,直接累加即可
     *
     * <p>■ 打印出N个数的平均值. <br>
     * fix,按照输入计算即可,无需保留所有.
     *
     * <p>■ 打印出大于平均数的数字数量的百分比.<br>
     * all,只有在全部遍历完之后才能确定平均数是多少,才有办法将之前的数字进行分类.
     *
     * <p>■ 使用升序打印N个数字.<br>
     * all,只有全部遍历后才有办法排序打印
     *
     * <p>■ 用随机顺序打印N个数字.<br>
     * all,全部保存后才能做到真随机.
     */
    private static void test1_1_34() {
    }

    /**
     * 1.1.33 Matrix library. Write a library Matrix that implements the following API: <br>
     * <code>
     * public class Matrix
     * static double dot(double[] x, double[] y) vector dot product
     * static double[][] mult(double[][] a, double[][] b) matrix-matrix product
     * static double[][] transpose(double[][] a) transpose
     * static double[] mult(double[][] a, double[] x) matrix-vector product
     * static double[] mult(double[] y, double[][] a) vector-matrix product
     * </code> Develop a test client that reads values from standard input and tests all the methods.
     *
     * <p>编写一个Matrix 库,实现以下API <br>
     * <code>
     * public class Matrix
     * static double dot(double[] x, double[] y) 向量点乘
     * static double[][] mult(double[][] a, double[][] b) 矩阵乘法
     * static double[][] transpose(double[][] a) 转置矩阵
     * static double[] mult(double[][] a, double[] x) 矩阵和向量积
     * static double[] mult(double[] y, double[][] a) 向量和矩阵积
     * </code>
     */
    private static void test1_1_33() {

        StdOut.println(Matrix.dot(new double[]{1, 2}, new double[]{2, 3}));
        double[][] a = Matrix.mult(new double[][]{{1, 2}, {1, 2}}, new double[][]{{2, 2}, {2, 2}});
        double[][] b = Matrix.transpose(new double[][]{{1, 2}, {1, 2}});
        double[][] c = Matrix.mult(new double[][]{{1, 2}, {1, 2}}, new double[]{2, 2});
        double[][] d = Matrix.mult(new double[]{1, 2}, new double[][]{{2, 2}, {2, 2}});
    }

    /**
     * 1.1.32 Histogram. Suppose that the standard input stream is a sequence of double values. Write
     * a program that takes an integer N and two double values l and r from the command line and uses
     * StdDraw to plot a histogram of the count of the numbers in the standard input stream that fall
     * in each of the N intervals defined by dividing (l , r) into N equal-sized intervals.
     *
     * <p>写一个程序接收一个int N 一个 double l 一个 double r ,N是直方图中条的个数,l 和 r 是直方图的左右边界,
     * 每条都是平分这个边界的.接收一定数量的输入,画出这些值落在每条直方的数量的直方图.
     *
     * @param N
     * @param l
     * @param r
     */
    private static void test1_1_32(int N, double l, double r) {

        double totalWidth = r - l;
        double perWidth = totalWidth / N;
        // 所有左侧点的位置,最后一个特殊处理,需要一个虚拟的N+1左侧点,实际是N 的右侧点
        double[] lows = new double[N + 1];
        int[] ranges = new int[N];
        // 由于第一个不需要加上每个宽度,所以需要从 -perWidth开始.
        double curLeft = -perWidth;
        // 计算左侧点
        for (int i = 0; i < lows.length; i++) {
            lows[i] = curLeft + perWidth;
            curLeft = lows[i];
        }
        int inputNum = 0;
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            // 以 # 作为结束输入的符号
            if (input.equals("#")) {
                break;
            }
            inputNum++;
            double num = Double.parseDouble(input);
            // 计算分布情况
            for (int i = 0; i < lows.length; i++) {
                if (num > lows[i] && num < lows[i + 1]) {
                    ranges[i]++;
                    break;
                }
            }
        }

        StdDraw.setXscale(l, r);
        StdDraw.setYscale(0, inputNum);
        for (int i = 0; i < N; i++) {
            StdDraw.filledRectangle(lows[i] + perWidth / 2, ranges[i] / 2, perWidth / 2, ranges[i] / 2);
        }
    }

    /**
     * 1.1.31 Random connections. Write a program that takes as command-line arguments an integer N
     * and a double value p (between 0 and 1), plots N equally spaced dots of size .05 on the
     * circumference of a circle, and then, with probability p for each pair of points, draws a gray
     * line connecting them.
     *
     * <p>随机连接.编写一段程序,从命令行接受一个整数 N 和 double 值 p(0 到 1 之间)作为参数. 在一个圆上画出大小为 0.05 且间距相等的 N 个点, 然后将每对点按照概率
     * p 用灰线连接.
     *
     * @param N
     * @param p
     */
    private static void test1_1_31(int N, double p) {
        // 可以使用平分圆的角度来看,每个角是 360/N . 要注意 Math.cos 的参数是弧度, 所以是 (degress / 360) * 2*pi.
        //    也可以直接用弧度api  Math.toRadians(x);

        double angle = 360.0 / N;
        StdDraw.circle(0.5, 0.5, 0.5);

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            points[i] =
                    //          new Point(
                    //              0.5 + 0.5 * Math.cos(angle * i * Math.PI / 180),
                    //              0.5 + 0.5 * Math.sin(angle * i * Math.PI / 180));
                    new Point(
                            0.5 + 0.5 * Math.cos(Math.toRadians(angle * i)),
                            0.5 + 0.5 * Math.sin(Math.toRadians(angle * i)));
            StdDraw.point(points[i].x, points[i].y);
        }

        StdDraw.setPenColor(StdDraw.GRAY);

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (StdRandom.bernoulli(p)) {
                    StdDraw.line(points[i].x, points[i].y, points[j].x, points[j].y);
                }
            }
        }
    }

    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }

    /**
     * 1.1.30 Array exercise. Write a code fragment that creates an N-by-N boolean array a[][] such
     * that a[i][j] is true if i and j are relatively prime (have no common factors), and false
     * otherwise.
     *
     * <p>没有公因子为true,也就是互质,否则为false
     *
     * @param ints
     */
    private static void test1_1_30(int[] ints) {
        // 可以复用之前的求最大公约数的方法来计算,当最大公约数是1的时候,就是互质
        int N = ints.length;
        boolean[][] result = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StdOut.println("");
            for (int j = 0; j < N; j++) {
                if (gcd(ints[i], ints[j]) == 1) {
                    result[i][j] = true;
                } else {
                    result[i][j] = false;
                }
                StdOut.print(result[i][j] + "  ,  ");
            }
        }
    }

    /**
     * 1.1.29 Equal keys. Add to BinarySearch a static method rank() that takes a key and a sorted
     * array of int values (some of which may be equal) as arguments and returns the number of
     * elements that are smaller than the key and a similar method count() that returns the number of
     * elements equal to the key. Note : If i and j are the values returned by rank(key, a) and
     * count(key, a) respectively, then a[i..i+j-1] are the values in the array that are equal to key.
     *
     * @param key
     * @param a
     */
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

    /**
     * 1.1.28 Remove duplicates. Modify the test client in BinarySearch to remove any duplicate keys
     * in the whitelist after the sort. 去重.删除白名单中重复的键.
     */
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

    /**
     * 1.1.27 Binomial distribution. Estimate the number of recursive calls that would be used by the
     * code <code>
     * public static double binomial(int N, int k, double p) {
     * if ((N == 0) || (k < 0)) return 1.0;
     * return (1.0 - p) * binomial(N - 1, k) + p * binomial(N - 1, k - 1);
     * }
     * </code> to compute binomial(100, 50). Develop a better implementation that is based on saving
     * computed values in an array.
     */
    private static void test1_1_27() {
        //    binomial(100, 50, 0.5);
        double result = binomial1(100, 50, 0.5, new double[100 + 1][50 + 1]);
        StdOut.println("calls = " + recursiveTime + " , result = " + result);
    }

    static int recursiveTime = 0;

    /**
     * 二项分布
     *
     * <p>其中当N次中正好发生k次的时候,概率为 C_n^k * p^k * (1-p)^(n-k)
     *
     * <p>由于每次抽取的结果并不会影响后面的抽取,所以这些事件是独立事件. P(A*B) = P(A) * P(B)
     *
     * <p>C_n^k 为组合,代表从n个中抽出k个,对顺序不敏感.
     *
     * @param N 实验次数
     * @param k 中标次数
     * @param p 中标概率
     * @return
     */
    public static double binomial(int N, int k, double p) {
        recursiveTime++;
        if ((N == 0) || (k < 0)) return 1.0;
        return (1.0 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);
    }

    /**
     * 空间换时间
     *
     * @param N
     * @param k
     * @param p
     * @return
     */
    public static double binomial1(int N, int k, double p, double[][] s) {
        recursiveTime++;
        if ((N == 0) || (k < 0)) return 1.0;
        if (s[N][k] == 0.0) {
            s[N][k] = (1.0 - p) * binomial1(N - 1, k, p, s) + p * binomial1(N - 1, k - 1, p, s);
        }
        return s[N][k];
    }

    /**
     * 1.1.26 Sorting three numbers. Suppose that the variables a, b, c, and t are all of the same
     * numeric primitive type. Show that the following code puts a, b, and c in ascending order:
     * <code>
     * if(a>b){t=a;a=b;b=t;}
     * if(a>c){t=a;a=c;c=t;}
     * if(b>c){t=b;b=c;c=t;}
     * </code>
     */
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
     * 1.1.25 Use mathematical induction to prove that Euclid’s algorithm computes the greatest common
     * divisor of any pair of nonnegative integers p and q.
     */
    private static void test1_1_25() {
        // a可以表示成a = kb + r（a，b，k，r皆为正整数，且r<b），则r = a mod b
        // 假设d是a,b的一个公约数，记作d|a,d|b，即a和b都可以被d整除。
        // 而r = a - kb，两边同时除以d，r/d=a/d-kb/d=m，由等式右边可知m为整数，因此d|r
        // 因此d也是b,a mod b的公约数
        // 假设d是b,a mod b的公约数, 则d|b,d|(a-k*b),k是一个整数。
        // 进而d|a.因此d也是a,b的公约数
        // 因此(a,b)和(b,a mod b)的公约数是一样的，a mod b 的最大公约数也就是 a,b 的最大公约数，得证。

    }


}
