package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.exercise.util.Constants;

/**
 * 3.5.32 Dictionary. Study the performance of a client like LookupCSV in a scenario
 * where performance matters. Specifically, design a query-generation scenario instead of
 * taking commands from standard input, and run performance tests for large inputs and
 * large numbers of queries.
 * <p>
 * 字典. 研究客户端比如 LookupCSV 客户端在性能敏感情况下的性能. 设计一个 生成搜索的场景替代从命令行接收指令,
 * 并且对大量的输入搜索运行性能测试
 *
 * @author LeonChen
 * @since 9/16/21
 */
class E3_5_32 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        doExperiment();

    }

    public static class LookupCSV {

        public void lookup(String filePath, int keyField, int valueField, int numberOfQueries) {
            In in = new In(filePath);

            LinearProbingHashST<String, String> symbolTable = new LinearProbingHashST<>(997);

            // Using Java's set to avoid duplicate keys and to transform it in an array with the toArray() method later
            java.util.Set<String> keys = new java.util.HashSet<>();

            boolean isTitleRow = true;

            while (in.hasNextLine()) {
                String line = in.readLine();

                if (isTitleRow) {
                    isTitleRow = false;
                    continue;
                }

                String[] tokens = line.split(",");
                String key = tokens[keyField];
                String value = tokens[valueField];
                symbolTable.put(key, value);

                keys.add(key);
            }

            String[] keysArray = keys.toArray(new String[keys.size()]);

            // Queries
            for (int i = 0; i < numberOfQueries; i++) {
                // Randomly chooses if this will be a key hit or a key miss query
                int keyHit = StdRandom.uniform(2);
                boolean isKeyHit = keyHit == 1;

                String query;

                if (!isKeyHit) {
                    query = generateRandomKey();
                } else {
                    int randomKeyIndex = StdRandom.uniform(keysArray.length);
                    query = keysArray[randomKeyIndex];
                }

                if (symbolTable.contains(query)) {
                    symbolTable.get(query);
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

    private static void doExperiment() {
        LookupCSV lookupCSV = new LookupCSV();

        int[] numberOfQueries = {100000, 1000000, 10000000, 100000000, 1000000000};
        String[] filePaths = {LARGE_INPUT_FILE_PATH1, LARGE_INPUT_FILE_PATH2};
        String[] fileNames = {"surnames.csv", "sdss174052.csv"};

        StdOut.printf("%18s %20s %10s\n", "Large input | ", "Number of queries | ", "Time spent");

        for (int q = 0; q < numberOfQueries.length; q++) {
            for (int f = 0; f < filePaths.length; f++) {
                In in = new In(filePaths[f]);
                String line = in.readLine();
                String[] tokens = line.split(",");

                int randomKeyIndex = StdRandom.uniform(tokens.length);
                int randomValueIndex = StdRandom.uniform(tokens.length);

                Stopwatch stopwatch = new Stopwatch();
                lookupCSV.lookup(filePaths[f], randomKeyIndex, randomValueIndex, numberOfQueries[q]);
                double timeSpent = stopwatch.elapsedTime();

                printResults(fileNames[f], numberOfQueries[q], timeSpent);
            }
        }
    }

    private static void printResults(String fileName, int numberOfQueries, double timeSpent) {
        StdOut.printf("%15s %20d %13.2f\n", fileName, numberOfQueries, timeSpent);
    }


}
