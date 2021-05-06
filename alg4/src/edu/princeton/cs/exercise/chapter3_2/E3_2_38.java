package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdDraw;

/**
 * 3.2.38 Tree drawing. Add a method draw() to BST that draws BST figures in the style
 * of the text. Hint : Use instance variables to hold node coordinates, and use a recursive
 * method to set the values of these variables.
 * <p>
 * 绘制树.添加一个方法 draw() 到 BST 中按照正文的风格绘制 BST 图.提示:使用实例变量来保存节点坐标,并且使用
 * 一个递归方法来设置这些变量值
 *
 * @author LeonChen
 * @since 4/30/21
 */
class E3_2_38 {

    /**
     * @formatter:off
     * 定下坐标连起来即可,避免碰撞,从最左节点开始计算,左中右的递归顺序,然后中间留出一些空位
     * @formatter:on
     */
    public static void main(String[] args) {
        StdDraw.setPenRadius(0.0025);

        BinarySearchTreeDrawable<Integer, String> binarySearchTreeDrawable = new BinarySearchTreeDrawable();

        binarySearchTreeDrawable.put(10, "Value");
        binarySearchTreeDrawable.put(4, "Value");
        binarySearchTreeDrawable.put(6, "Value");
        binarySearchTreeDrawable.put(1, "Value");
        binarySearchTreeDrawable.put(2, "Value");
        binarySearchTreeDrawable.put(17, "Value");
        binarySearchTreeDrawable.put(12, "Value");
        binarySearchTreeDrawable.put(20, "Value");
        binarySearchTreeDrawable.put(21, "Value");
        binarySearchTreeDrawable.put(34, "Value");
        binarySearchTreeDrawable.put(45, "Value");

        StdDraw.clear(StdDraw.WHITE);
        binarySearchTreeDrawable.draw();
    }

    private static class BinarySearchTreeDrawable<Key extends Comparable<Key>, Value> extends BST<Key, Value> {

        private class Node {
            private Key key;
            private Value value;
            private Node left, right;
            private int size;              // number of nodes in tree rooted here
            private double xCoordinate, yCoordinate;

            Node(Key key, Value value) {
                this.key = key;
                this.value = value;
                this.size = 1;
            }
        }

        private Node root;
        private int treeLevel;

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            if (node == null) {
                return 0;
            }

            return node.size;
        }

        public void put(Key key, Value value) {
            root = put(root, key, value);
        }

        private Node put(Node node, Key key, Value value) {
            if (node == null) {
                return new Node(key, value);
            }

            int compare = key.compareTo(node.key);

            if (compare < 0) {
                node.left = put(node.left, key, value);
            } else if (compare > 0) {
                node.right = put(node.right, key, value);
            } else {
                node.value = value;
            }

            node.size = size(node.left) + 1 + size(node.right);
            return node;
        }

        public void draw() {
            treeLevel = 0;
            setCoordinates(root, 0.9);

            StdDraw.setPenColor(StdDraw.BLACK);
            drawLines(root);
            drawNodes(root);
        }

        private void setCoordinates(Node node, double distance) {
            if (node == null) {
                return;
            }

            setCoordinates(node.left, distance - 0.05);
            node.xCoordinate = (0.5 + treeLevel++) / size();
            node.yCoordinate = distance - 0.05;
            setCoordinates(node.right, distance - 0.05);
        }

        private void drawLines(Node node) {
            if (node == null) {
                return;
            }

            drawLines(node.left);

            if (node.left != null) {
                StdDraw.line(node.xCoordinate, node.yCoordinate, node.left.xCoordinate, node.left.yCoordinate);
            }
            if (node.right != null) {
                StdDraw.line(node.xCoordinate, node.yCoordinate, node.right.xCoordinate, node.right.yCoordinate);
            }

            drawLines(node.right);
        }

        private void drawNodes(Node node) {
            if (node == null) {
                return;
            }

            double nodeRadius = 0.032;

            drawNodes(node.left);

            StdDraw.setPenColor(StdDraw.WHITE);
            //Clear the node circle area
            StdDraw.filledCircle(node.xCoordinate, node.yCoordinate, nodeRadius);

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.circle(node.xCoordinate, node.yCoordinate, nodeRadius);
            StdDraw.text(node.xCoordinate, node.yCoordinate, String.valueOf(node.key));

            drawNodes(node.right);
        }
    }


}
