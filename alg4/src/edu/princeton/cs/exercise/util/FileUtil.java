package edu.princeton.cs.exercise.util;

import edu.princeton.cs.algs4.StdOut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static String[] getAllStrFromFile(String filePath) {
        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            StdOut.println(e.getMessage());
            return null;
        }

        List<String> wordsList = new ArrayList<>();

        for (String line : lines) {
            String[] wordsInCurLine = line.split(" ");

            for (String word : wordsInCurLine) {
                if (word.equals("")) {
                    continue;
                }
                wordsList.add(word);
            }
        }

        String[] words = new String[wordsList.size()];
        wordsList.toArray(words);

        return words;
    }


}