package edu.princeton.cs.exercise.chapter2_3;

/**
 * 2.3.15 Nuts and bolts. (G.J.E.Rawlins) You have a mixed pile of N nuts and N bolts and need to
 * quickly find the corresponding pairs of nuts and bolts. Each nut matches exactly one bolt, and
 * each bolt matches exactly one nut. By fitting a nut and bolt together, you can see which is
 * bigger, but it is not possible to directly compare two nuts or two bolts. Give an efficient
 * method for solving the problem.
 *
 * <p>螺母和螺栓. (G.J.E.Rawlins)你有N个螺母和N个螺栓需要快速找到对应的螺母螺栓对.每个螺母正好匹配一个螺栓,<br>
 * 并且每个螺栓正好匹配一个螺母.通过将一个螺母和螺栓配在一起,你可以看到哪个更大,但是不能直接对比两个螺母或者<br>
 * 两个螺栓.给出一个高效的方法来解决这个问题.
 *
 * @author LeonChen
 * @since 8/13/20
 */
class E02_03_15 {

  /**
   * 对快速排序做一个改造即可,因为螺母和螺栓之间是一一对应的,所以对应螺母和螺栓在数组中的位置排序后也是一样的<br>
   * 所以两个数组之间互相作为分割点取值排序即可.
   */
  public static void main(String[] args) {

    // 螺母和螺栓的size
    Integer nuts[] = {4, 6, 7, 5, 2, 1, 3, 8};
    Integer bolts[] = {1, 4, 5, 2, 3, 8, 6, 7};

    matchPairs(nuts, bolts, 0, 7);

    System.out.println("Matched nuts and bolts are : ");
    printArray(nuts);
    printArray(bolts);
  }

  private static void printArray(Integer[] arr) {
    for (int a : arr) {
      System.out.print(a + " ");
    }
    System.out.print("\n");
  }

  private static void matchPairs(Integer[] nuts, Integer[] bolts, int low, int high) {
    if (low < high) {
      int pivot = partition(nuts, low, high, bolts[high]);

      // 因为两个数组一一对应,所以这里返回的值也会是 pivot
      partition(bolts, low, high, nuts[pivot]);

      matchPairs(nuts, bolts, low, pivot - 1);
      matchPairs(nuts, bolts, pivot + 1, high);
    }
  }

  /**
   * 使用 pivot 来分割 arr , 最后使得对应 pivot 的 arr值的位置在数组的位置左侧全是小于它的值,右侧全是大于他的值.<br>
   * 并返回这个位置.
   *
   * @param arr
   * @param low
   * @param high
   * @param pivot
   * @return
   */
  private static int partition(Integer[] arr, int low, int high, Integer pivot) {
    int i = low;
    Integer temp1, temp2;
    for (int j = low; j < high; j++) {
      if (arr[j] < pivot) {
        temp1 = arr[i];
        arr[i] = arr[j];
        arr[j] = temp1;
        i++;
      } else if (arr[j] == pivot) {
        // 将相等的值先放到最后
        temp1 = arr[j];
        arr[j] = arr[high];
        arr[high] = temp1;
        j--;
      }
    }
    // 将相等的值从最后取出放到分割点
    temp2 = arr[i];
    arr[i] = arr[high];
    arr[high] = temp2;

    return i;
  }
}
