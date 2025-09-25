import searchalgorithm.Algorithms;
import searchalgorithm.Node;

import undirectedgraph.Graph;
import undirectedgraph.Romenia;
import undirectedgraph.Vertex;
import undirectedgraph.VertexSet;

public class Main {
    public static void main(String[] args) {
        Graph romeniaGraph = Task1.init();
        Task2.execute(romeniaGraph);
        Task3.execute(romeniaGraph, "Arad", "Bucharest", "Dobrogea", Algorithms.AStarSearch);
    }

    static class Task1 {
        public static Graph init() {
            System.out.println("\nTASK 1 - Installation and use of the provided implementation\n");

            Graph romeniaGraph = Romenia.defineGraph();
            romeniaGraph.showLinks();
            romeniaGraph.showSets();
            Node n;
            n = romeniaGraph.searchSolution("Arad", "Bucharest", Algorithms.BreadthFirstSearch);
            romeniaGraph.showSolution(n);

            return romeniaGraph;
        }

    }

    static class Task2 {
        public static void execute(Graph romeniaGraph) {
            System.out.println("\nTASK 2 - Comparison between different search algorithms\n");

            String[][] testCases = {
                    {"Arad", "Bucharest"},
                    {"Bucharest", "Oradea"},
                    {"Oradea", "Bucharest"},
                    {"Timisoara", "Neamt"}
            };

            Algorithms[] algorithms = {
                    Algorithms.BreadthFirstSearch,
                    Algorithms.DepthFirstSearch,
                    Algorithms.UniformCostSearch,
                    Algorithms.GreedySearch,
                    Algorithms.AStarSearch
            };

            // loop for every algorithm
            for (Algorithms algorithm : algorithms) {
                System.out.println("\n---" + algorithm + "---\n");

                // loop for selected paths
                for (String[] testCase : testCases) {
                    Node result = romeniaGraph.searchSolution(testCase[0], testCase[1], algorithm);
                    romeniaGraph.showSolution(result);
                }
            }
        }
    }

    static class Task3 {
        public static void execute(Graph romeniaGraph, String origin, String destination, String province, Algorithms algorithm) {
            System.out.println("\nTASK 3 - Problems with mandatory passage through a province\n");
            Node result = romeniaGraph.searchSolutionProvince(origin, destination, province, algorithm);
            romeniaGraph.showSolution(result);
        }
    }
}
