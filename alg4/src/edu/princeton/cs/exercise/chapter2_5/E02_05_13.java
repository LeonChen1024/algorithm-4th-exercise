package edu.princeton.cs.exercise.chapter2_5;


import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.5.13 Load balancing. Write a program LPT.java that takes an integer M as a command-line
 * argument, reads job names and processing times from standard input and prints a schedule
 * assigning the jobs to M processors that approximately minimizes the time when the last job
 * completes using the longest processing time first rule, as described on page 349.
 *
 * 均衡负载.编写一个程序 LPT.java 接收一个整数 M 作为一个命令行参数,从标准输入读取工作名字和处理时间并且打印出
 * 一个安排将任务指派给 M 个处理器让处理时间近似最小化,使用最长处理时间优先原则,如正文 349页描述的.
 *
 * @author LeonChen
 * @since 12/22/20
 */
class E02_05_13 {
    private static class Job implements Comparable<Job> {

        private String name;
        private Double time;

        public Job(String name, double time) {
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
     * 直接按照时间升序排序即可. 官方解答
     */
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);   // number of machines

        int n = StdIn.readInt();
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            String name = StdIn.readString();
            double time = StdIn.readDouble();
            jobs[i] = new Job(name, time);
        }

        // sort jobs in ascending order of processing time
        Arrays.sort(jobs);

        // generate m empty processors and put on priority queue
        MinPQ<Processor> pq = new MinPQ<Processor>(m);
        for (int i = 0; i < m; i++) {
            pq.insert(new Processor());
        }


        // assign each job (in decreasing order of time) to processor that is least busy
        for (int j = n - 1; j >= 0; j--) {
            Processor min = pq.delMin();
            min.add(jobs[j]);
            pq.insert(min);
        }

        // print out contents of each processor
        while (!pq.isEmpty()) {
            StdOut.println(pq.delMin());
        }
    }


    private static class Processor {
        private List<Job> jobs;
        private Double useTime;

        public Processor() {
        }

        public Processor(List<Job> jobs, Double useTime) {
            this.jobs = jobs;
            this.useTime = useTime;
        }

        public void add(Job job) {
            jobs.add(job);
            useTime += job.time;
        }

        public List<Job> getJobs() {
            return jobs;
        }

        public void setJobs(List<Job> jobs) {
            this.jobs = jobs;
        }

        public Double getUseTime() {
            return useTime;
        }

        public void setUseTime(Double useTime) {
            this.useTime = useTime;
        }
    }
}
