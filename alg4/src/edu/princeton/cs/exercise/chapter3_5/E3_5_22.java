package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3.5.22 Fully indexed CSV. Implement an ST client FullLookupCSV that builds an array of
 * ST objects (one for each field), with a test client that allows the user to specify the
 * key and value fields in each query.
 * <p>
 * 完全索引的 CSV.实现一个 ST 客户端 FullLookupCSV 构建一个数组的 ST 对象(每个字段一个),使用一个测试
 * 客户端允许用户指定每个查询的键值字段
 *
 * @author LeonChen
 * @since 9/12/21
 */
class E3_5_22 {

    /**
     * @formatter:off
     * 使用一个 map 数组来保存,数组索引代表的是 key 索引,map 中保存的值序列是值序列
     *          //CSV file - csv_file.txt
     *         // rene,1,abc
     *         // sedgewick,9,aaa
     *         // dijkstra,10,dgs
     *         // rene,3,asfa
     *         // wayne,9,lpa
     *         // rene,5,lll
     *         // wayne,10,zzp
     *         // arnold,200,aab
     *         // dwayne,201,bba
     *         // fenwick,202,bbc
     *
     *         //Queries
     *         // 0 1 wayne
     *         // 0 2 rene
     *         // 1 0 3
     *         // 1 2 200
     *         // 2 0 aaa
     *         // 2 1 lpa
     *         // 0 0 fenwick
     *
     *         //Expected output
     *         // 10
     *         // lll
     *         // rene
     *         // aab
     *         // sedgewick
     *         // 9
     *         // fenwick
     * @formatter:on
     */
    public static void main(String[] args) {


//        String csvFilePath = Constants.FILES_PATH + args[0];
        String[] input = new String[]{"rene,1,abc", "sedgewick,9,aaa", "dijkstra,10,dgs",
                "rene,3,asfa", "wayne,9,lpa", "rene,5,lll", "wayne,10,zzp", "arnold,200,aab",
                "dwayne,201,bba", "fenwick,202,bbc"};
        FullLookupCSV fullLookupCSV = new FullLookupCSV();
        fullLookupCSV.buildHashMapArray(input);

//        while (StdIn.hasNextLine()) {
        String[] querys = new String[]{"0 1 wayne", "0 2 rene", "1 0 3", "1 2 200",
                "2 0 aaa", "2 1 lpa", "0 0 fenwick"};
//        String line = StdIn.readLine();
        for (String line : querys) {
            String[] words = line.split(" ");

            int keyField = Integer.parseInt(words[0]);
            int valueField = Integer.parseInt(words[1]);
            String query = words[2];

            StdOut.println(fullLookupCSV.get(keyField, valueField, query));
        }

//        }
    }

    private static class FullLookupCSV {

        private Map<String, List<String>>[] hashMapArray;

        public void buildHashMapArray(String[] input) {
            boolean isFirstLine = true;
            for (String line : input) {
                String[] tokens = line.split(",");

                if (isFirstLine) {
                    hashMapArray = (HashMap<String, List<String>>[]) new HashMap[tokens.length];

                    for (int i = 0; i < hashMapArray.length; i++) {
                        hashMapArray[i] = new HashMap<>();
                    }

                    isFirstLine = false;
                }

                for (int keyField = 0; keyField < tokens.length; keyField++) {
                    List<String> values = new ArrayList<>();

                    for (int valueField = 0; valueField < tokens.length; valueField++) {
                        if (valueField != keyField) {
                            values.add(tokens[valueField]);
                        }
                    }

                    hashMapArray[keyField].put(tokens[keyField], values);
                }
            }


        }

        public void buildHashMapArray(String csvFilePath) {
            In in = new In(csvFilePath);

            boolean isFirstLine = true;

            while (in.hasNextLine()) {
                String line = in.readLine();
                String[] tokens = line.split(",");

                if (isFirstLine) {
                    hashMapArray = (HashMap<String, List<String>>[]) new HashMap[tokens.length];

                    for (int i = 0; i < hashMapArray.length; i++) {
                        hashMapArray[i] = new HashMap<>();
                    }

                    isFirstLine = false;
                }

                for (int keyField = 0; keyField < tokens.length; keyField++) {
                    List<String> values = new ArrayList<>();

                    for (int valueField = 0; valueField < tokens.length; valueField++) {
                        if (valueField != keyField) {
                            values.add(tokens[valueField]);
                        }
                    }

                    hashMapArray[keyField].put(tokens[keyField], values);
                }
            }
        }

        public String get(int keyField, int valueField, String query) {

            if (keyField < 0 || valueField < 0) {
                throw new IllegalArgumentException("Fields must be equal or higher than 0");
            }

            if (keyField == valueField) {
                return query;
            } else if (keyField < valueField) {
                valueField--;
            }

            return hashMapArray[keyField].get(query).get(valueField);
        }
    }


}
