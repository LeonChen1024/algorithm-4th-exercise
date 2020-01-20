package edu.princeton.cs.exercise.chapter1_3;

/**
 * 1.3.14 Develop a class ResizingArrayQueueOfStrings that implements the queue abstraction with a
 * fixed-size array, and then extend your implementation to use array resizing to remove the size
 * restriction.
 *
 * <p>开发一个类 ResizingArrayQueueOfStrings 使用了一个定长数组实现了queue的抽象,并且扩展你的实现使用 resizing<br>
 * 来移除大小限制
 *
 * @author LeonChen
 * @since 1/3/20
 */
class e01_03_14 {

  public static void main(String[] args) {
    ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings();
    queue.enqueue("1");
    System.out.println("size= " + queue.size() + " , count= " + queue.count);
    queue.enqueue("2");
    System.out.println("size= " + queue.size() + " , count= " + queue.count);
    queue.enqueue("3");
    System.out.println("size= " + queue.size() + " , count= " + queue.count);
    queue.enqueue("4");
    System.out.println("size= " + queue.size() + " , count= " + queue.count);
    queue.enqueue("5");
    System.out.println("size= " + queue.size() + " , count= " + queue.count);
    queue.enqueue("6");
    System.out.println("size= " + queue.size() + " , count= " + queue.count);
    while (!queue.isEmpty()) {
      System.out.println(queue.dequeue() + "size= " + queue.size() + " , count= " + queue.count);
    }
  }

  static class ResizingArrayQueueOfStrings {

    private String[] queue;
    private int start;
    private int end;
    private int count;

    public ResizingArrayQueueOfStrings() {
      start = 0;
      end = 0;
      count = 2;
      queue = new String[count];
    }

    public void enqueue(String s) {
      if (end == count) {
        resize(count << 1);
      }
      queue[end++] = s;
    }

    public String dequeue() {
      if ((count >> 2) > size()) {
        resize(count >> 1);
      }

      return queue[start++];
    }

    private int size() {
      return end - start;
    }

    private void resize(int size) {
      String[] temp = new String[size];

      int index = 0;
      for (int i = start; i < end; i++) {
        temp[index++] = queue[i];
      }
      queue = temp;
      count = size;
      end = end - start;
      start = 0;
    }

    public boolean isEmpty() {
      return start == end;
    }
  }
}
