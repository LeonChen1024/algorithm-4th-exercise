package edu.princeton.cs.exercise.util;

public class MathUtil {

    public static double distanceBetweenPoints(double x1, double y1, double x2,
                                               double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static long factorial(int number) {
        long factorial = 1;

        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }

        return factorial;
    }

}
