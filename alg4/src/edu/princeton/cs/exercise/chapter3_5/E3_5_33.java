package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.exercise.util.Constants;

/**
 * 3.5.33 Indexing. Study a client like LookupIndex in a scenario where performance
 * matters. Specifically, design a query-generation scenario instead of taking commands
 * from standard input, and run performance tests for large inputs and large numbers of
 * queries.
 * <p>
 * 索引.在性能敏感的场景下研究一个如同 LookupIndex 的客户端.设计一个生成查询的场景替代从标准输入接收指令,
 * 并且针对大量查询输入的情况运行性能测试
 *
 * @author LeonChen
 * @since 9/17/21
 */
class E3_5_33 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        LookupIndex lookupIndex = new LookupIndex();

        int[] numberOfQueries = {100000, 1000000, 10000000, 100000000, 1000000000};
        String[] filePaths = {LARGE_INPUT_FILE_PATH1, LARGE_INPUT_FILE_PATH2};
        String[] fileNames = {"surnames.csv", "sdss174052.csv"};

        StdOut.printf("%18s %20s %10s\n", "Large input | ", "Number of queries | ", "Time spent");

        for (int q = 0; q < numberOfQueries.length; q++) {
            for (int f = 0; f < filePaths.length; f++) {
                Stopwatch stopwatch = new Stopwatch();
                lookupIndex.lookupIndex(filePaths[f], SEPARATOR, numberOfQueries[q]);
                double timeSpent = stopwatch.elapsedTime();

                printResults(fileNames[f], numberOfQueries[q], timeSpent);
            }
        }
    }

    public static class LookupIndex {

        public void lookupIndex(String filePath, String separator, int numberOfQueries) {
            In in = new In(filePath);

            LinearProbingHashST<String, Queue<String>> symbolTable1 =
                    new LinearProbingHashST<>(997);
            LinearProbingHashST<String, Queue<String>> symbolTable2 = new LinearProbingHashST<>(997);

            //Using Java's set to avoid duplicate keys and to transform it in an array with the toArray() method later
            java.util.Set<String> keys = new java.util.HashSet<>();

            boolean isTitleRow = true;

            while (in.hasNextLine()) {

                String[] tokens = in.readLine().split(separator);

                if (isTitleRow) {
                    isTitleRow = false;
                    continue;
                }

                String key = tokens[0];

                for (int i = 1; i < tokens.length; i++) {
                    String value = tokens[i];

                    if (!symbolTable1.contains(key)) {
                        symbolTable1.put(key, new Queue<>());
                    }
                    if (!symbolTable2.contains(value)) {
                        symbolTable2.put(value, new Queue<>());
                    }

                    symbolTable1.get(key).enqueue(value);
                    symbolTable2.get(value).enqueue(key);

                    keys.add(value);
                }
                keys.add(key);
            }

            String[] keysArray = keys.toArray(new String[keys.size()]);

            //Queries
            for (int i = 0; i < numberOfQueries; i++) {
                //Randomly chooses if this query will be a key hit or a key miss
                int keyHit = StdRandom.uniform(2);
                boolean isKeyHit = keyHit == 1;

                String query;

                if (!isKeyHit) {
                    query = generateRandomKey();
                } else {
                    int randomKeyIndex = StdRandom.uniform(keysArray.length);
                    query = keysArray[randomKeyIndex];
                }

                if (symbolTable1.contains(query)) {
                    for (String value : symbolTable1.get(query)) {
                        //Loop through values
                    }
                }

                if (symbolTable2.contains(query)) {
                    for (String value : symbolTable2.get(query)) {
                        //Loop through values
                    }
                }
            }
        }

        private String generateRandomKey() {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 5; i++) {
                int ascIIRandomValue = StdRandom.uniform(Constants.UPPER_LETTER_INITIAL_INDEX,
                        Constants.LOWER_LETTER_FINAL_INDEX + 1);
                stringBuilder.append(((char) ascIIRandomValue));
            }

            return stringBuilder.toString();
        }

    }

    private static final String LARGE_INPUT_FILE_PATH1 = Constants.FILES_PATH + "surnames.csv";
    private static final String LARGE_INPUT_FILE_PATH2 = Constants.FILES_PATH + "sdss174052.csv";
    private static final String SEPARATOR = ",";


    private static void printResults(String fileName, int numberOfQueries, double timeSpent) {
        StdOut.printf("%15s %20d %13.2f\n", fileName, numberOfQueries, timeSpent);
    }


}
