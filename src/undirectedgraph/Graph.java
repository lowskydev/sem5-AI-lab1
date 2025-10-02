package undirectedgraph;

import java.util.*;

import searchalgorithm.*;
import searchproblem.SearchProblem;
import searchproblem.State;

public class Graph {
	private HashMap<String,Vertex> vertices;
	private HashMap<Integer,Edge> edges;	
	private ArrayList<VertexSet> vSets;
	
	private long expansions;
	private long generated;
	private long repeated;
	private double time;
	
	public Graph() {
		this.vertices = new HashMap<>();
		this.edges = new HashMap<>();
		this.vSets = new ArrayList<>();
		this.expansions = 0;
		this.generated = 0;
		this.repeated = 0;
		this.time = 0;
	}
	
	public void addVertex(String label, double lat, double lng) {
		Vertex v =  new Vertex(label);
		this.vertices.put(label, v);
		v.setCoordinates(lat, lng);
	}
	
	public Vertex getVertex(String label) {
		return this.vertices.get(label);
	}
	
	public void addVertexSet(String label) {
		VertexSet vSet =  new VertexSet(label);
		this.vSets.add(vSet);
	}
	
	public VertexSet getVertexSet(String setLabel) {
		for (VertexSet vSet : vSets) {
			if (Objects.equals(vSet.getLabel(), setLabel))
				return vSet;
		}
		return null;
	}
	
	public void addVertexToSet(String labelSet, String labelVertex) {
		Vertex v = this.vertices.get(labelVertex);
		for (VertexSet vSet : vSets) {
			if (Objects.equals(vSet.getLabel(), labelSet)) {
				vSet.addVertex(v);
				break;
			}
		}
	}
	
	public boolean addEdge(Vertex one, Vertex two, double weight) {
		if (two.equals(one)) return false;
		Edge e = new Edge(one,two,weight);
		if (edges.containsKey(e.hashcode())) return false;
		if (one.containsNeighbor(e) || two.containsNeighbor(e))  return false;	
		edges.put(e.hashcode(), e);
		one.addNeighbor(e);
		two.addNeighbor(e);
		return true;
	} 
	
	public boolean addEdge(String oneLabel, String twoLabel, double weight) {
		Vertex one = getVertex(oneLabel);
		Vertex two = getVertex(twoLabel);
		return addEdge(one,two,weight);
	}
	
	public boolean addEdge(String oneLabel, String twoLabel) {
		Vertex one = getVertex(oneLabel);
		Vertex two = getVertex(twoLabel);
		return addEdge(one,two,one.straightLineDistance(two));
	}

	public void showLinks() {
		System.out.println("********************* LINKS *********************");
		for (Vertex current: vertices.values()) {		
			System.out.print(current + ":" + " ");
			for (Edge e: current.getNeighbors()) {
				System.out.print(e.getNeighbor(current) + " (" + e.getWeight() + "); ");
			}
			System.out.println();
		}
		System.out.println("*************************************************");
	}
	
	public void showSets() {
		System.out.println("********************* SETS *********************");
		for (VertexSet vSet: vSets) {
			System.out.println(vSet);
		}
		System.out.println("*************************************************");
	}
	
	public Node searchSolution(String initLabel, String goalLabel, Algorithms algID) {
		State init = new State(this.getVertex(initLabel));
		State goal = new State(this.getVertex(goalLabel));
		SearchProblem prob = new SearchProblem(init,goal);
		SearchAlgorithm alg = null;
		switch (algID) {
			case BreadthFirstSearch -> alg = new BreadthFirstSearch(prob);
			case DepthFirstSearch -> alg = new DepthFirstSearch(prob);
			case UniformCostSearch -> alg = new UniformCostSearch(prob);
			case GreedySearch -> alg = new GreedySearch(prob);
			case AStarSearch -> alg = new AStarSearch(prob);
			default -> System.out.println("This algorithm is not implemented yet!");
		}
		Node n = alg.searchSolution();	
		Map<String,Number> m = alg.getMetrics();
		this.expansions += (long)m.get("Node Expansions");
		this.generated += (long)m.get("Nodes Generated");
		this.repeated += (long)m.get("State repetitions");
		this.time += (double)m.get("Runtime (ms)");
		return n;
	}

	public Node searchSolution(String initLabel, String goalLabel, String provinceLabel, Algorithms algID) {
		Graph auxiliaryGraph = new Graph();

		Vertex origin = getVertex(initLabel);
		Vertex destination = getVertex(goalLabel);

		// add origin and destination to the graph
		auxiliaryGraph.addVertex(origin.getLabel(), origin.getLatitude(), origin.getLongitude());
		auxiliaryGraph.addVertex(destination.getLabel(), destination.getLatitude(), destination.getLongitude());

		// get all the cities in the province
		VertexSet province = getVertexSet(provinceLabel);
		for (Vertex city: province.getVertices()) {
			auxiliaryGraph.addVertex(city.getLabel(), city.getLatitude(), city.getLongitude());
		}

		// add edge from origin to province cities with appropriate cost
		for (Vertex city: province.getVertices()) {
			Node n = searchSolution(origin.getLabel(), city.getLabel(), algID);
			auxiliaryGraph.addEdge(origin.getLabel(), city.getLabel(), n.getPathCost());
		}

		// add edge from province to destination cities with appropriate cost
		for (Vertex city: province.getVertices()) {
			Node n = searchSolution(city.getLabel(), destination.getLabel(), algID);
			auxiliaryGraph.addEdge(city.getLabel(), destination.getLabel(), n.getPathCost());
		}

		Node result = auxiliaryGraph.searchSolution(origin.getLabel(), destination.getLabel(), algID);

		// get metrics from auxGraph and add them to the current graph
		this.expansions += auxiliaryGraph.expansions;
		this.generated += auxiliaryGraph.generated;
		this.repeated += auxiliaryGraph.repeated;
		this.time += auxiliaryGraph.time;

		return result;
	}

	public Node searchSolution(String initLabel, String goalLabel, String[] provinceLabel, Algorithms algID) {
		Graph auxiliaryGraph = new Graph();

		Vertex origin = getVertex(initLabel);
		Vertex destination = getVertex(goalLabel);

		// add origin and destination to the graph
		auxiliaryGraph.addVertex(origin.getLabel(), origin.getLatitude(), origin.getLongitude());
		auxiliaryGraph.addVertex(destination.getLabel(), destination.getLatitude(), destination.getLongitude());

		// add provinces to the graph
		for (String province: provinceLabel) {
			VertexSet provinceSet = getVertexSet(province);
			for (Vertex city: provinceSet.getVertices()) {
				auxiliaryGraph.addVertex(city.getLabel(), city.getLatitude(), city.getLongitude());
			}
		}

		// add edge from origin to first province cities with appropriate cost
		VertexSet firstProvince = getVertexSet(provinceLabel[0]);
		for (Vertex city: firstProvince.getVertices()) {
			Node n = searchSolution(origin.getLabel(), city.getLabel(), algID);
			auxiliaryGraph.addEdge(origin.getLabel(), city.getLabel(), n.getPathCost());
		}

		// add edges between provinces
		for (int i = 0; i < provinceLabel.length - 1; i++) {
			VertexSet currentProvince = getVertexSet(provinceLabel[i]);
			VertexSet nextProvince = getVertexSet(provinceLabel[i + 1]);
			for (Vertex cityCurrent: currentProvince.getVertices()) {
				for (Vertex cityNext: nextProvince.getVertices()) {
					Node n = searchSolution(cityCurrent.getLabel(), cityNext.getLabel(), algID);
					auxiliaryGraph.addEdge(cityCurrent.getLabel(), cityNext.getLabel(), n.getPathCost());
				}
			}
		}

		// add edge from last province to destination cities with appropriate cost
		VertexSet lastProvince = getVertexSet(provinceLabel[provinceLabel.length - 1]);
		for (Vertex city: lastProvince.getVertices()) {
			Node n = searchSolution(city.getLabel(), destination.getLabel(), algID);
			auxiliaryGraph.addEdge(city.getLabel(), destination.getLabel(), n.getPathCost());
		}
		Node result = auxiliaryGraph.searchSolution(origin.getLabel(), destination.getLabel(), algID);

		// get metrics from auxGraph and add them to the current graph
		this.expansions += auxiliaryGraph.expansions;
		this.generated += auxiliaryGraph.generated;
		this.repeated += auxiliaryGraph.repeated;
		this.time += auxiliaryGraph.time;
		return result;
	}

	public void showSolution(Node n) {
		System.out.println("******************* SOLUTION ********************");
		System.out.println("Node Expansions: " + this.expansions);
		System.out.println("Nodes Generated: " + this.generated);
		System.out.println("State Repetitions: " + this.repeated);
		System.out.printf("Runtime (ms): %6.3f \n",this.time);
		Node ni;
		List<Object> solution = n.getPath();
		double dist = 0;
		for (int i = 0; i<solution.size()-1;i++) {
			System.out.printf("| %-9s | %4.0f | ",solution.get(i), dist);
			ni = searchSolution(solution.get(i).toString(), solution.get(i+1).toString(), Algorithms.AStarSearch);
			System.out.print(ni.getPath());	
			System.out.println(" -> " + (int)ni.getPathCost());
			dist += ni.getPathCost();
		}
		System.out.printf("| %-9s | %4.0f | \n",solution.get(solution.size()-1), dist);
		System.out.println("*************************************************");
	}
	
}
