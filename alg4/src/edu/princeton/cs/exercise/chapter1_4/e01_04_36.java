package edu.princeton.cs.exercise.chapter1_4;

/**
 * 1.4.36 Space usage for pushdown stacks. Justify the entries in the table below, which shows
 * typical space usage for various pushdown stack implementations. Use a static nested class for
 * linked-list nodes to avoid the non-static nested class overhead. <code>
 *     data structure             item type              space usage for N int values (bytes)
 *
 *                                  int                                  ~ 32N
 *   linked list                    Integer                              ~ 64N (应该是56N)
 *
 *                                  int                       between ~4N and ~16N
 * resizing array                   Integer                   between ~32N and ~56N
 *
 *                Space usage in pushdown stacks (various implementations)
 * </code>
 *
 * <p>pushdown 栈的空间使用. 证明下表中的条目,它们展示了多种 pushdown 栈实现的空间使用情况.使用一个一个静态<br>
 * 内部类实现链表节点来避免非静态内部类的overhead.
 *
 * @author LeonChen
 * @since 3/30/20
 */
class e01_04_36 {

  /**
   * <code>
   * linked list     int  ~32N = 需要N个 Node, 每个node overhead 16byte , 引用 8byte , int 4byte ,padding 4byte.所以总共 ~32N
   *                 Integer ~56N 比int 多了 Integer overhead 16byte , 和 Integer 引用 8byte , 总共 ~56N
   *
   *
   * resizing array   int  between ~4N and ~16N=  每个 int 4byte, 由于扩容机制是 25% ~ 100% 的容量,所以是 4N_16N . 已经默认排除了与N无关的成本.
   *                  Integer  between ~32N and ~56N  = 比int 每个多出了 overhead 16byte , 引用 8byte , padding 4byte ,所以是32N .<br>
   *                           由于容量是从 25%~100%, 当极限情况时,会有3N个引用没有值,只有引用,也就是24N.所以总共  ~32N and ~56N
   *
   *
   * </code>
   */
  public static void main(String[] args) {}
}
