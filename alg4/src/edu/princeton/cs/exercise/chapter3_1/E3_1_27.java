package edu.princeton.cs.exercise.chapter3_1;

/**
 * 3.1.27 Small tables. Suppose that a BinarySearchST client has S search operations
 * and N distinct keys. Give the order of growth of S such that the cost of building the table
 * is the same as the cost of all the searches.
 * <p>
 * 小表.假设有一个BinarySearchST 客户端有 S 个搜索操作和 N 个独立的键.给出 S 的增长级别使得创建表和表中搜索
 * 的成本相同
 *
 * @author LeonChen
 * @since 2/20/20
 */
class E3_1_27 {


    /**
     * 创建表的时候需要不断插入,每次插入需要查找一次,并且平均需要移动一半的元素,查找成本
     * lg0+lg1+...lgn< nlgn 总成本约为 nlgn+1/2\Sigma^n_{k=1}k=nlgn+n(n-1)/4
     * 单独的查找成本为 Slgn , 要使得查找和建表的成本相同可以得到 Slg_n = nlgn+n(n-1)/4
     * 解得 S = n+n(n-1)/(4lgn) 约等于 n^2/lgn ~= n^2
     *
     * @param args
     */
    public static void main(String[] args) {
    }


}
