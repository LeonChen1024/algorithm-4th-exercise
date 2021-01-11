package edu.princeton.cs.exercise.chapter2_5;


import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.5.12 Scheduling. Write a program SPT.java that reads job names and processing times from
 * standard input and prints a schedule that minimizes average completion time using the shortest
 * processing time first rule, as described on page 349.
 *
 * 调度.编写一个程序 SPT.java 从标准输入读取工作名称和处理时间并且打印出一个调度安排使得平均完成时间最小化,使用
 * 最少处理时间优先原则,如正文349页.
 *
 * @author LeonChen
 * @since 12/22/20
 */
class E02_05_12 {
    private static class Job implements Comparable<Job> {

        private String name;
        private Integer time;

        public Job(String name, Integer time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public int compareTo(Job o) {
            return time.compareTo(o.time);
        }

        @Override
        public String toString() {
            return "Job{" +
                    "name='" + name + '\'' +
                    ", time=" + time +
                    '}';
        }
    }

    /**
     * 直接按照时间升序排序即可
     */
    public static void main(String[] args) {
        Job[] jobs = new Job[10];
        for (int i = 0; i < 10; i++) {
            Job item = new Job("job" + i, StdRandom.uniform(10));
            jobs[i] = item;
        }
        Arrays.sort(jobs);

        for (Job job : jobs) {
            StdOut.println(job);
        }

    }

}
