package edu.princeton.cs.exercise.chapter2_1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.38 Various types of items. Write a client that generates arrays of items of various types
 * with random key values, including the following:<br>
 * ■ String key (at least ten characters), one double value<br>
 * ■ double key, ten String values (all at least ten characters)<br>
 * ■ int key, one int[20] value<br>
 * Develop and test hypotheses about the effect of such input on the performance of the algorithms
 * in this section.
 *
 * <p>多种类型的项.编写一个客户端生成多种类型的带有随机键值的项,包含下面这些:<br>
 * - 字符串键(最少10个字符).1个double的值<br>
 * - double的键,十个字符串的值(每个都至少长10个字符)<br>
 * - int 键 , 一个 int[20]的值<br>
 * 开发并测试本章中有关这些输入对算法性能影响的假说.
 *
 * @author LeonChen
 * @since 6/3/20
 */
class E02_01_38 {

  private static final String INSERTION = "INSERTION";
  private static final String SELECTION = "SELECTION";
  private static final String SHELL = "SHELL";

  private static final String ARR_TYPE_KEY_STRING = "ARR_TYPE_KEY_STRING";
  private static final String ARR_TYPE_KEY_DOUBLE = "ARR_TYPE_KEY_DOUBLE";
  private static final String ARR_TYPE_KEY_INT = "ARR_TYPE_KEY_INT";

  /**
   * <code>
   * when arr type is ARR_TYPE_KEY_STRING when sort is SELECTION , used time is 0.8266
   * when arr type is ARR_TYPE_KEY_STRING when sort is INSERTION , used time is 0.4478
   * when arr type is ARR_TYPE_KEY_STRING when sort is SHELL , used time is 0.017599999999999998
   *
   * when arr type is ARR_TYPE_KEY_DOUBLE when sort is SELECTION , used time is 0.819
   * when arr type is ARR_TYPE_KEY_DOUBLE when sort is INSERTION , used time is 0.48980000000000007
   * when arr type is ARR_TYPE_KEY_DOUBLE when sort is SHELL , used time is 0.015799999999999998
   *
   * when arr type is ARR_TYPE_KEY_INT when sort is SELECTION , used time is 0.881
   * when arr type is ARR_TYPE_KEY_INT when sort is INSERTION , used time is 0.48919999999999997
   * when arr type is ARR_TYPE_KEY_INT when sort is SHELL , used time is 0.012
   *
   * </code> 选择排序的耗时与输入值的内容无关。对于插入排序，除了随机数之外部分有序的情况下，<br>
   * 插入排序的速度会比选择排序快. 希尔排序在某种程度上有序或者是顺序错乱不大情况上会比插入排序慢,<br>
   * 其他情况速度更快。
   *
   * @param args
   */
  public static void main(String[] args) {

    String[] types = new String[] {SELECTION, INSERTION, SHELL};

    String[] mapTypes = new String[] {ARR_TYPE_KEY_STRING, ARR_TYPE_KEY_DOUBLE, ARR_TYPE_KEY_INT};

    for (String arrType : mapTypes) {
      for (String sortType : types) {
        timeCost(arrType, sortType);
      }
      StdOut.println();
    }
  }

  private static void timeCost(String arrType, String sortType) {
    double usedTime = 0;

    int N = 5000;
    int x = 0;
    while (x < 5) {
      Map[] a;
      switch (arrType) {
        case ARR_TYPE_KEY_STRING:
          a = getKeyStringMap(N);
          break;
        case ARR_TYPE_KEY_DOUBLE:
          a = getKeyDoubleMap(N);
          break;
        case ARR_TYPE_KEY_INT:
          a = getKeyIntMap(N);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + arrType);
      }

      Stopwatch stopwatch = new Stopwatch();
      switch (sortType) {
        case INSERTION:
          insertionSort(a);
          break;
        case SELECTION:
          selectionSort(a);
          break;
        case SHELL:
          shellSort(a);
      }
      usedTime += stopwatch.elapsedTime();
      x++;
    }
    StdOut.println(
        "when arr type is "
            + arrType
            + " when sort is "
            + sortType
            + " , used time is "
            + usedTime / x);
  }

  private static void shellSort(Map[] a) {
    int n = a.length;

    // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
    int h = 1;
    while (h < n / 3) h = 3 * h + 1;

    while (h >= 1) {
      // h-sort the array
      for (int i = h; i < n; i++) {
        for (int j = i;
            j >= h
                && less(
                    (Comparable) a[j].keySet().toArray()[0],
                    (Comparable) a[j - h].keySet().toArray()[0]);
            j -= h) {
          exch(a, j, j - h);
        }
      }
      h /= 3;
    }
  }

  private static void selectionSort(Map[] a) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      int min = i;
      for (int j = i + 1; j < n; j++) {
        if (less(
            (Comparable) a[j].keySet().toArray()[0], (Comparable) a[min].keySet().toArray()[0]))
          min = j;
      }
      exch(a, i, min);
    }
  }

  private static void insertionSort(Map[] a) {
    int n = a.length;
    for (int i = 1; i < n; i++) {
      for (int j = i;
          j > 0
              && less(
                  (Comparable) a[j].keySet().toArray()[0],
                  (Comparable) a[j - 1].keySet().toArray()[0]);
          j--) {
        exch(a, j, j - 1);
      }
    }
  }

  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  // is v < w ?
  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static Map[] getKeyIntMap(int n) {
    Map<Integer, Integer[]>[] maps = new HashMap[n];
    for (int i = 0; i < n; i++) {
      maps[i] = new HashMap<>();
      int key = StdRandom.uniform(n);
      Integer[] values = new Integer[20];
      for (int k = 0; k < 20; k++) {
        int num = StdRandom.uniform(n);
        values[k] = num;
      }

      maps[i].put(key, values);
    }

    return maps;
  }

  private static Map[] getKeyDoubleMap(int n) {
    Map<Double, String[]>[] maps = new HashMap[n];
    for (int i = 0; i < n; i++) {
      maps[i] = new HashMap<>();
      double key = StdRandom.uniform();

      char l = 'A';
      char h = 'z';
      String[] strings = new String[10];
      for (int k = 0; k < 10; k++) {
        StringBuilder value = new StringBuilder();

        for (int j = 0; j < 10; j++) {
          char a = (char) StdRandom.uniform((int) l, (int) h);
          value.append(a);
        }
        strings[k] = value.toString();
      }

      maps[i].put(key, strings);
    }

    return maps;
  }

  private static Map[] getKeyStringMap(int n) {
    Map<String, Double>[] maps = new HashMap[n];
    for (int i = 0; i < n; i++) {
      maps[i] = new HashMap<>();
      char l = 'A';
      char h = 'z';
      StringBuilder key = new StringBuilder();

      for (int j = 0; j < 10; j++) {
        char a = (char) StdRandom.uniform((int) l, (int) h);
        key.append(a);
      }
      double value = StdRandom.uniform();
      maps[i].put(key.toString(), value);
    }

    return maps;
  }

  private static Comparable[] get5UnsortArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) {
      a[i] = i;
    }
    HashSet usedSet = new HashSet();

    while (usedSet.size() < (n * 0.05)) {
      int src = StdRandom.uniform(n);
      int des = StdRandom.uniform(n);
      Comparable temp = a[src];
      a[src] = a[des];
      a[des] = temp;
      usedSet.add(src);
      usedSet.add(des);
    }
    return a;
  }
}
