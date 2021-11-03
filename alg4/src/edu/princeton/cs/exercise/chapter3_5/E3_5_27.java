package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 3.5.27 List. Develop an implementation of the following API:
 * public class List<Item> implements Iterable<Item>
 * List()                       create a list
 * void addFront(Item item)     add item to the front
 * void addBack(Item item)      add item to the back
 * Item deleteFront()           remove from the front
 * Item deleteBack()            remove from the back
 * void delete(Item item)       remove item from the list
 * void add(int i, Item item)   add item as the ith in the list
 * Item delete(int i)           remove the ith item from the list
 * boolean contains(Item item)  is key in the list?
 * boolean isEmpty()            is the list empty?
 * int size()                   number of items in the list
 * \            API for a list data type
 * Hint : Use two symbol tables, one to find the ith item in the list efficiently, and the
 * other to efficiently search by item. (Java’s java.util.List interface contains
 * methods like these but does not supply any implementation that efficiently supports all
 * operations.)
 * <p>
 * List.开发一个以下 API 实现:
 * public class List<Item> implements Iterable<Item>
 * List()                       创建一个列表
 * void addFront(Item item)     添加一个 item 到前面
 * void addBack(Item item)      添加一个 item 到后面
 * Item deleteFront()           从首部删除一个元素
 * Item deleteBack()            从尾部删除一个元素
 * void delete(Item item)       从列表中删除指定 item
 * void add(int i, Item item)   在第 i 位添加 item
 * Item delete(int i)           删除列表中第 i 个item
 * boolean contains(Item item)  key 是否在列表中?
 * boolean isEmpty()            列表是否为空?
 * int size()                   列表中 item 的数量
 * \            API for a list data type
 * 提示:使用两个符号表,一个可以快速找到列表中第 i 个项,另一个可以快速的通过 item 查找位置(Java 的
 * java.util.List 接口包含了类似的方法但是没有提供所有操作的有效实现)
 *
 * @author LeonChen
 * @since 9/14/21
 */
class E3_5_27 {

    /**
     * @formatter:off
     * 使用一个初始 key 当做中间值,加在前面就最小值减偏移,加在后面就最小值加偏移
     * @formatter:on
     */
    public static void main(String[] args) {
        List<Integer> list = new List<>();

        StdOut.println("Add item 1 to the front of the list");
        //Test addFront() and addBack()
        list.addFront(1);
        StdOut.println("Add item 0 to the front of the list");
        list.addFront(0);
        StdOut.println("Add item 10 to the back of the list");
        list.addBack(10);
        StdOut.println("Add item 11 to the back of the list");
        list.addBack(11);

        //Test size()
        StdOut.println("\nSize: " + list.size() + " Expected: 4");

        //Test isEmpty()
        StdOut.println("isEmpty: " + list.isEmpty() + " Expected: false\n");

        //Test iterator
        for (int item : list) {
            StdOut.print(item + " ");
        }
        StdOut.println("\nExpected: 0 1 10 11");

        //Test add()
        StdOut.println("\nAdd item 9 on index 2");
        list.add(2, 9);

        for (int item : list) {
            StdOut.print(item + " ");
        }
        StdOut.println("\nExpected: 0 1 9 10 11");

        StdOut.println("\nAdd item -1 on index 0");
        list.add(0, -1);

        for (int item : list) {
            StdOut.print(item + " ");
        }
        StdOut.println("\nExpected: -1 0 1 9 10 11");

        //Test deleteFront()
        StdOut.println("\nDelete front");
        list.deleteFront();

        for (int item : list) {
            StdOut.print(item + " ");
        }
        StdOut.println("\nExpected: 0 1 9 10 11");

        //Test deleteBack()
        StdOut.println("\nDelete back");
        list.deleteBack();

        for (int item : list) {
            StdOut.print(item + " ");
        }
        StdOut.println("\nExpected: 0 1 9 10");

        //Test delete(int index)
        StdOut.println("\nDelete item on index 2");
        list.delete(2);

        for (int item : list) {
            StdOut.print(item + " ");
        }
        StdOut.println("\nExpected: 0 1 10");

        StdOut.println("\nDelete item on index 0");
        list.delete(0);

        for (int item : list) {
            StdOut.print(item + " ");
        }
        StdOut.println("\nExpected: 1 10");

        //Test delete(Item item)
        StdOut.println("\nDelete item 5");
        list.delete(new Integer(5));

        for (int item : list) {
            StdOut.print(item + " ");
        }
        StdOut.println("\nExpected: 1 10");

        StdOut.println("\nDelete item 10");
        list.delete(new Integer(10));

        for (int item : list) {
            StdOut.print(item + " ");
        }
        StdOut.println("\nExpected: 1");

        StdOut.println("\nDelete front");
        list.deleteFront();
        StdOut.println("isEmpty: " + list.isEmpty() + " Expected: true");
    }

    public static class List<Item> implements Iterable<Item> {

        private RedBlackBST<Double, Item> itemsBST;
        private SeparateChainingHashST<Item, Double> itemsPositions;

        private static final double INITIAL_VALUE = 50000;
        private static final double OFFSET = 0.0001;
        ;

        List() {
            itemsBST = new RedBlackBST<>();
            itemsPositions = new SeparateChainingHashST<>();
        }

        //O(lg n)
        public void addFront(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Item cannot be null");
            }

            if (contains(item)) {
                delete(item);
            }

            double minKey;

            if (isEmpty()) {
                minKey = INITIAL_VALUE;
            } else {
                minKey = itemsBST.min();
            }

            double newMinKey = minKey - OFFSET;

            itemsBST.put(newMinKey, item);
            itemsPositions.put(item, newMinKey);
        }

        //O(lg n)
        public void addBack(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Item cannot be null");
            }

            if (contains(item)) {
                delete(item);
            }

            double maxKey;

            if (isEmpty()) {
                maxKey = INITIAL_VALUE;
            } else {
                maxKey = itemsBST.max();
            }

            double newMaxKey = maxKey + OFFSET;

            itemsBST.put(newMaxKey, item);
            itemsPositions.put(item, newMaxKey);
        }

        //O(lg n)
        public Item deleteFront() {
            if (isEmpty()) {
                return null;
            }

            Item firstItem = itemsBST.get(itemsBST.min());

            itemsBST.deleteMin();
            itemsPositions.delete(firstItem);

            return firstItem;
        }

        //O(lg n)
        public Item deleteBack() {
            if (isEmpty()) {
                return null;
            }

            Item lastItem = itemsBST.get(itemsBST.max());

            itemsBST.deleteMax();
            itemsPositions.delete(lastItem);

            return lastItem;
        }

        //O(lg n)
        public void delete(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Item cannot be null");
            }

            if (!contains(item)) {
                return;
            }

            double itemPosition = itemsPositions.get(item);
            itemsBST.delete(itemPosition);
            itemsPositions.delete(item);
        }

        //O(lg n)
        public void add(int index, Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Item cannot be null");
            }

            if (index < 0 || index > size()
                    || (index == size() && contains(item))) {
                throw new IllegalArgumentException("Invalid index");
            }

            if (contains(item)) {
                delete(item);
            }

            double previousItemIndex = 0;
            double nextItemIndex = size() - 1;

            if (index > 0) {
                previousItemIndex = itemsBST.select(index - 1);
            } else if (index == 0) {
                previousItemIndex = itemsBST.min() - OFFSET;
            }

            if (index < size()) {
                nextItemIndex = itemsBST.select(index);
            } else if (index == size()) {
                nextItemIndex = itemsBST.max() + OFFSET;
            }

            double medianKey = (previousItemIndex + nextItemIndex) / 2;

            itemsBST.put(medianKey, item);
            itemsPositions.put(item, medianKey);
        }

        //O(lg n)
        public Item delete(int index) {
            if (index < 0 || index >= size()) {
                throw new IllegalArgumentException("Invalid index");
            }

            Item deletedItem = itemsBST.get(itemsBST.select(index));
            delete(deletedItem);
            return deletedItem;
        }

        //O(1)
        public boolean contains(Item item) {
            return itemsPositions.contains(item);
        }

        //O(1)
        public boolean isEmpty() {
            return size() == 0;
        }

        //O(1)
        public int size() {
            return itemsPositions.size();
        }

        @Override
        public Iterator<Item> iterator() {
            return new ListIterator();
        }

        //O(n)
        private class ListIterator implements Iterator<Item> {

            Queue<Double> keys;

            ListIterator() {
                keys = new LinkedList<>();

                for (Double key : itemsBST.keys()) {
                    keys.add(key);
                }
            }

            @Override
            public boolean hasNext() {
                return keys.size() > 0;
            }

            @Override
            public Item next() {
                return itemsBST.get(keys.poll());
            }
        }

    }


}
