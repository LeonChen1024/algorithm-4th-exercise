package edu.princeton.cs.exercise.chapter2_5;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Arrays;

/**
 * 2.5.26 Simple polygon. Given N points in the plane, draw a simple polygon with N
 * points as vertices. Hint : Find the point p with the smallest y coordinate, breaking ties
 * with the smallest x coordinate. Connect the points in increasing order of the polar angle
 * they make with p.
 * <p>
 * 简单的多边形.给定 N 个平面点画出有 N个顶点的多边形.提示:找到 y 坐标最小的点,与 x 坐标最小的点断开.
 * 按照与 p 的极角升序依次连接这些点
 *
 * @author LeonChen
 * @since 1/7/20
 */
class E02_05_26 {

    /**
     * 如下官方解答
     */
    public static void main(String[] args) {
        int N = 8;
        int drawLen = 100;
        E02_05_25.Point2D[] point2DS = new E02_05_25.Point2D[N];
        for (int i = 0; i < N; i++) {
            point2DS[i] = new E02_05_25.Point2D(StdRandom.uniform(drawLen),
                    StdRandom.uniform(drawLen));
        }
        double minY = Integer.MAX_VALUE;
        E02_05_25.Point2D minYPoint = null;

        for (E02_05_25.Point2D point2D : point2DS) {
            if (minY > point2D.y()) {
                minY = point2D.y();
                minYPoint = point2D;
            }
        }

        Arrays.sort(point2DS, minYPoint.polarOrder());
        StdOut.println(Arrays.toString(point2DS));

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.blue);
        StdDraw.setXscale(0, drawLen);
        StdDraw.setYscale(0, drawLen);

        E02_05_25.Point2D cur = minYPoint;
        for (int i = 0; i < point2DS.length; i++) {
            if (point2DS[i] == minYPoint) {
                continue;
            }
            StdDraw.line(cur.x(), cur.y(), point2DS[i].x(), point2DS[i].y());
            cur = point2DS[i];
        }
        StdDraw.line(cur.x(), cur.y(), minYPoint.x(), minYPoint.y());

    }


}
