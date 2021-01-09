package edu.princeton.cs.exercise.chapter2_5;


import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.5.10 Create a data type Version that represents a software version number, such as 115.1.1,
 * 115.10.1, 115.10.2 . Implement the Comparable interface so that 115.1.1 is less than 115.10.1,
 * and so forth.
 *
 * 创建一个数据类型 Version 代表着软件版本号, 比如115.1.1,115.10.1, 115.10.2 . 实现 Comparable 接口使得
 * 115.1.1 小于 115.10.1 等.
 *
 * @author LeonChen
 * @since 12/18/20
 */
class E02_05_10 {


    /**
     *
     */
    public static void main(String[] args) {
        String[] strings = {"115.1.1", "115.10.1", "115.10.2", "112.3.44"};
        Arrays.sort(strings);
        for (String string : strings) {
            StdOut.println(string);
        }
    }

    private static class Version implements Comparable<Version> {
        private int major;
        private int minor;
        private int revision;

        public Version(int major, int minor, int revision) {
            this.major = major;
            this.minor = minor;
            this.revision = revision;
        }

        public static Version stringToVersion(String s) {
            String[] split = s.split("/.");
            return new Version(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
                    Integer.parseInt(split[2]));
        }

        @Override
        public int compareTo(Version o) {
            return major - o.major == 0 ? (minor - o.minor == 0 ? (revision - o.revision)
                    : minor - o.minor) : major - o.major;
        }

        @Override
        public String toString() {
            return "Version{" +
                    "major=" + major +
                    ", minor=" + minor +
                    ", revision=" + revision +
                    '}';
        }
    }
}
