import searchalgorithm.Algorithms;
import searchalgorithm.Node;

import undirectedgraph.Graph;
import undirectedgraph.Romenia;

public class Main {
    public static void main(String[] args) {
        Graph graph = Romenia.defineGraph();
        graph.showLinks();
        graph.showSets();
        Node n;
        n = graph.searchSolution("Arad", "Bucharest", Algorithms.BreadthFirstSearch);
        graph.showSolution(n);

        System.out.println("\nTASK 1 - Comparison between different search algorithms\n");
        Task1.execute(graph);

    }

    static class Task1 {
        public static void execute(Graph graph) {

            String[][] testCases = {
                    {"Arad", "Bucharest"},
                    {"Bucharest", "Oradea"},
                    {"Oradea", "Bucharest"},
                    {"Timisoara", "Neamt"}
            };

            for (Algorithms algorithm : Algorithms.values()) {
                System.out.println("\n---" + algorithm + "---\n");

                for (String[] testCase : testCases) {
                    Node result = graph.searchSolution(testCase[0], testCase[1], algorithm);
                    graph.showSolution(result);
                }
            }
        }
    }
}
