package edu.princeton.cs.exercise.chapter2_1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.1.16 Certification. Write a check() method that calls sort() for a given array and returns true
 * if sort() puts the array in order and leaves the same set of objects in the array as were there
 * initially, false otherwise. Do not assume that sort() is restricted to move data only with
 * exch(). You may use Arrays.sort() and assume that it is correct.
 *
 * <p>验证.编写一个 check() 方法来对给定的数组调用 sort() 方法,如果sort()对这个有序数组排序后数组仍然保持<br>
 * 和开始的时候一样的对象则返回true,否则返回false.不要假设sort()是使用 exch()来移动数据的.你可以使用<br>
 * Array.sort()并假设它是对的
 *
 * @author LeonChen
 * @since 5/19/20
 */
class E02_01_16 {

  /**
   * 只需要把初始数组里的对象取出来保存起来,和后面的对比即可,这里注意一点,我增加了一个数量的判断,<br>
   * 确保对象的数量也是一致的
   */
  public static void main(String[] args) {
    Comparable[] array = {
      1, new Integer(34), 3, 7, 32, 54, 73, 83, 3, 36, 234, 23, 213, 33, 11, 33, 22
    };
    StdOut.println(check(array));
  }

  private static boolean check(Comparable[] array) {
    Map<Comparable, Integer> map = new HashMap<>();

    // 保存初始的对象
    for (Comparable value : array) {
      int count = 0;

      if (map.containsKey(value)) {
        count = map.get(value);
      }

      count++;
      map.put(value, count);
    }

    Arrays.sort(array);

    // 对比前后是否一致
    for (Comparable value : array) {
      if (map.containsKey(value)) {
        int count = map.get(value);
        count--;

        if (count == 0) {
          map.remove(value);
        } else {
          map.put(value, count);
        }
      } else {
        return false;
      }
    }
    if (map.size() > 0) {
      return false;
    }

    return true;
  }
}
