package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.12 Draw the red-black BST that results after each transformation (color flip or
 * rotation) during the insertion of P for our standard indexing client.
 * <p>
 * 绘制出在标准索引客户端中插入 P 之后每次转换的结果(颜色翻转或者旋转)
 *
 * @author LeonChen
 * @since 6/17/21
 */
class E3_3_12 {

    /**
     * @formatter:off
     *                              
     *                             R
     *                      rE                X
     *                  C        M       rS
     *              rA       rH
     * -------------------------------------------------
     * 插入 P
     *                             R
     *                      rE                X
     *                  C        M       rS
     *              rA       rH    rP
     * -------------------------------------------------
     * 翻转
     *                             R
     *                      rE                X
     *                  C       rM       rS
     *              rA        H     P
     * -------------------------------------------------
     * 左旋
     *                               R
     *                      rM                X
     *                 rE        P       rS
     *               C     H
     *            rA
     * -------------------------------------------------
     * 右旋
     *                          M
     *                  rE           rR
     *                C     H      P       X
     *            rA                    rS
     * -------------------------------------------------
     * 翻转
     *                         rM
     *                   E            R
     *                C     H      P       X
     *            rA                    rS
     * -------------------------------------------------
     * 根节点置黑
     *                          M
     *                   E            R
     *                C     H      P       X
     *            rA                  rS
     * -------------------------------------------------
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
