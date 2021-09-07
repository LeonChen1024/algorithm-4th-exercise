package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.4.25 Hash cache. Modify Transaction on page 462 to maintain an instance variable
 * hash, so that hashCode() can save the hash value the first time it is called for each
 * object and does not have to recompute it on subsequent calls. Note : This idea works
 * only for immutable types.
 * <p>
 * hash 缓存.修改 462 页的Transaction 维护一个 hash 的实例变量,使得 hashCode() 可以保存每个对象第一次
 * 调用 hash 得到的值并且在后续的调用中不需要重新计算.注意:这个方式只针对不可变类型
 *
 * @author LeonChen
 * @since 8/9/21
 */
class E3_4_25 {

    /**
     * @formatter:off
     * 加一个缓存就行了
     * @formatter:on
     */
    public static void main(String[] args) {

        Transaction transaction = new Transaction("Person 1", new Date(1, 10, 5), 1000);
        StdOut.println(transaction.hashCode() + " Expected: Cache miss");
        StdOut.println(transaction.hashCode() + " Expected: Cache hit");
        StdOut.println(transaction.hashCode() + " Expected: Cache hit");
    }

    public static class Transaction {
        private final String who;
        private final Date when;
        private final double amount;

        private int hash;

        Transaction(String who, Date when, double amount) {
            this.who = who;
            this.when = when;
            this.amount = amount;

            hash = -1;
        }

        public int hashCode() {
            int hash;

            if (this.hash != -1) {
                hash = this.hash;

                StdOut.println("Cache hit");
            } else {
                hash = 17;
                hash = 31 * hash + who.hashCode();
                hash = 31 * hash + when.hashCode();
                hash = 31 * hash + ((Double) amount).hashCode();

                StdOut.println("Cache miss");

                this.hash = hash;
            }

            return hash;
        }

    }

}
