import searchalgorithm.Algorithms;
import searchalgorithm.Node;

import undirectedgraph.Graph;
import undirectedgraph.Romenia;
import undirectedgraph.Vertex;
import undirectedgraph.VertexSet;

public class Main {
    public static void main(String[] args) {
        Task1.init();
        Task2.execute();
        Task3.execute( "Arad", "Bucharest", "Dobrogea", Algorithms.AStarSearch);
        Task4.execute("Arad", "Bucharest", new String[]{"Dobrogea", "d"}, Algorithms.AStarSearch);
    }

    static class Task1 {
        public static void init() {
            System.out.println("\nTASK 1 - Installation and use of the provided implementation\n");

            Graph romeniaGraph = Romenia.defineGraph();
            romeniaGraph.showLinks();
            romeniaGraph.showSets();
            Node n;
            n = romeniaGraph.searchSolution("Arad", "Bucharest", Algorithms.BreadthFirstSearch);
            romeniaGraph.showSolution(n);
        }

    }

    static class Task2 {
        public static void execute() {
            System.out.println("\nTASK 2 - Comparison between different search algorithms\n");

            Graph romeniaGraph = Romenia.defineGraph();

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
        public static void execute(String origin, String destination, String province, Algorithms algorithm) {
            System.out.println("\nTASK 3 - Problems with mandatory passage through a province\n");

            Graph romeniaGraph = Romenia.defineGraph();

            Node result = romeniaGraph.searchSolution(origin, destination, province, algorithm);
            romeniaGraph.showSolution(result);
        }
    }

    static class Task4 {
        public static void execute (String origin, String destination, String[] provinces, Algorithms algorithm) {
            System.out.println("\nTASK 4 - Problems with mandatory passage through multiple provinces\n");

            Graph romeniaGraph = Romenia.defineGraph();
            Node result = romeniaGraph.searchSolution(origin, destination, provinces, algorithm);
            romeniaGraph.showSolution(result);
        }
    }
}
