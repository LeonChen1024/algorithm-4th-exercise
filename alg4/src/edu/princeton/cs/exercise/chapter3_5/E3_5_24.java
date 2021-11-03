package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.5.24 Non-overlapping interval search. Given a list of non-overlapping intervals of
 * items, write a function that takes an item as argument and determines in which, if
 * any, interval that item lies. For example, if the items are integers and the intervals
 * are 1643-2033, 5532-7643, 8999-10332, 5666653-5669321, then the query point 9122
 * lies in the third interval and 8122 lies in no interval.
 * <p>
 * 无重叠间隔搜索. 给定一个列表的无重叠项,编写一个函数接收一个项作为参数并指明在哪一个,如果有的话,放置到那个
 * 项区间中.比如,如果项是整数并且间隔是 1643-2033, 5532-7643, 8999-10332, 5666653-5669321 ,那么
 * 搜索点 9122 落在第三个区间 并且 8122 没有落在任意区间
 *
 * @author LeonChen
 * @since 9/12/21
 */
class E3_5_24 {

    /**
     * @formatter:off
     * 用范围起始点做 key, 先找到目标 key 的 floor key,然后根据值区间判断是否落在里面,没有就搜索丢失
     * @formatter:on
     */
    public static void main(String[] args) {
        Interval interval1 = new Interval(1, 1643, 2033);
        Interval interval2 = new Interval(2, 5532, 7643);
        Interval interval3 = new Interval(3, 8999, 10332);
        Interval interval4 = new Interval(4, 5666653, 5669321);

        List<Interval> intervals = new ArrayList<>();
        intervals.add(interval1);
        intervals.add(interval2);
        intervals.add(interval3);
        intervals.add(interval4);

        NonOverlappingIntervalFinder nonOverlappingIntervalFinder =
                new NonOverlappingIntervalFinder(intervals);

        StdOut.println("Find 9122: " + nonOverlappingIntervalFinder.findInterval(9122) + " Expected: 3");
        StdOut.println("Find 8122: " + nonOverlappingIntervalFinder.findInterval(8122) + " Expected: -1");
        StdOut.println("Find -100: " + nonOverlappingIntervalFinder.findInterval(-100) + " Expected: -1");
        StdOut.println("Find 5531: " + nonOverlappingIntervalFinder.findInterval(5531) + " Expected: -1");
        StdOut.println("Find 5532: " + nonOverlappingIntervalFinder.findInterval(5532) + " Expected: 2");
        StdOut.println("Find 5669321: " + nonOverlappingIntervalFinder.findInterval(5669321) + " Expected: 4");
        StdOut.println("Find 5669322: " + nonOverlappingIntervalFinder.findInterval(5669322) + " Expected: -1");

    }

    private static class Interval {
        int start;
        int end;
        int index;

        Interval(int index, int start, int end) {
            this.index = index;
            this.start = start;
            this.end = end;
        }
    }

    private static class NonOverlappingIntervalFinder {
        private RedBlackBST<Integer, Interval> redBlackBST;

        NonOverlappingIntervalFinder(List<Interval> intervals) {
            redBlackBST = new RedBlackBST<>();

            for (Interval interval : intervals) {
                redBlackBST.put(interval.start, interval);
            }
        }

        private int findInterval(int query) {
            Integer key = redBlackBST.floor(query);
            if (key == null) {
                return -1;
            }
            Interval possibleInterval = redBlackBST.get(key);

            if (possibleInterval != null && possibleInterval.start <= query && query <= possibleInterval.end) {
                return possibleInterval.index;
            } else {
                return -1;
            }
        }
    }


}
