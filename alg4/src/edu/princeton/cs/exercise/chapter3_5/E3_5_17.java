package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 3.5.17 Mathematical sets. Your goal is to develop an implementation of the following
 * API MathSET for processing (mutable) mathematical sets:
 * \                     public class MathSET<Key>
 * MathSET(Key[] universe)              create a set
 * void add(Key key)                    put key into the set
 * MathSET<Key> complement()            set of keys in the universe that are not in
 * /                                    this set
 * void union(MathSET<Key> a)           put any keys from a into the set that are not
 * /                                    already there
 * void intersection(MathSET<Key> a)    remove any keys from this set that are not in a
 * void delete(Key key)                 remove key from the set
 * boolean contains(Key key)            is key in the set?
 * boolean isEmpty()                    is the set empty?
 * int size()                           number of keys in the set
 * \                    API for a basic set data type
 * <p>
 * Use a symbol table. Extra credit : Represent sets with arrays of boolean values.
 * <p>
 * 数学集合. 你的目标是开发一个下面 API 的 MathSET 实现用来处理可变数学集合
 * 使用符号表.额外分数: 使用 boolean 值得数组表示 set
 *
 * @author LeonChen
 * @since 9/10/21
 */
class E3_5_17 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {

        Integer[] universe = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        MathSET<Integer> mathSET = new MathSET<>(universe);

        //Test add
        mathSET.add(0);
        mathSET.add(2);
        mathSET.add(4);
        mathSET.add(6);

        StdOut.println("Keys in mathSET:");
        StdOut.println(mathSET);
        StdOut.println("Expected: { 0, 2, 4, 6 }");

        StdOut.println("\nSize: " + mathSET.size() + " Expected: 4");
        StdOut.println("isEmpty: " + mathSET.isEmpty() + " Expected: false");
        StdOut.println("Contains 4: " + mathSET.contains(4) + " Expected: true");
        StdOut.println("Contains 9: " + mathSET.contains(9) + " Expected: false");

        StdOut.println("\nDelete 6");
        mathSET.delete(6);
        StdOut.println(mathSET);
        StdOut.println("Expected: { 0, 2, 4 }");

        MathSET<Integer> mathSETUnion = new MathSET<>(universe);
        mathSETUnion.add(6);
        mathSETUnion.add(8);
        mathSETUnion.add(9);

        mathSET.union(mathSETUnion);

        StdOut.println("\nUnion with { 6, 8, 9 }");
        StdOut.println(mathSET);
        StdOut.println("Expected: { 0, 2, 4, 6, 8, 9 }");

        MathSET<Integer> complement = mathSET.complement();
        StdOut.println("\nComplement");
        StdOut.println(complement);
        StdOut.println("Expected: { 1, 3, 5, 7 }");

        MathSET<Integer> mathSETIntersect = new MathSET<>(universe);
        mathSETIntersect.add(1);
        mathSETIntersect.add(4);
        mathSETIntersect.add(8);
        mathSETIntersect.add(9);

        mathSET.intersection(mathSETIntersect);
        StdOut.println("\nIntersect with { 1, 4, 8, 9 }");
        StdOut.println(mathSET);
        StdOut.println("Expected: { 4, 8, 9 }");
    }

    public static class MathSET<Key> extends HashSet<Key> {

        HashSet<Key> universe; //Used to guarantee that only keys in this universe will be added to the math set
        Key[] universeArray; //Required by the API constructor

        MathSET(Key[] universe) {
            universeArray = universe;
            this.universe = new HashSet<>();

            for (Key key : universe) {
                this.universe.add(key);
            }
        }

        public boolean add(Key key) {
            if (!universe.contains(key)) {
                throw new IllegalArgumentException("Key " + key + " does not belong to the universe");
            }

            return super.add(key);
        }

        public MathSET<Key> complement() {
            MathSET<Key> complement = new MathSET<>(universeArray);

            for (Key key : universeArray) {
                if (!contains(key)) {
                    complement.add(key);
                }
            }

            return complement;
        }

        public void union(MathSET<Key> mathSetToUnite) {
            for (Iterator<Key> it = mathSetToUnite.iterator(); it.hasNext(); ) {
                Key key = it.next();
                if (!contains(key)) {
                    add(key);
                }
            }
        }

        public void intersection(MathSET<Key> mathSetToIntersect) {
            Iterator<Key> keysInMathSet = iterator();

            for (; keysInMathSet.hasNext(); ) {
                Key key = keysInMathSet.next();
                if (!mathSetToIntersect.contains(key)) {
                    keysInMathSet.remove();
                }
            }
        }

        public void delete(Key key) {
            super.remove(key);
        }

        public boolean contains(Object key) {
            return super.contains(key);
        }

        public boolean isEmpty() {
            return super.isEmpty();
        }

        public int size() {
            return super.size();
        }

    }


}
