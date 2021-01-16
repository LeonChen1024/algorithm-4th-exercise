package edu.princeton.cs.exercise.chapter2_5;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 2.5.20 Idle time. Suppose that a parallel machine processes N jobs. Write a program that,
 * given the list of job start and finish times, finds the largest interval where the machine
 * is idle and the largest interval where the machine is not idle.
 * <p>
 * 空闲时间.假定有一个并行机器处理 N 个工作.编写一个程序,给定一个有开始和结束时间的 job 列表,得出机器的最大空闲
 * 时间周期和最大忙碌时间周期
 *
 * @author LeonChen
 * @since 12/31/20
 */
class E02_05_20 {

    /**
     * 首先我们可以根据开始时间排序,这样下一个工作如果在前一个工作结束之前开始,那么这两个工作就可以合并成一个
     * 忙碌周期,而空闲周期就是在一个任务结束而下一个任务还没开始的时候.
     */
    public static void main(String[] args) {
        Job job1 = new Job(3, 11);
        Job job2 = new Job(45, 70);
        Job job3 = new Job(8, 22);
        Job job4 = new Job(77, 90);
        Job job5 = new Job(26, 30);
        Job job6 = new Job(80, 85);
        Job job7 = new Job(60, 88);

        Job[] jobs = new Job[]{job1, job2, job3, job4, job5, job6, job7};

        Arrays.sort(jobs);

        int maxIdleTime = 0;
        int maxBusyTime = 0;

        int curBusyStartTime = 0;
        int curBusyFinishTime = 0;
        int curIdleStartTime = 0;
        int curIdleFinishTime = 0;


        for (int i = 0; i < jobs.length; i++) {
            Job job = jobs[i];
            if (job.startTime <= curBusyFinishTime) {
                // 两个任务执行时间重叠,合并成一个忙碌时间段
                curBusyFinishTime = Math.max(curBusyFinishTime, job.finishTime);
                int curBusyTime = curBusyFinishTime - curBusyStartTime;
                maxBusyTime = Math.max(maxBusyTime, curBusyTime);
            } else {
                // 两个任务间出现隔离,结束上一个忙碌时间段,并开始计算空闲时间

                curIdleStartTime = curBusyFinishTime;
                curIdleFinishTime = job.startTime;
                int curIdleTime = curIdleFinishTime - curIdleStartTime;
                maxIdleTime = Math.max(maxIdleTime, curIdleTime);

                curBusyStartTime = job.startTime;
                curBusyFinishTime = job.finishTime;
                int curBusyTime = curBusyFinishTime - curBusyStartTime;
                maxBusyTime = Math.max(maxBusyTime, curBusyTime);

            }
        }

        StdOut.println("maxBusyTime = " + maxBusyTime + " maxIdleTime=" + maxIdleTime);

    }

    private static class Job implements Comparable<Job> {

        private int startTime;
        private int finishTime;

        public Job(int startTime, int finishTime) {
            this.startTime = startTime;
            this.finishTime = finishTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(int finishTime) {
            this.finishTime = finishTime;
        }

        @Override
        public int compareTo(Job o) {
            if (startTime < o.startTime) {
                return -1;
            } else if (startTime > o.startTime) {
                return 1;
            } else {
                return Integer.compare(finishTime, o.finishTime);
            }
        }
    }


}
