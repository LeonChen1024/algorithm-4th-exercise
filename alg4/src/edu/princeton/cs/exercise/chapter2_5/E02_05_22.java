package edu.princeton.cs.exercise.chapter2_5;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.5.22 Stock market trading. Investors place buy and sell orders for a particular stock
 * on an electronic exchange, specifying a maximum buy or minimum sell price that they
 * are willing to pay, and how many shares they wish to trade at that price. Develop a
 * program that uses priority queues to match up buyers and sellers and test it through
 * simulation. Maintain two priority queues, one for buyers and one for sellers, executing
 * trades whenever a new order can be matched with an existing order or orders.
 * <p>
 * 股票市场交易. 投资人买卖指定股票订单的电子平台,指定一个让他们愿意支付的最大购买或者最小出售价格,并且她们愿意
 * 在这个价格交易多少股.开发一个程序使用优先队列来匹配买家和卖家并且模拟测试它.维护两个优先队列,一个给买家一个给
 * 卖家,当有新的订单可以匹配的时候进行处理
 *
 * @author LeonChen
 * @since 1/4/20
 */
class E02_05_22 {

    /**
     * 卖家优先队列以价格低的优先,而买家以价格高的优先.
     */
    public static void main(String[] args) {
        MaxPQ<Stock> buyerPQ = new MaxPQ<>();
        MinPQ<Stock> sellerPQ = new MinPQ<>();

        for (int i = 0; i < 10; i++) {
            Stock buy = new Stock(StdRandom.uniform(100), StdRandom.uniform(100));
            StdOut.println("buy price = " + buy.price + " , share = " + buy.share);
            Stock sell = new Stock(StdRandom.uniform(100), StdRandom.uniform(100));
            StdOut.println("sell price = " + sell.price + " , share = " + sell.share);
            buyerPQ.insert(buy);
            sellerPQ.insert(sell);
            while (!buyerPQ.isEmpty() && !sellerPQ.isEmpty()
                    && buyerPQ.max().price >= sellerPQ.min().price) {
                int share = Math.min(buyerPQ.max().share, sellerPQ.min().share);
                StdOut.println("trade price = " + sellerPQ.min().price
                        + ", trade share = " + share);
                buyerPQ.max().share -= share;
                sellerPQ.min().share -= share;
                if (buyerPQ.max().share == 0) {
                    buyerPQ.delMax();
                }
                if (sellerPQ.min().share == 0) {
                    sellerPQ.delMin();
                }
            }
        }


    }

    private static class Stock implements Comparable<Stock> {

        private double price;
        private int share;

        public Stock(double price, int share) {
            this.price = price;
            this.share = share;
        }

        @Override
        public int compareTo(Stock o) {
            return Double.compare(price, o.price);
        }
    }
}
