import searchalgorithm.Algorithms;
import searchalgorithm.Node;

import undirectedgraph.Graph;
import undirectedgraph.Romenia;
import undirectedgraph.Vertex;
import undirectedgraph.VertexSet;

public class Main {
    public static void main(String[] args) {
        Graph romeniaGraph = Romenia.defineGraph();
        romeniaGraph.showLinks();
        romeniaGraph.showSets();
        Node n;
        n = romeniaGraph.searchSolution("Arad", "Bucharest", Algorithms.BreadthFirstSearch);
        romeniaGraph.showSolution(n);

        Task1.execute(romeniaGraph);

        String origin= "Arad";
        String destination= "Bucharest";
        String province = "Dobrogea";
        Task2.execute(romeniaGraph, origin, destination, province);
    }

    static class Task1 {
        public static void execute(Graph romeniaGraph) {
            System.out.println("\nTASK 1 - Comparison between different search algorithms\n");

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

    static class Task2 {
        public static void execute(Graph romeniaGraph, String origin, String destination, String province) {
            Node result = romeniaGraph.searchSolutionProvince(origin, destination, province, Algorithms.AStarSearch);
            romeniaGraph.showSolution(result);
        }
    }
}
