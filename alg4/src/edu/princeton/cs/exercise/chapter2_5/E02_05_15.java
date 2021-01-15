package edu.princeton.cs.exercise.chapter2_5;


import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.5.15 Spam campaign. To initiate an illegal spam campaign, you have a list of email addresses
 * from various domains (the part of the email address that follows the @ symbol). To better
 * forge the return addresses, you want to send the email from another user at the same domain.
 * For example, you might want to forge an email from wayne@princeton.edu to rs@princeton.edu.
 * How would you process the email list to make this an efficient task?
 *
 * 垃圾邮件活动.为了创建一个非法垃圾邮件服务,你拥有一个不同域名(跟在符号@后面的那部分)的邮件地址列表.为了更好的伪造
 * 返回的地址,你想要发送一封其它相同域名的邮件.比如,你可能想要伪造一封邮件从 wayne@princeton.edu 到
 * rs@princeton.edu . 你要如何处理邮件列表来高效的处理这个任务
 *
 * @author LeonChen
 * @since 12/24/20
 */
class E02_05_15 {


    /**
     * 直接利用上一题的排序即可
     */
    public static void main(String[] args) {
        // read in domain names
        String[] names = StdIn.readAllStrings();
        E02_05_14.Domain[] domains = new E02_05_14.Domain[names.length];
        for (int i = 0; i < domains.length; i++) {
            domains[i] = new E02_05_14.Domain(names[i]);
        }

        // sort
        Arrays.sort(domains);

        // print results
        for (int i = 0; i < domains.length; i++) {
            StdOut.println(domains[i]);
        }
    }


}
