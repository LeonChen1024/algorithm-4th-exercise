package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 3.2.9 Draw all the different BST shapes that can result when N keys are inserted into an
 * initially empty tree, for N = 2, 3, 4, 5, and 6.
 * <p>
 * 画出当插入 N 个键到一个初始为空的树中所有不同的 BST 形状,其中N =  2, 3, 4, 5, and 6.
 *
 * @author LeonChen
 * @since 4/6/21
 */
class E3_2_09 {

    /**
     * @formatter:off
     * 将完全二叉树分成两个部分,一个是满二叉树,还有一部分多余的节点,此时可以先计算满二叉树的总对比次数,
     * 再计算多余的节点的对比次数
     *
     * ========================================================================
     * N = 2
     *        0                    1
     *           1              0
     *
     *
     * N = 3
     *         0                      2                    1
     *            1                1                   0       2
     *               2          0
     *
     *
     *         2                     0
     *      0                            2
     *        1                        1
     *
     *
     * N = 4
     *         1                    3
     *      0     3             0
     *          2                 1
     *                              2
     *
     * ...........
     *
     *
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {
        int[] Ns = new int[]{2, 3, 4, 5, 6};
        for (int n : Ns) {
            StdOut.println(" N = " + n + "========================");
            ArrayList<Integer> source = new ArrayList();
            for (int i = 0; i < n; i++) {
                source.add(i, i);
            }
            ArrayList<List<Integer>> inputs = new ArrayList<>();
            getAllInputOrder(source, new ArrayList<Integer>(), inputs);
            HashSet<String> treePathSet = new HashSet<>();
            inputs.forEach(list -> {

                BST<Integer, Integer> bst = new BST<>();
                for (Integer integer : list) {
                    bst.put(integer, integer);
                }
                ArrayList<Integer> tree = new ArrayList<>();
                for (Integer key : bst.levelOrder()) {
                    tree.add(key);
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (Integer integer : tree) {
                    stringBuilder.append(integer);
                }
                String s = stringBuilder.toString();
                treePathSet.add(s);
            });
            StdOut.println(" N = " + n + " , path num =" + inputs.size());
            StdOut.println(" N = " + n + " ,distinct tree path num =" + treePathSet.size());
            StdOut.println("==========================================");
            StdOut.println("distinct path is " + String.join(",", treePathSet));
            StdOut.println("==========================================");
        }

    }

    private static ArrayList<List<Integer>> getAllInputOrder(
            ArrayList<Integer> source, ArrayList<Integer> path,
            ArrayList<List<Integer>> inputs) {

        if (source.size() == 0) {
            ArrayList<Integer> cPath = new ArrayList<>(path);
            inputs.add(cPath);
            return inputs;
        }

        for (int i = 0; i < source.size(); i++) {
            int item = source.get(i);
            path.add(item);
            source.remove(i);
            getAllInputOrder(source, path, inputs);
            source.add(i, item);
            path.remove((Object) item);
        }
        return inputs;
    }


}
