package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.io.File;

/**
 * 1.3.43 Listing files. A folder is a list of files and folders. Write a program that takes the
 * name of a folder as a command-line argument and prints out all of the files contained in that
 * folder, with the contents of each folder recursively listed (indented) under that folder’s name.
 * Hint : Use a queue, and see java.io.File.
 *
 * <p>列出所以文件. 一个文件时一个文件和文件夹的列表.编写一个程序从命令行接收一个文件夹的名字作为参数并且打印出所有包含在这个文件<br>
 * 夹中的文件,并且包含每个文件夹里的文件内容列(带有缩进)在这个文件夹的名字下并不断迭代.
 *
 * @author LeonChen
 * @since 2/3/20
 */
class E01_03_43 {

  public static void main(String[] args) {

    // 因为栈是后入先出,所以需要一个临时栈将弹出的 item 按照正确的顺序重新压栈到目标栈.

    listFile("/Users/test", 1);
  }

  private static void listFile(String filePath, int level) {
    File file = new File(filePath);
    if (file.isDirectory()) {
      for (File f : file.listFiles()) {
        for (int i = 0; i < level - 1; i++) {
          StdOut.print("*");
        }
        StdOut.println(f.getName());
        if (f.isDirectory()) {
          listFile(f.getAbsolutePath(), level++);
        }
      }
    }
  }
}
