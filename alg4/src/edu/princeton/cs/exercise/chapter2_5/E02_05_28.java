package edu.princeton.cs.exercise.chapter2_5;

import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.Arrays;

/**
 * 2.5.28 Sort files by name. Write a program FileSorter that takes the name of a
 * directory as a command-line argument and prints out all of the files in the current
 * directory, sorted by file name. Hint : Use the File data type.
 * <p>
 * 通过文件名排序文件.编写一个程序 FileSorter 接受一个文件夹的名字作为参数并且打印出这个文件夹下的所有文件,
 * 通过文件名排序.提示: 使用 File 数据类型
 *
 * @author LeonChen
 * @since 1/8/20
 */
class E02_05_28 {

    /**
     * 官方解答,替换为实际输入便于测试
     */
    public static void main(String[] args) {

    }

    private static class FileSorter {
        public static void main(String[] args) {
            String dirName = "home";
            File directory = new File(dirName);     // root directory
//            File directory = new File(args[0]);     // root directory
            if (!directory.exists()) {
                StdOut.println(dirName + " does not exist");
//                StdOut.println(args[0] + " does not exist");
                return;
            }
            if (!directory.isDirectory()) {
                StdOut.println(dirName + " is not a directory");
//                StdOut.println(args[0] + " is not a directory");
                return;
            }
            File[] files = directory.listFiles();
            if (files == null) {
                StdOut.println("could not read files");
                return;
            }
            Arrays.sort(files);
            for (int i = 0; i < files.length; i++)
                StdOut.println(files[i].getName());
        }

    }


}
