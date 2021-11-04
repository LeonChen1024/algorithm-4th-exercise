package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 3.5.28 UniQueue. Create a data type that is a queue, except that an element may only
 * be inserted the queue once. Use an existence symbol table to keep track of all elements
 * that have ever been inserted and ignore requests to re-insert such items.
 * <p>
 * UniQueue.创建一个数据类型,它是一个队列,但是一个元素只能被插入到队列中一次.使用已有的符号表来追踪所有插入
 * 过得元素并且忽略重插入的项
 *
 * @author LeonChen
 * @since 9/14/21
 */
class E3_5_28 {

    /**
     * @formatter:off
     * 使用一个初始 key 当做中间值,加在前面就最小值减偏移,加在后面就最小值加偏移
     * @formatter:on
     */
    public static void main(String[] args) {
        UniQueue<Integer> uniQueue = new UniQueue<>();

        StdOut.println("IsEmpty: " + uniQueue.isEmpty() + " Expected: true");

        uniQueue.enqueue(0);
        uniQueue.enqueue(1);
        uniQueue.enqueue(2);
        uniQueue.enqueue(4);
        uniQueue.enqueue(8);

        StdOut.println("Size: " + uniQueue.size() + " Expected: 5");

        uniQueue.enqueue(2);
        StdOut.println("Size: " + uniQueue.size() + " Expected: 5");

        uniQueue.dequeue();
        StdOut.println("Size: " + uniQueue.size() + " Expected: 4");

        uniQueue.enqueue(8);
        StdOut.println("Size: " + uniQueue.size() + " Expected: 4");

        uniQueue.enqueue(16);
        StdOut.println("Size: " + uniQueue.size() + " Expected: 5");

        StdOut.println("\nItems:");
        for (int item : uniQueue) {
            StdOut.print(item + " ");
        }
        StdOut.println("\nExpected: 1 2 4 8 16");
    }

    private static class UniQueue<Item> implements Iterable<Item> {

        private Queue<Item> queue;
        private HashSet<Item> existenceSet;

        UniQueue() {
            existenceSet = new HashSet<>();
            queue = new Queue<>();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public int size() {
            return queue.size();
        }

        public void enqueue(Item item) {
            if (existenceSet.contains(item)) {
                return;
            }

            queue.enqueue(item);
            existenceSet.add(item);
        }

        public Item dequeue() {
            return queue.dequeue();
        }

        public Iterator<Item> iterator() {
            return queue.iterator();
        }

    }


}
