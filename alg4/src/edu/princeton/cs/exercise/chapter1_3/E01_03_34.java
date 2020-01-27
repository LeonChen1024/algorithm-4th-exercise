package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * 1.3.34 Random bag. A random bag stores a collection of items and supports the following API:
 * <code>
 *     public class RandomBag<Item> implements Iterable<Item>
 * RandomBag() create an empty random bag
 * boolean isEmpty() is the bag empty?
 * int size() number of items in the bag
 * void add(Item item) add an item
 * API for a generic random bag
 * </code> Write a class RandomBag that implements this API. Note that this API is the same as for
 * Bag, except for the adjective random, which indicates that the iteration should provide the items
 * in random order (all N! permutations equally likely, for each iterator). Hint : Put the items in
 * an array and randomize their order in the iterator’s constructor.
 *
 * <p>随机 bag. 一个随机 bag 存储了一个项的集合并且支持以下 API : <code>
 *  *     public class RandomBag<Item> implements Iterable<Item>
 *  * RandomBag() create an empty random bag
 *  * boolean isEmpty() is the bag empty?
 *  * int size() number of items in the bag
 *  * void add(Item item) add an item
 *  * API for a generic random bag
 *  * </code>
 *  编写一个类 RandomBag 实现这个 API . 注意这个 API 和 Bag 的是一样的,除了随机的形容,也就是说这个迭代器要支持它里面的项是随机顺序的<br>
 *      (对于每个迭代器所有的 N! 种排列的可能性都是相等的). 提示: 在迭代器的构造器中把所有项放到一个数组里并且随机化他们的顺序.
 *
 * @author LeonChen
 * @since 1/27/20
 */
class E01_03_34 {

  public static void main(String[] args) {
    RandomBag<String> bag = new RandomBag<>();
    bag.add("1");
    bag.add("2");
    bag.add("3");
    bag.add("4");

    for (String s: bag) {
      StdOut.print(s);
    }

  }


  private static class RandomBag<Item> implements Iterable<Item>{
    private int quntity;
    private Item[] items;
    private int size;

    public RandomBag() {
      size = 3;
      items = (Item[]) new Object[size];
      quntity = 0;
    }

    public boolean isEmpty() {
      return quntity == 0;
    }

    public int size() {
      return quntity;
    }

    public void add(Item item) {
      if (quntity == size) {
        resize(size * 2);
      }
      items[quntity++] = item;
    }

    private void resize(int i) {
      Item[] newItems = (Item[]) new Object[i];
      System.arraycopy(items,0,newItems,0,items.length);
      items = newItems;
    }

    @Override
    public Iterator<Item> iterator() {
      return new Itera();
    }

    class Itera implements Iterator<Item> {

      private Item[] temp;
      private int index;

      public Itera() {
        temp = (Item[]) new Object[quntity];
        System.arraycopy(items,0,temp,0,temp.length);
        StdRandom.shuffle(temp);
        index = 0;
      }

      @Override
      public boolean hasNext() {
        return index < temp.length;
      }

      @Override
      public Item next() {
        return temp[index++];
      }
    }
  }

}
