package edu.princeton.cs.exercise.chapter1_1;

import java.util.Scanner;
/**
 * 1.1.4 What (if anything) is wrong with each of the following statements? <br>
 * a.if(a>b)thenc=0;<br>
 * b.ifa>b{c=0;} <br>
 * c.if(a>b)c=0; <br>
 * d.if(a>b)c=0elseb=0;
 *
 * 下面的语句有哪里是错的吗?
 *
 *
 */
class e01_01_04 {



  /**
   */
  public static void main(String[] args) {
    int a = 0;
    int b = 0;
    int c = 0;

    // java 中没有 then 关键字.
    //    if(a>b)then c=0;

    // a>b 外缺少括号
    //    if a>b{c=0;}

    // 正确
    if (a > b) c = 0;

    // c=0 没有分号结尾
    //    if (a > b) c = 0
    //    else b = 0;
  }
}
