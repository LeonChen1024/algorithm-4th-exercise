package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.16 Show the result of inserting n into the red-black BST drawn at right (only
 * the search path is shown, and you need to include only these nodes in your answer).
 *
 * @formatter:off
 *                      j
 *                                              u
 *                                           rt
 *                                        s
 *                                     rr
 *                                  q
 *                               rp
 *                           l
 *                       rk         o
 *                              rm
 *
 * @formatter:on
 * <p>
 * 展示出插入 n 到右边红黑树的结果(只展示搜索路径,并且在你的回答里只包含这些节点
 *
 * @author LeonChen
 * @since 6/21/21
 */
class E3_3_16 {

    /**
     * @formatter:off
     *
     *                      j
     *                                              u
     *                                           rt
     *                                        s
     *                                     rr
     *                                  q
     *                               rp
     *                           l
     *                       rk         o
     *                              rm
     *                                  rn
     * ---------------------------------------------------------------
     *                      j
     *                                              u
     *                                           rt
     *                                        s
     *                                     rr
     *                                  q
     *                               rp
     *                           l
     *                       rk         n
     *                              rm      ro
     *
     * ---------------------------------------------------------------
     *                      j
     *                                              u
     *                                           rt
     *                                        s
     *                                     rr
     *                                  q
     *                               rp
     *                           l
     *                       rk         rn
     *                               m      o
     *
     * ---------------------------------------------------------------
     *                      j
     *                                              u
     *                                           rt
     *                                        s
     *                                     rr
     *                                  q
     *                               rp
     *                           rl
     *                       k         n
     *                              m      o
     *
     * ---------------------------------------------------------------
     *                      j
     *                                              u
     *                                           rt
     *                                        s
     *                                     rr
     *                                   p
     *                             rl           rq
     *                          k      n
     *                               m   o
     *
     * ---------------------------------------------------------------
     *                      j
     *                                              u
     *                                           rt
     *                                        s
     *                                     rr
     *                                   rp
     *                             l           q
     *                          k      n
     *                               m   o
     *
     * ---------------------------------------------------------------
     *                      j
     *                                              u
     *                                           rt
     *                                        r
     *                                   rp          rs
     *                              l         q
     *                          k      n
     *                               m   o
     *
     * ---------------------------------------------------------------
     *                      j
     *                                              u
     *                                           rt
     *                                        rr
     *                                   p          s
     *                              l        q
     *                          k      n
     *                               m   o
     *
     * ---------------------------------------------------------------
     *                      j
     *                                              t
     *                                        rr                 ru
     *                                    p        s
     *                              l        q
     *                          k      n
     *                               m   o
     *
     * ---------------------------------------------------------------
     *                      j
     *                                              rt
     *                                        r                 u
     *                                    p        s
     *                              l        q
     *                          k      n
     *                               m   o
     *
     * ---------------------------------------------------------------
     *                          t
     *              rj                          u
     *                  r
     *              p        s
     *           l      q
     *         k    n
     *            m   o
     *
     * ---------------------------------------------------------------
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
