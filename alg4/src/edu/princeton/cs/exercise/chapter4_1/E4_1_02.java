package edu.princeton.cs.exercise.chapter4_1;

/**
 * 4.1.2 Draw, in the style of the figure in the text (page 524), the adjacency lists
 * built by Graph’s input stream constructor for the file tinyGex2.txt depicted at left.
 * see /chapter4_1/ex2.png
 * tinyGex2.txt
 * V   12
 * E   16
 * <p>
 * 8   4
 * 2   3
 * 1    11
 * 0    6
 * 3    6
 * 10   3
 * 7    11
 * 7    8
 * 11   8
 * 2    0
 * 6    2
 * 5    2
 * 5    10
 * 3    10
 * 8    1
 * 4    1
 * .....
 * <p>
 * 绘制,使用正文(524 页)的图形风格,输出 tinyGex2.txt 构建的图的邻接列表,图形见 /chapter4_1/ex2.png
 * <p>
 *
 * @author LeonChen
 * @since 10/21/21
 */
class E4_1_02 {

    /**
     * @formatter:off
     * 按照寻找方向依次传入即可,没有输入顺序,按照图形排布
     * 0    -5 -2 -6
     * 1    -4 -8 -11
     * 2    -6 -0 -5 -3
     * 3    -2 -10 -6
     * 4    -8 -1
     * 5    -0 -10 -2
     * 6    -3 -0 -2
     * 7    -8 -11
     * 8    -7 -11 -1 -4
     * 9
     * 10   -5 -3
     * 11   -7 -1 -8
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
