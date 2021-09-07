package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

/**
 * 1.3.35 Random queue. A random queue stores a collection of items and supports the following API:
 * <code>
 * public class RandomQueue<Item>
 * RandomQueue() create an empty random queue
 * boolean isEmpty() is the queue empty?
 * void enqueue(Item item) add an item
 * Item dequeue() remove and return a random item (sample without replacement)
 * Item sample() return a random item, but do not remove (sample with replacement)
 * API for a generic random queue
 *
 * </code> Write a class RandomQueue that implements this API. Hint : Use an array
 * representation (with resizing). To remove an item, swap one at a random position (indexed 0
 * through N-1) with the one at the last position (index N-1). Then delete and return the last
 * object, as in ResizingArrayStack. Write a client that deals bridge hands (13 cards each) using
 * RandomQueue<Card>.
 * <p>
 * 随机 queue.一个随机 queue 存储一个项的集合并且支持以下 API:
 * <code>
 * public class RandomQueue<Item>
 * RandomQueue() create an empty random queue
 * boolean isEmpty() is the queue empty?
 * void enqueue(Item item) add an item
 * Item dequeue() remove and return a random item (sample without replacement)
 * Item sample() return a random item, but do not remove (sample with replacement)
 * API for a generic random queue
 * </code>
 * 编写一个类 RandomQueue 实现这个 API.提示: 使用一个数组代表(带有变化容量的功能).为了删除一个项,通过交换一个随机位置的(索引是 0<br>
 * 到 N-1 ) 项到最后一个位置(索引 N-1). 然后删除并且返回最后一个对象,如同在 ResizingArrayStack中一样.编写一个客户端使用<br>
 * RandomQueue<Card> 解决桥牌发牌(每人 13 张卡)问题.
 *
 * @author LeonChen
 * @since 1/28/20
 */
public class E01_03_35 {

    public static void main(String[] args) {

        RandomQueue<Card> cards = new RandomQueue<>();

        //发牌
        for (Card.SuitsType suitsType :
                Card.SuitsType.values()) {

            for (Card.Num num : Card.Num.values()) {
                cards.enqueue(new Card(suitsType, num));
            }
        }

        ArrayList<Card> player1Cards = new ArrayList<>();
        ArrayList<Card> player2Cards = new ArrayList<>();
        ArrayList<Card> player3Cards = new ArrayList<>();
        ArrayList<Card> player4Cards = new ArrayList<>();
        while (!cards.isEmpty()) {
            player1Cards.add(cards.dequeue());
            player2Cards.add(cards.dequeue());
            player3Cards.add(cards.dequeue());
            player4Cards.add(cards.dequeue());
        }

        printPlayersCard(player1Cards, "player1");
        printPlayersCard(player2Cards, "player2");
        printPlayersCard(player3Cards, "player3");
        printPlayersCard(player4Cards, "player4");

    }

    private static void printPlayersCard(ArrayList<Card> playerCards, String playerName) {
        StdOut.println(playerName);
        for (Card card : playerCards) {
            StdOut.print(card + ",");
        }
        StdOut.println();
    }

    private static class Card {
        private enum SuitsType {
            //黑桃
            spade,
            //红桃
            heart,
            //梅花
            club,
            //方块
            dianmond
        }

        private enum Num {
            two, three, four, five, six, seven, eight, nine, ten, jack, queen, king, ace
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

    public static class RandomQueue<Item> {
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
            items[swapIndex] = items[quntity - 1];
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
    }


}
