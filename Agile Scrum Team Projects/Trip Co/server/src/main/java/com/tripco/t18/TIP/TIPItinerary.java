package com.tripco.t18.TIP;

import com.tripco.t18.misc.GreatCircleDistance;
import com.tripco.t18.misc.Optimizer;

import java.util.Map;
import java.util.ArrayList;


public class TIPItinerary extends TIPHeader {
    private Map options;
    private ArrayList<Map> places;
    private ArrayList<Long> distances;

    //private final transient Logger log = LoggerFactory.getLogger(TIPItinerary.class);


    TIPItinerary(int version, Map options, ArrayList<Map> places) {
        this();
        this.requestVersion = version;
        this.options = options;
        this.places = places;
    }


    private TIPItinerary() {
        this.requestType = "itinerary";
    }


    @Override
    public void buildResponse() {
        double earthRadius = getEarthRadius();

        String optimizationLevel = (String) this.options.get("optimization");
        Optimizer optimizer = new Optimizer(Optimizer.Optimization.fromString(optimizationLevel), places, earthRadius);
        places = optimizer.buildOptimizedTrip();

        this.distances = calculateDistances(earthRadius);
    }

    //calculates the distances given the latitudes, longitudes, and earth radius
    private ArrayList<Long> calculateDistances(double earthRadius) {
        ArrayList<Long> distanceArray = new ArrayList<Long>();
        for (int i = 0; i < places.size(); i++) {
            long distance;
            if (i == places.size() - 1)
                distance = GreatCircleDistance.calculate(places.get(places.size() - 1), places.get(0), earthRadius);
            else
                distance = GreatCircleDistance.calculate(places.get(i), places.get(i+1), earthRadius);
            distanceArray.add(distance);
        }
        return distanceArray;
    }

    //gets the earth radius from the options map
    private double getEarthRadius() {
        return Double.parseDouble((String) this.options.get("earthRadius"));
    }

    ArrayList<Long> getDistances() {
        return this.distances;
    }

    ArrayList<Map> getPlaces() {
        return this.places;
    }

    public String toString() {
        return "";
    }

}
