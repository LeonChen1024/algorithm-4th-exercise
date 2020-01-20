package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.Stack;

/**
 * 1.3.3 Suppose that a client performs an intermixed sequence of (stack) push and pop operations.
 * The push operations put the integers 0 through 9 in order onto the stack; the pop operations
 * print out the return values. Which of the following sequence(s) could not occur? <code>
 *     a.4321098765
 *     b.4687532901
 *     c.2567489310
 *     d.4321056789
 *     e.1234569870
 *     f.0465381729
 *     g.1479865302
 *     h.2143658790
 * </code> 假设一个客户端可以进行 一个stack push 和 pop 的混合操作, push 操作按顺序从0-9添加到stack中;pop 操作打印出返回值<br>
 * 以下哪个序列不可能发生.
 *
 * @author LeonChen
 * @since 12/24/19
 */
class e01_03_03 {

  public static void main(String[] args) {

    //    答案是b,f,g , 我们可以注意到,由于stack 的后入先出的原则,一但元素a入栈后并且另一个元素b也入栈了,那么a就不可能比
    //    b 提前出栈. 也就是说不会出现 大 小 中 这种排序的情况.

    // 我们还可以使用代码来解决,由于这个结果是由 0-9 通过入栈出栈得到的, 所以反之将这些结果通过反序的入栈出栈应该可以
    // 得到 9-0 , 如下

    int[] in = new int[] {4, 3, 2, 1, 0, 9, 8, 7, 6, 5};

    if (isValid(in)) {
      System.out.println("OK");
    } else {
      System.out.println("No");
    }
  }

  public static boolean isValid(int[] seq) {
    Stack<Integer> stack = new Stack<Integer>();
    int curWantNum = 9;
    int index = 9;
    while (curWantNum >= 0) {
      if (!stack.isEmpty() && stack.peek() == curWantNum) {
        // 当前栈顶是期待的数,弹出.
        stack.pop();
        curWantNum--;
      } else {
        if (index < 0) {
          // 添加完了,退出
          break;
        }
        // 添加数到栈中
        stack.push(seq[index]);
        index--;
      }
    }
    return stack.isEmpty();
  }
}
