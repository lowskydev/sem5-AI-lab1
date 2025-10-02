import searchalgorithm.Algorithms;
import searchalgorithm.Node;

import undirectedgraph.Graph;
import undirectedgraph.Romenia;
import undirectedgraph.Vertex;
import undirectedgraph.VertexSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nSelect a task to execute:");
            System.out.println("  1) Task 1 - Installation and use of the provided implementation");
            System.out.println("  2) Task 2 - Comparison between different search algorithms");
            System.out.println("  3) Task 3 - Problems with mandatory passage through a province");
            System.out.println("  4) Task 4 - Problems with mandatory passage through a sequence of provinces");
            System.out.println("  5) Run all with defaults");
            System.out.print("[1-5]: ");
            int taskChoice = scanner.nextInt();

            switch (taskChoice) {
                case 1 -> {
                    Task1.init();
                }
                case 2 -> {
                    Task2.execute();
                }
                case 3 -> {
                    System.out.println("\tSelect option:");
                    System.out.println("\t  1) Default arguments");
                    System.out.println("\t  2) Custom arguments");
                    System.out.print("\t[1-2]: ");

                    int argChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    switch (argChoice) {
                        case 1 -> Task3.execute("Arad", "Bucharest", "Dobrogea", Algorithms.AStarSearch);
                        case 2 -> {
                            System.out.print("\t\t[originCity] [destinationCity] [province]: ");
                            String[] arguments = scanner.nextLine().split(" ");

                            if (arguments.length < 3) {
                                System.out.println("Not enough arguments provided");
                            } else {
                                String origin = arguments[0];
                                String destination = arguments[1];
                                String province = arguments[2];

                                Algorithms algorithm = selectAlgorithm(scanner);
                                Task3.execute(origin, destination, province, algorithm);
                            }
                        }
                    }
                }

                case 4 -> {
                    System.out.println("\tSelect option:");
                    System.out.println("\t  1) Default arguments");
                    System.out.println("\t  2) Custom arguments");
                    System.out.print("\t[1-2]: ");

                    int argChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    switch (argChoice) {
                        case 1 -> Task4.execute("Arad", "Bucharest", new String[]{"Dobrogea", "d"}, Algorithms.AStarSearch);
                        case 2 -> {
                            System.out.print("\t\t[originCity] [destinationCity] [province1,province2,...]: ");
                            String[] arguments = scanner.nextLine().split(" ");

                            if (arguments.length < 3) {
                                System.out.println("Not enough arguments provided");
                            } else {
                                String origin = arguments[0];
                                String destination = arguments[1];
                                String[] provinces = arguments[2].split(",");

                                Algorithms algorithm = selectAlgorithm(scanner);
                                Task4.execute(origin, destination, provinces, algorithm);
                            }
                        }
                    }
                }

                case 5 -> {
                    Task1.init();
                    Task2.execute();
                    Task3.execute( "Arad", "Bucharest", "Dobrogea", Algorithms.AStarSearch);
                    Task4.execute("Arad", "Bucharest", new String[]{"Dobrogea", "d"}, Algorithms.AStarSearch);
                }
                default -> System.out.println("Invalid option.");
            }
        }

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

    private static Algorithms selectAlgorithm(Scanner scanner) {
        System.out.println("\t\tSelect algorithm:");
        System.out.println("\t\t  1) Breadth First Search");
        System.out.println("\t\t  2) Depth First Search");
        System.out.println("\t\t  3) Uniform Cost Search");
        System.out.println("\t\t  4) Greedy Search");
        System.out.println("\t\t  5) A* Search");
        System.out.print("\t\t[1-5]: ");

        int algChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        return switch (algChoice) {
            case 1 -> Algorithms.BreadthFirstSearch;
            case 2 -> Algorithms.DepthFirstSearch;
            case 3 -> Algorithms.UniformCostSearch;
            case 4 -> Algorithms.GreedySearch;
            case 5 -> Algorithms.AStarSearch;
            default -> {
                System.out.println("Invalid option. Using default A* Search.");
                yield Algorithms.AStarSearch;
            }
        };
    }
}
