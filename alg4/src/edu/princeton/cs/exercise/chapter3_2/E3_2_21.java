package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.21 Add a BST method randomKey() that returns a random key from the symbol
 * table in time proportional to the tree height, in the worst case.
 * <p>
 * 给 BST 添加一个方法 randomKey() ,从符号表返回一个随机 key ,在最差的情况下时间成本和树高成比例
 *
 * @author LeonChen
 * @since 4/18/21
 */
class E3_2_21 {

    /**
     * @formatter:off
     * 先随机获取一个 index ,再根据 select 取出这个key
     * public Key randomKey() {
     *         if (isEmpty()) {
     *             return null;
     *         }
     *
     *         int randomIndex = StdRandom.uniform(size());
     *         return select(randomIndex);
     *     }
     * @formatter:on
     */
    public static void main(String[] args) {

    }


}
