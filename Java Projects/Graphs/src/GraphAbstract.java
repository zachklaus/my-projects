// GraphAbstract.java - abstract class for graph assignment
// Author: Chris Wilcox
// Date:   4/16/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu

import java.util.ArrayList;

public abstract class GraphAbstract {

    // Represents a graph edge
    public class GraphEdge implements Comparable<GraphEdge> {
        public int fromIndex; // index of "from" city
        public int toIndex; // index of "to" city
        public int mileage; // mileage between cities
        public GraphEdge (int from, int to, int mileage) {
            this.fromIndex = from;
            this.toIndex = to;
            this.mileage = mileage;
        }
        public int compareTo(GraphEdge edge) {
            return this.mileage - edge.mileage;
        }
        public String toString() {
        	
        	return cities.get(toIndex).name;
        	
        }
    }

    // Represents a graph node (and incident edges)
    public class GraphNode {
        public String name; // City name
        public ArrayList<GraphEdge> edges; // Distances
        public GraphNode(String name) {
            this.name = name;
            edges = new ArrayList<>();
        }
        public boolean equals(Object object) {
            if (object instanceof GraphNode)
                return ((GraphNode)object).name.equals(this.name);
            else
                return false;
        }
        
        public String toString() {
        	return name;
        }
        
    }
    
    // Graph data structures
    public static ArrayList<GraphNode> cities = new ArrayList<>();
    public static ArrayList<GraphEdge> mileages = new ArrayList<>();

    // Reads mileage chart from CSV file
    public abstract void readGraph(String filename);

    // Writes mileage graph to DOT file
    public abstract void writeGraph(String filename);
    
    // Print graph in depth first search order
    public abstract void depthFirst(String startCity);

    // Print graph in breadth first search order
    public abstract void breadthFirst(String startCity);

    // Calculate and print shortest path
    public abstract void shortestPath(String fromCity, String toCity);
}
