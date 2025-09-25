import java.util.Scanner;
import searchalgorithm.Algorithms;
import searchalgorithm.Node;
import undirectedgraph.Graph;
import undirectedgraph.Romenia;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose a task:");
            System.out.println("1 - Installation");
            System.out.println("2 - Comparison between different search algorithms");
            System.out.println("3 - Problems with mandatory passage through a province");
            System.out.println("4 - Problems with mandatory passage through a sequence of provinces");
            System.out.println("0 - Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    installation();
                    break;
                case 2:
                    comparisonTask();
                    break;
                case 3:
                    mandatoryProvinceTask();
                    break;
                case 4:
                    mandatorySequenceTask();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void installation() {
        Graph graph = Romenia.defineGraph();
        graph.showLinks();
        graph.showSets();
        Node n = graph.searchSolution("Arad", "Bucharest", Algorithms.BreadthFirstSearch);
        graph.showSolution(n);
    }


    public static void comparisonTask() {
        Graph graph = Romenia.defineGraph();
        graph.showLinks();
        graph.showSets();
        System.out.println("\nComparison between different search algorithms\n");

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

    static void mandatoryProvinceTask() {
        Graph graph = Romenia.defineGraph();

        System.out.println("\nProblems with mandatory passage through a province\n");
        Node result = graph.searchSolution("Arad", "Bucharest", Algorithms.AStarSearch, "Dobrogea");
        graph.showSolution(result);
    }

    static void mandatorySequenceTask() {
        System.out.println("Problems with mandatory passage through a sequence of provinces not implemented yet.");
    }
}