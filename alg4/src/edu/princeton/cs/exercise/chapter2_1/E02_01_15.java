package edu.princeton.cs.exercise.chapter2_1;

/**
 * 2.1.15 Expensive exchange. A clerk at a shipping company is charged with the task of rearranging
 * a number of large crates in order of the time they are to be shipped out. Thus, the cost of
 * compares is very low (just look at the labels) relative to the cost of exchanges (move the
 * crates). The warehouse is nearly full—there is extra space sufficient to hold any one of the
 * crates, but not two. What sorting method should the clerk use?
 *
 * <p>昂贵的交换.一个货运公司的员工面临一项挑战任务,要将一定的大货箱按照出仓的时间进行排序.这样,对比的成本<br>
 * (只需要对照标签即可)相对于交换的成本(移动货箱)就很小.仓库已经接近满了,只有一个额外的空间来放置任意一个货箱,<br>
 * 没有两个.这个员工应该使用什么排序方法?
 *
 * @author LeonChen
 * @since 5/19/20
 */
class E02_01_15 {

  /**
   * 应该使用选择排序.如题所示,对比的成本远远小于交换成本,并且只有一个额外的空间,选择排序只需要一个额外的空间,虽然<br>
   * 选择排序需要对比 O(N^2) 次,但是在这个条件下这个成本并不高.
   */
  public static void main(String[] args) {}
}
