package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.exercise.util.Constants;

/**
 * 4.1.7 Develop a test client for Graph that reads a graph from the input stream named
 * as command-line argument and then prints it, relying on toString().
 * <p>
 * 给 Graph 开发一个测试客户端,它从命令行参数输入流中读取一个图并且打印它,依靠 toString().
 *
 * @author LeonChen
 * @since 10/26/21
 */
class E4_1_07 {

    /**
     * @formatter:off
     * 源码中已有
     * @formatter:on
     */
    public static void main(String[] args) {
        String filePath = Constants.FILES_PATH + args[0];
        Graph graph = new Graph(new In(filePath));
        StdOut.println(graph);

    }

}
