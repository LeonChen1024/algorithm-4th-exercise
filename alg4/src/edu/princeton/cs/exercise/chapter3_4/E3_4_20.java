package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.4.20 Add a method to LinearProbingHashST that computes the average cost of a
 * search hit in the table, assuming that each key in the table is equally likely to be sought.
 * <p>
 * 添加一个方法到 LinearProbingHashST 计算表中搜索命中的成本,假设每个表中的键出现的几率相同
 *
 * @author LeonChen
 * @since 8/5/21
 */
class E3_4_20 {

    /**
     * @formatter:off
     *
     * 这个方案是添加的时候测试找到该位置的对比次数,是对动态情况的平均.
     * 如果针对某个固定大小内容测试,需要遍历所有 key 做一次查找对比次数累积
     * @formatter:on
     */
    public static void main(String[] args) {
        LinearProbingHashTableAvgSearchHitCost<TestKey, Integer> linearProbingHashTableAvgSearchHitCost =
                new LinearProbingHashTableAvgSearchHitCost<>(20);

        //Hash code 1
        linearProbingHashTableAvgSearchHitCost.put(new TestKey(5), 5);
        StdOut.println(linearProbingHashTableAvgSearchHitCost.getAverageCostOfSearchHit() + " Expected: 1");

        //Hash code 0
        linearProbingHashTableAvgSearchHitCost.put(new TestKey(8), 8);
        //Hash code 2
        linearProbingHashTableAvgSearchHitCost.put(new TestKey(2), 2);
        StdOut.println(linearProbingHashTableAvgSearchHitCost.getAverageCostOfSearchHit() + " Expected: 1");

        //Hash code 1 -> hash collision and is sent to index 3
        linearProbingHashTableAvgSearchHitCost.put(new TestKey(1), 1);
        //Hash code 1 -> hash collision and is sent to index 4
        linearProbingHashTableAvgSearchHitCost.put(new TestKey(9), 9);
        StdOut.println(linearProbingHashTableAvgSearchHitCost.getAverageCostOfSearchHit() + " Expected: 2");
    }


    private static class LinearProbingHashTableAvgSearchHitCost<Key, Value> extends LinearProbingHashST<Key, Value> {

        LinearProbingHashTableAvgSearchHitCost(int size) {
            super(size);
        }

        private long totalNumberOfComparesForSearchHit;

        public long getAverageCostOfSearchHit() {
            return totalNumberOfComparesForSearchHit / n;
        }

        private void resize(int newSize) {
            LinearProbingHashTableAvgSearchHitCost<Key, Value> tempHashTable =
                    new LinearProbingHashTableAvgSearchHitCost<>(newSize);

            for (int i = 0; i < size(); i++) {
                if (keys[i] != null) {
                    tempHashTable.put(keys[i], vals[i]);
                }
            }

            keys = tempHashTable.keys;
            vals = tempHashTable.vals;
            m = tempHashTable.m;
            totalNumberOfComparesForSearchHit = tempHashTable.totalNumberOfComparesForSearchHit;
        }

        public void put(Key key, Value value) {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            if (value == null) {
                delete(key);
                return;
            }

            if (n >= m / (double) 2) {
                resize(m * 2);
            }

            int numberOfComparesBeforeFindingKey = 1;

            int tableIndex;
            for (tableIndex = hash(key); keys[tableIndex] != null; tableIndex =
                    (tableIndex + 1) % m) {
                numberOfComparesBeforeFindingKey++;

                if (keys[tableIndex].equals(key)) {
                    vals[tableIndex] = value;
                    return;
                }
            }

            totalNumberOfComparesForSearchHit += numberOfComparesBeforeFindingKey;

            keys[tableIndex] = key;
            vals[tableIndex] = value;
            n++;
        }
    }

    //Class to enable hash collisions test
    private static class TestKey {
        int key;

        TestKey(int key) {
            this.key = key;
        }

        @Override
        public int hashCode() {
            return key % 4;
        }
    }


}
