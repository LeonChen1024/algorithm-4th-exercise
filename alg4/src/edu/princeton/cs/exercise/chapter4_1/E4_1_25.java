package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.exercise.util.Constants;

import java.util.Calendar;

/**
 * 4.1.25 Modify DegreesOfSeparation to take an int value y as a command-line argument
 * and ignore movies that are more than y years old.
 * <p>
 * 修改 DegreesOfSeparation 来接收一个 int 值 y 作为命令行参数并且忽略比 y 年早发布的电影
 *
 * @author LeonChen
 * @since 11/4/21
 */
class E4_1_25 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        String filePath = Constants.FILES_PATH + Constants.MOVIES_FILE;
        new DegreesOfSeparationRecentMovies().buildGraphAndGetInput(filePath, "/",
                "Altered States (1980)", "20");

    }

    private static class DegreesOfSeparationRecentMovies {

        private void buildGraphAndGetInput(String filePath, String separator, String sourceMovie, String yearsOld) {
            SymbolGraph movieSymbolGraph = new SymbolGraph(filePath, separator);
            Graph graph = movieSymbolGraph.graph();

            if (!movieSymbolGraph.contains(sourceMovie)) {
                StdOut.println(sourceMovie + " not in database.");
                return;
            }

            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int yearsOldToConsider = Integer.parseInt(yearsOld);

            int sourceVertex = movieSymbolGraph.index(sourceMovie);
            BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph, sourceVertex);

            while (!StdIn.isEmpty()) {
                String sink = StdIn.readLine();

                //All movie names in the graph end with "(YYYY)"
                int movieYear = Integer.parseInt(sink.substring(sink.length() - 5, sink.length() - 1));

                if (currentYear - movieYear > yearsOldToConsider) {
                    StdOut.println("Ignoring old movie");
                    continue;
                }

                if (movieSymbolGraph.contains(sink)) {
                    int destinationVertex = movieSymbolGraph.index(sink);

                    if (breadthFirstPaths.hasPathTo(destinationVertex)) {
                        for (int vertexInPath : breadthFirstPaths.pathTo(destinationVertex)) {
                            StdOut.println("    " + movieSymbolGraph.name(vertexInPath));
                        }
                    } else {
                        StdOut.println("Not connected");
                    }
                } else {
                    StdOut.println("Not in database.");
                }
            }
        }

    }


}
