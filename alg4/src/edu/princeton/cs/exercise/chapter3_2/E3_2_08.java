package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.8 Write a static method optCompares() that takes an integer argument N and computes
 * the number of compares required by a random search hit in an optimal (perfectly
 * balanced) BST, where all the null links are on the same level if the number of links is
 * a power of 2 or on one of two levels otherwise.
 * <p>
 * 编写一个静态方法 optCompares() 接受一个整数参数 N 并且计算在最优(完美平衡)二叉树中随机搜索命中需要对比
 * 的次数,如果连接的个数是 2 的幂次那么所有的空连接都会在相同的层级否则会在两个层级上
 *
 * @author LeonChen
 * @since 4/5/20
 */
class E3_2_08 {

    /**
     * @formatter:off
     * 将完全二叉树分成两个部分,一个是满二叉树,还有一部分多余的节点,此时可以先计算满二叉树的总对比次数,
     * 再计算多余的节点的对比次数
     */
    public static void main(String[] args) {
//        BST
    }


    private static int optCompares(int n) {
        if (n == 0) {
            return 0;
        }

        int totalCompares = 0;
        // 计算满二叉树对比次数
        int fullTreeHeight = (int) (Math.log(n) / Math.log(2));
        int nodesOfFull = 0;

        for(int i = 1; i <= fullTreeHeight; i++) {
            totalCompares += i * Math.pow(2, i - 1);
            nodesOfFull += Math.pow(2, i - 1);
        }

        // 加上剩余对比次数
        int nodesInLastLevel = n - nodesOfFull;
        totalCompares += nodesInLastLevel * (fullTreeHeight + 1);

        int avgCompares = (totalCompares / n) + 1;

        return avgCompares;
    }


}
