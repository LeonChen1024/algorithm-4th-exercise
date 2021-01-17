package edu.princeton.cs.exercise.chapter2_5;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 2.5.21 Multidimensional sort. Write a Vector data type for use in having the sorting
 * methods sort multidimensional vectors of d integers, putting the vectors in order by first
 * component, those with equal first component in order by second component, those with equal
 * first and second components in order by third component, and so forth.
 * <p>
 * 多维排序. 编写一个 Vector 数据类型用来排序一个d 个整数的多维向量,将向量按照第一个组件的大小排序,如果第一个
 * 组件大小相同那么按照第二个组件大小,如果第一个和第二个组件大小都相同那么按照第三个组件大小排序,以此类推
 *
 * @author LeonChen
 * @since 1/4/20
 */
class E02_05_21 {

    /**
     * 只需要写一个根据组件顺序比较的 compare 就可以了
     */
    public static void main(String[] args) {

        Vector vector = new Vector(new Integer[]{4, 7, 2, 7, 593, 26, 83, 46, 84, 3,});
        Vector vector2 = new Vector(new Integer[]{4, 7, 12, 67, 93, 26, 83, 46, 84, 3,});
        Vector vector3 = new Vector(new Integer[]{1, 7, 1, 67, 9, 26, 83, 46, 84, 3,});
        Vector vector4 = new Vector(new Integer[]{7, 2, 1, 67, 9, 26, 83, 46, 84, 3,});
        Vector vector5 = new Vector(new Integer[]{7, 2, 1, 67, 9, 26, 83, 46, 84, 3,});
        Vector vector6 = new Vector(new Integer[]{7, 2, 1, 67, 10, 26, 83, 46, 84, 3,});
        Vector vector7 = new Vector(new Integer[]{7, 2, 1, 67, 10, 26, 83, 46, 84, 5,});

        Vector[] vectors = new Vector[]{vector, vector2, vector3, vector4, vector5, vector6,
                vector7};
        Arrays.sort(vectors);
        StdOut.println(Arrays.toString(vectors));

    }

    private static class Vector implements Comparable<Vector> {

        private Integer[] componets;

        public Vector(Integer[] componets) {
            this.componets = componets;
        }

        @Override
        public int compareTo(Vector o) {
            for (int i = 0; i < componets.length; i++) {
                int compare = Integer.compare(componets[i], o.componets[i]);
                if (compare != 0) {
                    return compare;
                }
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Vector{" +
                    "componets=" + Arrays.toString(componets) +
                    '}';
        }
    }
}
