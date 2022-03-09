package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

/**
 * 4.1.45 Random interval graphs. Consider a collection of V intervals on the real line
 * (pairs of real numbers). Such a collection defines an interval graph with one vertex
 * corresponding to each interval, with edges between vertices if the corresponding
 * intervals intersect (have any points in common). Write a program that generates V
 * random intervals in the unit interval, all of length d, then builds the corresponding
 * interval graph.
 * Hint: Use a BST.
 * <p>
 * 随机区间图.思考一个实线上的 V 区间集合(实数对).这个集合定义了一个区间图,每个顶点对应了每个区间,如果对应的
 * 间隔相交(有任何共同点),则顶点之间有边.编写一个程序在单位间隔中生成 V 个随机间隔,所有的长度 d,然后构建
 * 对应的区间图.
 * 提示: 使用 BST
 *
 * @author LeonChen
 * @since 11/25/21
 */
class E4_1_45 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        // Arguments example: 10 0.3
//        int vertices = Integer.parseInt(args[0]);
//        double length = Double.parseDouble(args[1]);
        int vertices = 10;
        double length = 0.3;

        if (length < 0 || length >= 1) {
            throw new IllegalArgumentException("Intervals length must be higher than 0 " +
                    "and less than 1");
        }

        IntervalGraph intervalGraph =
                generateIntervalGraph(vertices,
                        length);
        StdOut.println(intervalGraph);
    }

    public static class IntervalGraph {
        private IntervalBinarySearchTree.Interval[] keys;
        private Graph graph;

        private SeparateChainingHashST<IntervalBinarySearchTree.Interval, Integer> intervalToIdHashMap;

        public IntervalGraph(int vertices) {
            graph = new Graph(vertices);

            intervalToIdHashMap = new SeparateChainingHashST<>();
            keys = new IntervalBinarySearchTree.Interval[vertices];
        }

        public int vertices() {
            return graph.V();
        }

        public int edges() {
            return graph.E();
        }

        public void addEdge(IntervalBinarySearchTree.Interval interval1,
                            IntervalBinarySearchTree.Interval interval2) {
            if (!intervalToIdHashMap.contains(interval1)) {
                int newVertexId = intervalToIdHashMap.size();

                intervalToIdHashMap.put(interval1, newVertexId);
                keys[newVertexId] = interval1;
            }
            if (!intervalToIdHashMap.contains(interval2)) {
                int newVertexId = intervalToIdHashMap.size();

                intervalToIdHashMap.put(interval2, newVertexId);
                keys[newVertexId] = interval2;
            }

            int vertexId1 = intervalToIdHashMap.get(interval1);
            int vertexId2 = intervalToIdHashMap.get(interval2);

            graph.addEdge(vertexId1, vertexId2);
        }

        public boolean contains(IntervalBinarySearchTree.Interval interval) {
            return intervalToIdHashMap.contains(interval);
        }

        public int index(IntervalBinarySearchTree.Interval interval) {
            return intervalToIdHashMap.get(interval);
        }

        public IntervalBinarySearchTree.Interval getIntervalById(int vertexId) {
            return keys[vertexId];
        }

        public Graph graph() {
            return graph;
        }

        public Iterable<Integer> adjacent(int vertex) {
            return graph.adj(vertex);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            for (int vertex = 0; vertex < vertices(); vertex++) {
                IntervalBinarySearchTree.Interval intervalVertex = keys[vertex];
                stringBuilder.append("[").append(intervalVertex.min).append(" - ").append(intervalVertex.max).append("]: ");

                for (int neighbor : graph.adj(vertex)) {
                    IntervalBinarySearchTree.Interval neighborVertex = keys[neighbor];
                    stringBuilder.append("[").append(neighborVertex.min).append(" - ").append(neighborVertex.max).append("] ");
                }
                stringBuilder.append("\n");
            }

            return stringBuilder.toString();
        }
    }

    public static IntervalGraph generateIntervalGraph(int vertices, double length) {
        // Generate random endpoints
        IntervalBinarySearchTree.Interval[] randomIntervals =
                new IntervalBinarySearchTree.Interval[vertices];
        int randomIntervalsIndex = 0;

        IntervalBinarySearchTree<Integer> intervalBinarySearchTree =
                new IntervalBinarySearchTree();

        for (int randomVertex = 0; randomVertex < vertices; randomVertex++) {
            double randomIntervalStart = StdRandom.uniform(0, 1 - length);
            double randomIntervalEnd = randomIntervalStart + length;
            IntervalBinarySearchTree.Interval intervalVertex =
                    intervalBinarySearchTree.new Interval(randomIntervalStart,
                            randomIntervalEnd);
            randomIntervals[randomIntervalsIndex++] = intervalVertex;
        }

        // Generate interval graph
        IntervalGraph intervalGraph = new IntervalGraph(vertices);

        for (int vertexId = 0; vertexId < randomIntervals.length; vertexId++) {
            IntervalBinarySearchTree.Interval randomInterval = randomIntervals[vertexId];

            List<IntervalBinarySearchTree.Interval> intervalsThatIntersect =
                    intervalBinarySearchTree.getAllIntersections(randomInterval);

            for (IntervalBinarySearchTree.Interval intervalIntersection :
                    intervalsThatIntersect) {
                intervalGraph.addEdge(randomInterval, intervalIntersection);
            }

            intervalBinarySearchTree.put(randomInterval, vertexId);
        }

        return intervalGraph;
    }

    public static class IntervalBinarySearchTree<Value> {

        public class Interval implements Comparable<Interval> {
            double min;
            double max;

            Interval(double min, double max) {
                this.min = min;
                this.max = max;
            }

            public boolean intersects(Interval that) {
                if (this.max < that.min || that.max < this.min) {
                    return false;
                }
                return true;
            }

            @Override
            public int compareTo(Interval that) {
                if (this.min < that.min) {
                    return -1;
                } else if (this.min > that.min) {
                    return 1;
                } else if (this.max < that.max) {
                    return -1;
                } else if (this.max > that.max) {
                    return 1;
                } else {
                    return 0;
                }
            }

            @Override
            public boolean equals(Object other) {
                if (!(other instanceof IntervalBinarySearchTree.Interval)) {
                    return false;
                }

                IntervalBinarySearchTree.Interval otherInterval =
                        (IntervalBinarySearchTree.Interval) other;
                return this.min == otherInterval.min && this.max == otherInterval.max;
            }

            @Override
            public int hashCode() {
                return Double.hashCode(min) * 31 + Double.hashCode(max);
            }
        }

        private Node root;

        private class Node {
            Interval interval;  // key
            Value value;        // associated data
            Node left, right;   // left and right subtrees
            int size;           // size of subtree rooted at this node
            double max;         // max endpoint in subtree rooted at this node

            Node(Interval interval, Value value) {
                this.interval = interval;
                this.value = value;
                this.size = 1;
                this.max = interval.max;
            }
        }

        /***************************************************************************
         *  BST search
         ***************************************************************************/

        public boolean contains(Interval interval) {
            return (get(interval) != null);
        }

        // return value associated with the given key
        // if no such value, return null
        public Value get(Interval interval) {
            return get(root, interval);
        }

        private Value get(Node node, Interval interval) {
            if (node == null) {
                return null;
            }

            int compare = interval.compareTo(node.interval);

            if (compare < 0) {
                return get(node.left, interval);
            } else if (compare > 0) {
                return get(node.right, interval);
            } else {
                return node.value;
            }
        }

        /***************************************************************************
         *  Insertion
         ***************************************************************************/
        public void put(Interval interval, Value value) {
            if (contains(interval)) {
                //remove duplicates
                remove(interval);
            }

            root = randomizedInsert(root, interval, value);
        }

        // make new node the root with uniform probability to keep the BST balanced
        private Node randomizedInsert(Node node, Interval interval, Value value) {
            if (node == null) {
                return new Node(interval, value);
            }

            if (Math.random() * size(node) < 1.0) {
                return rootInsert(node, interval, value);
            }

            int compare = interval.compareTo(node.interval);
            if (compare < 0) {
                node.left = randomizedInsert(node.left, interval, value);
            } else {
                node.right = randomizedInsert(node.right, interval, value);
            }

            updateSizeAndMax(node);

            return node;
        }

        private Node rootInsert(Node node, Interval interval, Value value) {
            if (node == null) {
                return new Node(interval, value);
            }

            int compare = interval.compareTo(node.interval);
            if (compare < 0) {
                node.left = rootInsert(node.left, interval, value);
                node = rotateRight(node);
            } else {
                node.right = rootInsert(node.right, interval, value);
                node = rotateLeft(node);
            }

            return node;
        }

        /***************************************************************************
         *  Deletion
         ***************************************************************************/

        // Remove and return value associated with given interval;
        // if no such interval exists return null
        public Value remove(Interval interval) {
            Value value = get(interval);
            root = remove(root, interval);
            return value;
        }

        private Node remove(Node node, Interval interval) {
            if (node == null) {
                return null;
            }

            int compare = interval.compareTo(node.interval);
            if (compare < 0) {
                node.left = remove(node.left, interval);
            } else if (compare > 0) {
                node.right = remove(node.right, interval);
            } else {
                node = joinLeftAndRightNodes(node.left, node.right);
            }

            updateSizeAndMax(node);
            return node;
        }

        private Node joinLeftAndRightNodes(Node left, Node right) {
            if (left == null) {
                return right;
            }
            if (right == null) {
                return left;
            }

            if (Math.random() * (size(left) + size(right)) < size(left)) {
                left.right = joinLeftAndRightNodes(left.right, right);
                updateSizeAndMax(left);
                return left;
            } else {
                right.left = joinLeftAndRightNodes(left, right.left);
                updateSizeAndMax(right);
                return right;
            }
        }

        /***************************************************************************
         *  Interval searching
         ***************************************************************************/

        // return an interval in the data structure that intersects the given interval;
        // return null if no such interval exists
        // running time is proportional to log N
        public Interval getIntersection(Interval interval) {
            return getIntersection(root, interval);
        }

        // look in subtree rooted at node
        public Interval getIntersection(Node node, Interval interval) {
            while (node != null) {
                if (interval.intersects(node.interval)) {
                    return node.interval;
                } else if (node.left == null) {
                    node = node.right;
                } else if (node.left.max < interval.min) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }

            return null;
        }

        // return *all* intervals in the data structure that intersect the given interval
        // running time is proportional to R log N, where R is the number of intersections
        public List<Interval> getAllIntersections(Interval interval) {
            List<Interval> intersections = new ArrayList<>();
            getAllIntersections(root, interval, intersections);
            return intersections;
        }

        // look in subtree rooted at node
        public boolean getAllIntersections(Node node, Interval interval,
                                           List<Interval> intersections) {
            boolean foundCenter = false;
            boolean foundLeft = false;
            boolean foundRight = false;

            if (node == null) {
                return false;
            }

            if (interval.intersects(node.interval)) {
                intersections.add(node.interval);
                foundCenter = true;
            }

            if (node.left != null && node.left.max >= interval.min) {
                foundLeft = getAllIntersections(node.left, interval, intersections);
            }

            if (foundLeft || node.left == null || node.left.max < interval.min) {
                foundRight = getAllIntersections(node.right, interval, intersections);
            }

            return foundCenter || foundLeft || foundRight;
        }

        /***************************************************************************
         *  useful binary search tree functions
         ***************************************************************************/

        // return number of nodes in subtree rooted at node
        public int size() {
            return size(root);
        }

        private int size(Node node) {
            if (node == null) {
                return 0;
            } else {
                return node.size;
            }
        }

        // height of tree (empty tree height = 0)
        public int height() {
            return height(root);
        }

        private int height(Node node) {
            if (node == null) {
                return 0;
            }

            return 1 + Math.max(height(node.left), height(node.right));
        }

        /***************************************************************************
         *  helper binary search tree functions
         ***************************************************************************/

        // update tree information (subtree size and max fields)
        private void updateSizeAndMax(Node node) {
            if (node == null) {
                return;
            }

            node.size = 1 + size(node.left) + size(node.right);
            node.max = max3(node.interval.max, max(node.left), max(node.right));
        }

        private double max(Node node) {
            if (node == null) {
                return Double.MIN_VALUE;
            }

            return node.max;
        }

        // precondition: intervalAMax is not null
        private double max3(double intervalAMax, double intervalBMax,
                            double intervalCMax) {
            return Math.max(intervalAMax, Math.max(intervalBMax, intervalCMax));
        }

        private Node rotateRight(Node node) {
            Node newRoot = node.left;
            node.left = newRoot.right;
            newRoot.right = node;

            updateSizeAndMax(node);
            updateSizeAndMax(newRoot);

            return newRoot;
        }

        private Node rotateLeft(Node node) {
            Node newRoot = node.right;
            node.right = newRoot.left;
            newRoot.left = node;

            updateSizeAndMax(node);
            updateSizeAndMax(newRoot);

            return newRoot;
        }

        /***************************************************************************
         *  Debugging functions that test the integrity of the interval tree
         ***************************************************************************/

        // check integrity of subtree size and max fields
        public boolean checkIntegrity() {
            return checkSize() && checkMax();
        }

        // check integrity of size fields
        private boolean checkSize() {
            return checkSize(root);
        }

        private boolean checkSize(Node root) {
            if (root == null) {
                return true;
            }

            return checkSize(root.left)
                    && checkSize(root.right)
                    && (root.size == 1 + size(root.left) + size(root.right));
        }

        private boolean checkMax() {
            return checkMax(root);
        }

        private boolean checkMax(Node node) {
            if (node == null) {
                return true;
            }

            return node.max == max3(node.interval.max, max(node.left), max(node.right));
        }

    }


}
