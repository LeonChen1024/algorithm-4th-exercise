package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.7 Draw diagrams like the one at the top of page 428 for the other five cases
 * in the diagram at the bottom of that page.
 * <p>
 * 给428 页底部的 5 个情况绘制如同该页顶部的图表
 *
 * @author LeonChen
 * @since 6/14/21
 */
class E3_3_07 {

    /**
     * @formatter:off
     *
     * root
     *                            a b c
     *                  x<a   a<x<b    b<x<c    c<x
     *
     *                             b
     *                      a             c
     *                  x<a   a<x<b  b<x<c  c<x
     *
     * parent 2-node
     *
     *          left                 d
     *                      a b c               d<x
     *               x<a a<x<b b<x<c c<x<d
     *
     *                              b d
     *                     a         c          d<x
     *                  x<a a<x<b b<x<c c<x<d
     *
     *          right                   a
     *                      x<a                 b c d
     *                                 a<x<b b<x<c c<x<d d<x
     *
     *                                  a c
     *                       x<a         b            d
     *                              a<x<b b<x<c  c<x<d d<x
     *
     * parent 3-node
     *
     *          left                        d e
     *                          a b c           d<x<e           e<x
     *                   x<a a<x<b b<x<c c<x<d
     *
     *
     *                                      b d e
     *                           a           c      d<x<e       e<x
     *                       x<a   a<x<b  b<x<c c<x<d
     *
     *          right                       a b
     *                            x<a       a<x<b              c d e
     *                                                b<x<c c<x<d d<x<e e<x
     *
     *                                      a b d
     *                           x<a    a<x<b     c          e
     *                                       b<x<c c<x<d d<x<e e<x
     *
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
