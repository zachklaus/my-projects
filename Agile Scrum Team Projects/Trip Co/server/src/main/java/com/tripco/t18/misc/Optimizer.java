package com.tripco.t18.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.IntStream;

public class Optimizer {
    // Available Optimizations
    public enum Optimization {
        NONE("none"),
        SHORT("short"),
        SHORTER("shorter");

        private String text;

        Optimization(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static Optimization fromString(String text) {
            for (Optimization o : Optimization.values()) {
                if (o.text.equalsIgnoreCase(text)) {
                    return o;
                }
            }
            if(text.isEmpty()){
                return NONE;
            }
            throw new IllegalArgumentException("No Optimization with text " + text + " available.");
        }
    }

    // The chosen optimization
    private Optimization optimization;
    private ArrayList<Map> places;
    private final long[][] distances;
    // A 'tour' consists of the indexes of the locations visited.
    private int[] optimizedTour;
    private double earthRadius;

    public Optimizer(Optimization optimization, ArrayList<Map> places, double earthRadius) {
        this.optimization = optimization;
        this.places = places;
        if(optimization != Optimization.NONE) {
            this.distances = setupDistances(places, earthRadius);
        } else {
            this.distances = null;
        }
        this.earthRadius = earthRadius;
    }

    // Public Functions
    public ArrayList<Map> buildOptimizedTrip() {
        if(places.isEmpty()){
            return places;
        }

        switch (optimization) {
            case SHORTER:
                // Do 2-OPT/TwoOpt
                getShortestTrip(true);
                break;
            case SHORT:
                // Just NearestNeighbor
                getShortestTrip(false);
                break;
            case NONE:
                // No optimization -> No changes to list;
                return places;
        }
        return buildOptimumTour();
    }

    // Optimization Algorithms
    private void getShortestTrip(boolean doTwoOpt) {
        // A great place to make a parallel stream instead of forEach!
        // For Each Starting location
        int[] shortestTour = IntStream.range(0, places.size()) // For each starting point
                .parallel() // Use the godliness of Java 8 to spawn a thread-pool and do everything for us.
                .mapToObj(this::buildNearestNeighborPath) // Build a nearest neighbor trip
                .map((trip) -> doTwoOpt ? twoOptShorten(trip) : trip) // Do 2-Opt if necessary
                .min(Comparator.comparingLong(this::getTourDistance)) // Choose the shortest trip
                .orElse(new int[0]);
        optimizedTour = rearrangeStartPoint(shortestTour);
    }

    // Performs a simple array 'rotation' so that our tour starts at the beginning.
    private int[] rearrangeStartPoint(int[] tour){
        int start = 0;
        for(int i = 0; i < tour.length; i++){
            if(tour[i] == 0){
                start = i;
                break;
            }
        }

        int[] rearrangedTour = new int[tour.length];
        for(int i = 0; i < tour.length; i++){
            rearrangedTour[i] = tour[(start + i) % tour.length];
        }
        return rearrangedTour;
    }

    private int[] buildNearestNeighborPath(int startingPoint){
        boolean[] visitedList = new boolean[places.size()];
        int[] trip = new int[places.size()];

        trip[0] = startingPoint;
        visitedList[startingPoint] = true;

        int lastPoint = startingPoint;
        for(int i = 1; i < trip.length; i++){
            int nearestNeighbor = findNearestTo(lastPoint, visitedList);
            visitedList[nearestNeighbor] = true;
            trip[i] = nearestNeighbor;
            lastPoint = nearestNeighbor;
        }
        return trip;
    }

    private int findNearestTo(int place, boolean[] visited){
        long min = Long.MAX_VALUE;
        int minNeighbor = 0;

        for(int neighbor = 0; neighbor < distances[place].length; neighbor++){
            if(!visited[neighbor] && neighbor != place && distances[place][neighbor] < min){
                min = distances[place][neighbor];
                minNeighbor = neighbor;
            }
        }
        return minNeighbor;
    }

    // Variable names are identical to the Pseudo Code from the guide.
    public int[] twoOptShorten(int[] tour) {
        int n = tour.length;
        boolean improvement = true;
        while (improvement) {
            improvement = false;
            for (int i = 0; i <= n - 3; i++) {
                for (int k = i + 2; k <= n - 1; k++) {
                    long delta = crossoverDistance(tour, i, k);
                    if (delta < 0) {
                        reverseTourSection(i + 1, k, tour);
                        improvement = true;
                    }
                }
            }
        }
        return tour;
    }

    // Helpers
    private ArrayList<Map> buildOptimumTour() {
        ArrayList<Map> optimizedPlaces = new ArrayList<>();
        for (int place : optimizedTour) {
            optimizedPlaces.add(places.get(place));
        }

        return optimizedPlaces;

    }

    public long getTourDistance(int[] tour) {
        int size = tour.length;
        long totalDistance = 0;
        for (int i = 0; i < tour.length; i++) {
            totalDistance += distances[tour[i%size]][tour[(i+1)%size]];
        }
        return totalDistance;
    }

    private long[][] setupDistances(ArrayList<Map> places, double earthRadius) {
        long[][] tempDistances = new long[places.size()][places.size()];
        for (int i = 0; i < tempDistances.length; i++) {
            for (int j = 0; j < tempDistances[0].length; j++) {
                tempDistances[i][j] = GreatCircleDistance.calculate(places.get(i), places.get(j), earthRadius);
            }
        }
        return tempDistances;
    }

    private long crossoverDistance(int[] tour, int i, int k) {
        int size = tour.length;
        long distance = 0;
        distance -= distances[tour[i]][tour[(i + 1) % size]];
        distance -= distances[tour[k]][tour[(k + 1) % size]];
        distance += distances[tour[i]][tour[(k) % size]];
        distance += distances[tour[i + 1]][tour[(k + 1) % size]];
        return distance;
    }

    private static void reverseTourSection(int begin, int end, int[] tour) {
        while (begin < end) {
            int temp = tour[begin];
            tour[begin] = tour[end];
            tour[end] = temp;
            begin++;
            end--;
        }
    }


    // Getters

    public Optimization getOptimization() {
        return optimization;
    }

    public ArrayList<Map> getPlaces() {
        return places;
    }

    public long[][] getDistances() {
        return distances;
    }

    public int[] getOptimizedTour() {
        return optimizedTour;
    }

    public double getEarthRadius() {
        return earthRadius;
    }
}
