package edu.princeton.cs.exercise.chapter2_5;


import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.5.14 Sort by reverse domain. Write a data type Domain that represents domain names,
 * including an appropriate compareTo() method where the natural order is in order of the reverse
 * domain name. For example, the reverse domain of cs.princeton.edu is edu.princeton.cs. This is
 * useful for web log analysis. Hint: Use s.split("\\.") to split the string s into tokens,
 * delimited by dots. Write a client that reads domain names from standard input and prints the
 * reverse domains in sorted order.
 *
 * 通过域名倒置排序.编写一个数据类型 Domain 代表域名,包含了一个合适的 compareTo() 方法使得自然顺序是域名倒置的
 * 顺序.比如,域名 cs.princeton.edu 的倒置是 edu.princeton.cs .这有利于web日志分析.提示:使用 s.split("\\.")
 * 来分割字符串到标识,使用点来当做分割符.编写一个客户端从标准输入读取域名并且以域名倒置有序打印.
 *
 * @author LeonChen
 * @since 12/23/20
 */
class E02_05_14 {


    static class Domain implements Comparable<Domain> {
        private final String[] fields;
        private final int n;

        // store fields in reverse order
        public Domain(String name) {
            fields = name.split("\\.");
            n = fields.length;
        }

        // return string representation - fields, delimited by .
        @Override
        public String toString() {
            if (n == 0) return "";
            String s = fields[0];
            for (int i = 1; i < n; i++) {
                s = fields[i] + "." + s;
            }
            return s;
        }

        // compare by reverse domain name
        @Override
        public int compareTo(Domain that) {
            for (int i = 0; i < Math.min(this.n, that.n); i++) {
                String s = this.fields[this.n - i - 1];
                String t = that.fields[that.n - i - 1];
                int c = s.compareTo(t);
                if (c < 0) {
                    return -1;
                } else if (c > 0) return +1;
            }
            return this.n - that.n;
        }


    }


    /**
     * 官方解答
     */
    public static void main(String[] args) {
        // read in domain names
        String[] names = StdIn.readAllStrings();
        Domain[] domains = new Domain[names.length];
        for (int i = 0; i < domains.length; i++) {
            domains[i] = new Domain(names[i]);
        }

        // sort
        Arrays.sort(domains);

        // print results
        for (int i = 0; i < domains.length; i++) {
            StdOut.println(domains[i]);
        }
    }


}
