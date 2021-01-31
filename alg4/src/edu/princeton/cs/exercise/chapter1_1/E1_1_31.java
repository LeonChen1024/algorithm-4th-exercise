package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.31 Random connections. Write a program that takes as command-line arguments an integer N
 * and a double value p (between 0 and 1), plots N equally spaced dots of size .05 on the
 * circumference of a circle, and then, with probability p for each pair of points, draws a gray
 * line connecting them.
 *
 * <p>随机连接.编写一段程序,从命令行接受一个整数 N 和 double 值 p(0 到 1 之间)作为参数. 在一个圆上画出大小为 0.05 且间距相等的 N 个点, 然后将每对点按照概率
 * p 用灰线连接.
 */
class E1_1_31 {


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


    public static void main(String[] args) {
        test1_1_31(6, 0.3);
    }
}
