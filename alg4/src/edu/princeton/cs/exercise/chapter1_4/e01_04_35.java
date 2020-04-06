package edu.princeton.cs.exercise.chapter1_4;

/**
 * 1.4.35 Time costs for pushdown stacks. Justify the entries in the table below, which shows
 * typical time costs for various pushdown stack implementations, using a cost model that counts
 * both data references (references to data pushed onto the stack, either an array reference or a
 * reference to an object’s instance variable) and objects created.<code>
 *                                                             cost to push N int values
 *   data structure          item type           data references            objects created
 *                              int                  2N                          N
 * linked list                Integer                3N                          2N
 *
 *                              int                  ~5N                         lgN
 * resizing array             Integer                ~5N                         ~N
 *
 *                   Time costs for pushdown stacks (various implementations)
 * </code> pushdown 栈的时间成本. 证明下表中的条目,它们显示了多种 pshdown 栈实现的时间成本,使用一个成本模型<br>
 * 同时计算数据引用(指向被压入栈的数据引用,可以是数组引用或者是一个对象实例变量的引用)和创建的对象.
 *
 * @author LeonChen
 * @since 3/30/20
 */
class e01_04_35 {

  /**
   * <code>
   *  *                                                             cost to push N int values
   *  *   data structure          item type           data references            objects created
   *  *                              int                  2N                          N
   *  * linked list                Integer                3N                          2N
   *  *
   *  *                              int                  ~5N                         lgN
   *  * resizing array             Integer                ~5N                         ~N
   *  *
   *  *                   Time costs for pushdown stacks (various implementations)
   *  * </code> <code>
   * linked list   data references   int  2N = 一个指向当前对象,一个指向下一个对象.
   *                                 Integer 3N 比 int 多出了一个指向 Integer对象的引用
   *
   *               objects created   int      N = N个 Node
   *                                 Integer  2N 比int多出了一个Integer 对象
   *
   *
   * resizing array  data references   int     ~5N=因为可变数组的容量为 25%~100% ,所以极端情况下有 ~4N 个引用,加上自身数组的引用, ~5N
   *                                   Integer ~5N= 和int 相同
   *
   *                 objects created   int      lgN=每次扩容的时候前面的已有对象会被重新创建所以是 lgN
   *                                   Integer  因为Integer 每次都会创建一个对象,所以是 N+lgN ~N
   * </code>
   */
  public static void main(String[] args) {}
}
