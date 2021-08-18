// GraphImplementation.java - supplied code for graph assignment
// Author: Zachary Klausner
// Date:   4/23/2017
// Class:  CS165
// Email:  zachklau@rams.colostate.edu

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class GraphImplementation extends GraphAbstract {

	public int[] minDistances;
	public GraphNode[] previous;
	
    // Main entry point
    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("usage: java GraphImplementation <distanceFile> <graphFile>");
            System.exit(-1);
        }
        
        // Instantiate code
        GraphImplementation impl = new GraphImplementation();

        // Read distances chart
        System.out.println("Reading Chart: " + args[0]);
        impl.readGraph(args[0]);
        
        // Write distances graph
        System.out.println("Writing Graph: " + args[1]);
        impl.writeGraph(args[1]);
        
        // Print depth first search
        System.out.println("Depth First Search:");
        impl.depthFirst("Fort Collins");
        
        // Print breadth first search
        System.out.println("Breadth First Search:");
        impl.breadthFirst("Fort Collins");

        // Print all shortest paths
        for (int from = 0; from < cities.size(); from++) 
            for (int to = 0; to < cities.size(); to++)
                if (from != to) {
                    String fromCity = cities.get(from).name;
                    String toCity = cities.get(to).name;
                    System.out.print("Shortest Path: ");
                    impl.shortestPath(fromCity, toCity);
                }
    }

    // Reads mileage chart from CSV file
    public void readGraph(String filename) {
        
        // YOUR CODE HERE
    	ArrayList<String> contents = readFile(filename);
    	ArrayList<String> cityNames = new ArrayList<String>(Arrays.asList(contents.get(0).split(",")));
    	cityNames.remove(0);
    	for (String city: cityNames) {
    		GraphNode node = new GraphNode(city);
    		cities.add(node);
    	}
    	for (int i = 0, j = 1; i < cities.size(); i++, j++) {
    		ArrayList<String> distances = new ArrayList<String>(Arrays.asList(contents.get(j).split(",")));
    		distances.remove(0);
    		for (int k = 0; k < distances.size(); k++) {
    			String temp = distances.get(k);
    			if (temp.isEmpty()) {
    				continue;
    			}
    			else {
    				GraphEdge edge0 = new GraphEdge(i, k, Integer.parseInt(temp));
    				cities.get(i).edges.add(edge0);
    				GraphEdge edge1 = new GraphEdge(k, i, Integer.parseInt(temp));
    				cities.get(k).edges.add(edge1);
    				if (!mileages.contains(edge0)) {
    					mileages.add(edge0);
    				}
    			}
    		}
    	}
    	Collections.sort(mileages);
    }

    // Write graph in dot format
    public void writeGraph(String filename) {

        ArrayList<String> output = new ArrayList<>();
        output.add("digraph BST {");
        output.add("    ratio = 1.0;");
        output.add("    node [style=filled]");
        output.add("    node [fillcolor=darkslateblue]");
        output.add("    node [fixedsize=true]");
        output.add("    node [shape=oval]");
        output.add("    node [width=6;]");
        output.add("    node [height=4;]");
        output.add("    node [fontname=Arial]");
        output.add("    node [fontsize=60]");
        output.add("    node [fontcolor=white]");
        output.add("    edge [dir=none]");
        output.add("    edge [penwidth=24]");
        output.add("    edge [fontname=Arial]");
        output.add("    edge [fontsize=110]");
        
        // YOUR CODE HERE
        for (int i = 0; i < cities.size(); i++) {
        	output.add("    Node" + i + " " + "[label=\"" + cities.get(i).name + "\"];");
        }
        for (int i = 0; i < mileages.size(); i++) {
        	output.add("    Node" + mileages.get(i).fromIndex + " -> Node" + mileages.get(i).toIndex + " [label" +
        					"=\"" + mileages.get(i).mileage + "\";color=\"" + getColor(mileages.get(i).mileage) +
        					 "\";]");
        }
        output.add("}");
        // Write distances graph
        writeFile(filename, output);
    }


    // Print graph in depth first search (DFS) order
    public void depthFirst(String startCity) {
        
        // YOUR CODE HERE
    	ArrayList<Integer> visited = new ArrayList<Integer>();
    	for (int i = 0; i < cities.size(); i++) {
    		visited.add(0);
    	}
    	GraphNode city = getCity(startCity);
    	depthFirst(cities.indexOf(city), visited);

    }
    // Recursive helper method
    public void depthFirst(int index, ArrayList<Integer> visited) {

        // YOUR CODE HERE
    	visited.set(index, 1);
    	GraphNode current = cities.get(index);
    	ArrayList<GraphEdge> edgesTemp = current.edges;
    	Collections.sort(edgesTemp);
    	System.out.println("Visited " + cities.get(index).name);
    	for (GraphEdge edge: edgesTemp) {
    		if (visited.get(edge.toIndex) == 0) {
    			depthFirst(edge.toIndex, visited);
    		}
    	}
 
    }

    // Calculate and print shortest path
    public void shortestPath(String fromCity, String toCity) {

        // YOUR CODE HERE
    	computePaths(fromCity);
    	int shortestDistance = minDistances[cities.indexOf(getCity(toCity))];
    	System.out.println(getShortestPathTo(getCity(toCity)) + " (Mileage " + shortestDistance + ")");
    	
    }
    
    //
    // Helper functions
    //

	public void computePaths(String fromCity) {

		minDistances = new int[cities.size()];
		for (int i = 0; i < minDistances.length; i++) {
			minDistances[i] = Integer.MAX_VALUE;
		}
		previous = new GraphNode[cities.size()];
		for (int i = 0; i < previous.length; i++) {
			previous[i] = null;
		}
		minDistances[cities.indexOf(getCity(fromCity))] = 0;
		LinkedList<GraphNode> q = new LinkedList<GraphNode>();
		q.add(getCity(fromCity));
		while (!q.isEmpty()) {
			GraphNode u = q.poll();
			ArrayList<GraphEdge> edgesTemp = u.edges;
			Collections.sort(edgesTemp);
			for (GraphEdge e : edgesTemp) {
				GraphNode v = cities.get(e.toIndex);
				double weight = e.mileage;
				double distanceThroughU = minDistances[cities.indexOf(u)] + weight;
				if (distanceThroughU < minDistances[cities.indexOf(v)]) {
					q.remove(v);
					minDistances[cities.indexOf(v)] = (int) Math.ceil(distanceThroughU);
					previous[cities.indexOf(v)] = u;
					q.add(v);
				}
			}
		}
	}

	public ArrayList<String> getShortestPathTo(GraphNode target) {
		
		ArrayList<GraphNode> path = new ArrayList<GraphNode>();
		ArrayList<String> names = new ArrayList<String>();
		for (GraphNode vertex = target; vertex != null; vertex = previous[cities.indexOf(vertex)])
			path.add(vertex);
		Collections.reverse(path);
		for (GraphNode node: path)
			names.add(node.name);
		return names;
	}

	// Read contents of file to arraylist
	private static ArrayList<String> readFile(String filename) {
		ArrayList<String> contents = new ArrayList<>();
		try {
			Scanner reader = new Scanner(new File(filename));
			while (reader.hasNextLine()) {
				String line = reader.nextLine().trim();
				if (!line.isEmpty())
					contents.add(line);
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Cannot read chart: " + filename);
		}
		return contents;
	}

    // Write contents of arraylist to file
    private static void writeFile(String filename, ArrayList<String> contents) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            for (String line : contents)
                writer.println(line);
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write graph: " + filename);
        }
    }
    
    private static String getColor(int mileage) {
    	
    	if (mileage <= 100) 
    		return "green";
    	else if (mileage <= 200)
    		return "blue";
    	else if (mileage <= 300)
    		return "magenta";
    	else
    		return "red";
    	
    }

	@Override
	public void breadthFirst(String startCity) {
		
		boolean[] visited = new boolean[cities.size()];
		for (int i = 0; i < visited.length; i++)
			visited[i] = false;
		LinkedList<GraphNode> q = new LinkedList<GraphNode>(); 
		q.add(getCity(startCity));
		System.out.println("Visited " + q.getLast().name);
		visited[cities.indexOf(getCity(startCity))] = true;
		while (!q.isEmpty()) {
			GraphNode c0 = q.getLast();
			q.removeLast();
			ArrayList<GraphEdge> edgesTemp = new ArrayList<GraphEdge>();
			edgesTemp = c0.edges;
			Collections.sort(edgesTemp);
			visited[cities.indexOf(c0)] = true;
			for (GraphEdge edge: edgesTemp) {
				if (visited[edge.toIndex] == false) {
					System.out.println("Visited " + cities.get(edge.toIndex).name);
					GraphNode c1 = cities.get(edge.toIndex);
					q.addFirst(c1);
					visited[edge.toIndex] = true;
				}
			}
		}
	}
	
	public GraphNode getCity(String cityName) {
		
		GraphNode city = null;
		for (int i = 0; i < cities.size(); i++) {
			if (cities.get(i).name.equals(cityName)) {
				city = cities.get(i);
			}
		}
		return city;
	}
	
	public GraphEdge findClosest(ArrayList<GraphEdge> edges) {
		
		GraphEdge closest = null;
		int distance = edges.get(0).mileage;
		for (GraphEdge edge: edges) {
			if (edge.mileage < distance) {
				distance = edge.mileage;
				closest = edge;
			}
		}
		return closest;
	}
	
}
