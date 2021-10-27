package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.5.14 Develop and test a static method invert() that takes as argument an
 * ST<String, Bag<String>> and produces as return value the inverse of the given symbol
 * table (a symbol table of the same type).
 * <p>
 * 开发一个测试的静态方法 invert() 接收参数 ST<String, Bag<String>> 生成并返回一个非定符号表的键值相反
 * 结果(拥有相同类型的符号表)
 *
 * @author LeonChen
 * @since 9/9/21
 */
class E3_5_14 {

    /**
     * @formatter:off
     * 将值当做 key 即可
     * @formatter:on
     */
    public static void main(String[] args) {
        RedBlackBST<String, Bag<String>> redBlackBST = new RedBlackBST<>();
        Bag<String> colorsBag = new Bag<>();
        colorsBag.add("red");
        colorsBag.add("green");
        colorsBag.add("blue");
        redBlackBST.put("Colors", colorsBag);

        Bag<String> sortsBag = new Bag<>();
        sortsBag.add("mergesort");
        sortsBag.add("quicksort");
        sortsBag.add("heapsort");
        redBlackBST.put("Sorts", sortsBag);

        Bag<String> mixedBag = new Bag<>();
        mixedBag.add("mergesort");
        mixedBag.add("blue");
        mixedBag.add("algorithms");
        redBlackBST.put("Mixed Bag", mixedBag);

        RedBlackBST<String, Bag<String>> inverseSymbolTable = invert(redBlackBST);
        for (String key : inverseSymbolTable.keys()) {
            StdOut.println(key);

            for (String value : inverseSymbolTable.get(key)) {
                StdOut.println("  " + value);
            }
        }

        //Test
        StdOut.println("\nTests");
        StdOut.println("\nred key");
        for (String value : inverseSymbolTable.get("red")) {
            StdOut.println(value);
        }
        StdOut.println("Expected: \nColors");

        StdOut.println("\nblue key");
        for (String value : inverseSymbolTable.get("blue")) {
            StdOut.println(value);
        }
        StdOut.println("Expected: \nMixed Bag\nColors");

        StdOut.println("\nquicksort key");
        for (String value : inverseSymbolTable.get("quicksort")) {
            StdOut.println(value);
        }
        StdOut.println("Expected: \nSorts");

        StdOut.println("\nmergesort key");
        for (String value : inverseSymbolTable.get("mergesort")) {
            StdOut.println(value);
        }
        StdOut.println("Expected: \nSorts\nMixed Bag");
    }

    private static RedBlackBST<String, Bag<String>> invert(RedBlackBST<String, Bag<String>> symbolTable) {
        RedBlackBST<String, Bag<String>> inverseSymbolTable = new RedBlackBST<>();

        for (String key : symbolTable.keys()) {
            Bag<String> values = symbolTable.get(key);

            for (String newKey : values) {
                if (!inverseSymbolTable.contains(newKey)) {
                    inverseSymbolTable.put(newKey, new Bag<>());
                }
                inverseSymbolTable.get(newKey).add(key);
            }
        }

        return inverseSymbolTable;
    }

}
