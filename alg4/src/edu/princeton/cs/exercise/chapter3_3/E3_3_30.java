package edu.princeton.cs.exercise.chapter3_3;

import edu.princeton.cs.algs4.RedBlackBST;

/**
 * 3.3.30 Sofware caching. Modify RedBlackBST to keep the most recently accessed Node
 * in an instance variable so that it can be accessed in constant time if the next put() or
 * get() uses the same key (see Exercise 3.1.25).
 * <p>
 * 软?缓存.修改 RedBlackBST 使用一个实例变量来保存最近访问的节点,使得当下次 put() 或者 get() 使用相同
 * key 的时候可以在常量时间内访问(见练习 3.1.25)
 *
 * @author LeonChen
 * @since 7/2/21
 */
class E3_3_30 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
    }

    private static class CacheRBT<Key extends Comparable<Key>, Value>
            extends RedBlackBST<Key, Value> {

        private Node cache;

        public void put(Key key, Value val) {
            if (key == null)
                throw new IllegalArgumentException("first argument to put() is null");
            if (val == null) {
                delete(key);
                return;
            }
            if (key.compareTo(cache.key) == 0) {
                cache.val = val;
                return;
            }

            root = put(root, key, val);
            root.color = BLACK;
            // assert check();
        }

        // insert the key-value pair in the subtree rooted at h
        protected Node put(Node h, Key key, Value val) {
            if (h == null) return new Node(key, val, RED, 1);

            int cmp = key.compareTo(h.key);
            if (cmp < 0) h.left = put(h.left, key, val);
            else if (cmp > 0) {
                h.right = put(h.right, key, val);
            } else {
                cache = h;
                h.val = val;
            }

            // fix-up any right-leaning links
            if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
            if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
            if (isRed(h.left) && isRed(h.right)) flipColors(h);
            h.size = size(h.left) + size(h.right) + 1;

            return h;
        }

        public Value get(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to get() is null");
            if (key.compareTo(cache.key) == 0) {
                return cache.val;
            }
            return get(root, key);
        }

        // value associated with the given key in subtree rooted at x; null if no such key
        protected Value get(Node x, Key key) {
            while (x != null) {
                int cmp = key.compareTo(x.key);
                if (cmp < 0) x = x.left;
                else if (cmp > 0) x = x.right;
                else {
                    cache = x;
                    return x.val;
                }
            }
            return null;
        }
    }


}
