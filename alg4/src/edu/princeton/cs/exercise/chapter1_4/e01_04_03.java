package edu.princeton.cs.exercise.chapter1_4;

import java.awt.*;
import java.util.LinkedList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

/**
 * 1.4.3 Modify DoublingTest to use StdDraw to produce plots like the standard and log-log plots in
 * the text, rescaling as necessary so that the plot always fills a substantial portion of the
 * window.
 *
 * <p>修改 DoublingTest 使用 StdDraw 来生成类似文中的标准和对数图形点,根据需要重新缩放使得图案始终填充窗口中的主体.
 *
 * @author LeonChen
 * @since 3/3/20
 */
class e01_04_03 {

  public static void main(String[] args) {}

  public static class DoublingTest {
    private static final int MAXIMUM_INTEGER = 1000000;

    private static int xMaxScale;
    private static int yMaxScale;
    private static LinkedList<Point2D> standardPoints;
    private static LinkedList<Point2D> logPoints;

    // This class should not be instantiated.
    private DoublingTest() {}

    /**
     * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em> random 6-digit
     * integers.
     *
     * @param n the number of integers
     * @return amount of time (in seconds) to call {@code ThreeSum.count()} with <em>n</em> random
     *     6-digit integers
     */
    public static double timeTrial(int n) {
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
      }
      Stopwatch timer = new Stopwatch();
      ThreeSum.count(a);
      return timer.elapsedTime();
    }

    /**
     * Prints table of running times to call {@code ThreeSum.count()} for arrays of size 250, 500,
     * 1000, 2000, and so forth.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
      xMaxScale = 100;
      yMaxScale = 10;
      initCanvas(xMaxScale, yMaxScale);
      standardPoints = new LinkedList<>();
      logPoints = new LinkedList<>();

      Point2D prevStandard = null;
      Point2D prevLog = null;
      for (int n = 250; true; n += n) {
        double time = timeTrial(n);

        rescaleIfNecessary(n, time);
        Point2D standardP = new Point2D(n, time);
        Point2D logP = new Point2D(Math.log(n), Math.log(time));

        standardPoints.push(standardP);
        logPoints.push(logP);

        drawPointAndLine(prevStandard, standardP, true);
        drawPointAndLine(prevLog, logP, false);
        prevStandard = standardP;
        prevLog = logP;
      }
    }

    private static void rescaleIfNecessary(int n, double time) {
      if (n > xMaxScale || time > yMaxScale) {
        if (n > xMaxScale) {
          xMaxScale = xMaxScale * 2;
        }
        if (time > yMaxScale) {
          yMaxScale = yMaxScale * 2;
        }
        reDrawCanvas();
      }
    }

    private static void drawPointAndLine(Point2D prePoint2D, Point2D point2D, boolean isStandard) {

      if (isStandard) {
        StdDraw.setPenColor(Color.BLUE);
      } else {
        StdDraw.setPenColor(Color.red);
      }
      if (prePoint2D != null) {
        StdDraw.line(prePoint2D.x(), prePoint2D.y(), point2D.x(), point2D.y());
      }
      StdDraw.point(point2D.x(), point2D.y());
    }

    private static void reDrawCanvas() {
      StdDraw.clear();

      StdDraw.setXscale(0, xMaxScale);
      StdDraw.setYscale(0, yMaxScale);

      Point2D prev = null;

      for (Point2D point2D : standardPoints) {
        drawPointAndLine(prev, point2D, true);
        prev = point2D;
      }

      prev = null;
      for (Point2D point2D : logPoints) {
        drawPointAndLine(prev, point2D, false);
        prev = point2D;
      }
    }

    private static void initCanvas(int xMax, int yMax) {
      StdDraw.setCanvasSize(500, 500);
      StdDraw.setPenRadius(0.005);
      StdDraw.setXscale(0, xMax);
      StdDraw.setYscale(0, yMax);
    }
  }
}
