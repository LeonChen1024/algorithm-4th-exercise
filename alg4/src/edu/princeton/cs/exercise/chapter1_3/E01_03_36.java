package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * 1.3.36 Random iterator. Write an iterator for RandomQueue<Item> from the previous exercise that
 * returns the items in random order.
 *
 * <p>Random iterator. 从上一个练习中编写一个迭代器给 RandomQueue<Item> 使用一个随机顺序返回项.
 *
 * @author LeonChen
 * @since 1/29/20
 */
class E01_03_36 {

  public static void main(String[] args) {

    RandomQueue<Card> cards = new RandomQueue<>();

    // 发牌
    for (Card.SuitsType suitsType : Card.SuitsType.values()) {

      for (Card.Num num : Card.Num.values()) {
        cards.enqueue(new Card(suitsType, num));
      }
    }

    Iterator<Card> iter = cards.iterator();
    while (iter.hasNext()) {
      Card card = iter.next();
      System.out.println(card);
    }
  }

  private static class Card {
    private enum SuitsType {
      // 黑桃
      spade,
      // 红桃
      heart,
      // 梅花
      club,
      // 方块
      dianmond
    }

    private enum Num {
      two,
      three,
      four,
      five,
      six,
      seven,
      eight,
      nine,
      ten,
      jack,
      queen,
      king,
      ace
    }

    private SuitsType suits;
    private Num num;

    public Card(SuitsType suits, Num num) {
      this.suits = suits;
      this.num = num;
    }

    @Override
    public String toString() {
      return suits.toString() + " " + num.toString();
    }
  }

  private static class RandomQueue<Item> implements Iterable<Item> {
    private int quntity;
    private Item[] items;
    private int size;

    public RandomQueue() {
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

    public void enqueue(Item item) {
      if (quntity == size) {
        resize(size * 2);
      }
      items[quntity++] = item;
    }

    private void resize(int i) {
      Item[] newItems = (Item[]) new Object[i];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
      size = i;
    }

    public Item dequeue() {
      if (isEmpty()) {
        return null;
      }
      int swapIndex = StdRandom.uniform(quntity);
      Item temp = items[swapIndex];
      items[swapIndex] = items[quntity];
      items[--quntity] = null;
      return temp;
    }

    public Item sample() {
      if (isEmpty()) {
        return null;
      }

      int sampleIndex = StdRandom.uniform(quntity);
      return items[sampleIndex];
    }

    @Override
    public Iterator<Item> iterator() {
      return new Itera();
    }

    class Itera implements Iterator<Item> {

      private int size;

      public Itera() {
        size = quntity;
      }

      @Override
      public boolean hasNext() {
        return size != 0;
      }

      @Override
      public Item next() {
        int i = StdRandom.uniform(size);
        Item temp = items[i];
        items[i] = items[--size];
        items[size] = temp;
        return temp;
      }
    }
  }
}
