package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 3.2.26 Exact probabilities. Find the probability that each of the trees in Exercise
 * 3.2.9 is the result of inserting N random distinct elements into an initially empty
 * tree.
 * <p>
 * 精确几率.求练习 3.2.9 中树插入 N 个不同元素到空树的每种树的概率
 *
 * @author LeonChen
 * @since 4/22/20
 */
class E3_2_26 {

    /**
     * @formatter:off
     * 将原本的 set 使用 map 替换,保存每种情况出现的次数即可
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
            HashMap<String, Integer> treePathMap = new HashMap<>();
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
                if (treePathMap.containsKey(s)) {
                    treePathMap.put(s, treePathMap.get(s) + 1);
                } else {
                    treePathMap.put(s, 1);
                }

            });
            StdOut.println(" N = " + n + " , path num =" + inputs.size());
            StdOut.println(" N = " + n + " ,distinct tree path num =" + treePathMap.size());
            treePathMap.forEach((s, i) -> {
                StdOut.println("distinct path is " + s + " , num is " + i + " , " +
                        "probability is " + (i * 100.0) / (float) inputs.size() +
                        "%");
            });


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
