package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.44 Text editor buffer. Develop a data type for a buffer in a text editor that implements the
 * following API: <code>
 * public class Buffer
 * Buffer() create an empty buffer
 * void insert(char c) insert c at the cursor position
 * char delete() delete and return the character at the cursor
 * void left(int k) move the cursor k positions to the left
 * void right(int k) move the cursor k positions to the right
 * int size() number of characters in the buffer
 * API for a text buffer
 * </code> Hint : Use two stacks.
 *
 * <p>文本编辑器缓冲. 开发一个数据类型给文本编辑器中的缓冲区实现了以下 API: <code>
 * public class Buffer
 * Buffer() 创建一个空的缓冲
 * void insert(char c) 将 c 插入到指针的位置
 * char delete() 删除并且返回指针所在的字符
 * void left(int k) 将指针向左移动 k 个位置
 * void right(int k) 将指针向右移动 k 个位置
 * int size() 缓冲里字符的数量
 * API for a text buffer
 * </code> 提示: 使用两个 stack.
 *
 * @author LeonChen
 * @since 2/3/20
 */
class E01_03_45 {

  public static void main(String[] args) {
    EditorBuffer buffer = new EditorBuffer();
    buffer.insert('1');
    buffer.insert('2');
    buffer.insert('3');
    buffer.insert('4');
    buffer.insert('5');
    buffer.insert('6');
    buffer.insert('7');
    buffer.insert('8');
    StdOut.println("add numbers util 8 = " + buffer);
    StdOut.println(buffer.delete());
    StdOut.println("buffer.delete() = " + buffer);
    buffer.left(4);
    buffer.delete();
    StdOut.println("buffer.left(4) delete= " + buffer);

    buffer.right(1);
    buffer.delete();
    StdOut.println("buffer.right(1) delete = " + buffer);
    StdOut.println("buffer.size() = " + buffer.size());
  }

  /** 使用两个栈,分别是左栈和右栈,光标的位置就是在左栈顶,当光标左移的时候就是将栈顶元素推入到右栈中,右移就是将右栈顶元素弹出推入到左栈中. */
  public static class EditorBuffer {
    Stack<Character> leftStack;
    Stack<Character> rightStack;

    public EditorBuffer() {
      leftStack = new Stack<Character>();
      rightStack = new Stack<Character>();
    }

    void insert(char c) {
      leftStack.push(c);
    }

    char delete() {
      return leftStack.pop();
    }

    void left(int k) {
      for (int i = 0; i < k; i++) {
        rightStack.push(leftStack.pop());
      }
    }

    void right(int k) {
      for (int i = 0; i < k; i++) {
        leftStack.push(rightStack.pop());
      }
    }

    int size() {
      return leftStack.size() + rightStack.size();
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder();
      // 因为是从栈顶开始打印的,所以左栈要倒置,利用一个临时栈
      Stack<Character> temp = new Stack<>();

      for (Character c : leftStack) {
        temp.push(c);
      }
      for (Character c : temp) {
        result.append(c);
      }
      for (Character c : rightStack) {
        result.append(c);
      }
      return result.toString();
    }
  }
}
