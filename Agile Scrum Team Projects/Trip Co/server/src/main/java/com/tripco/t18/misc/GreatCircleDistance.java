package com.tripco.t18.misc;

import java.lang.Math;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Determines the distance between geographic coordinates.
 */
public class GreatCircleDistance {

    //variables to hold data for logging purposes
    private static Double originLat;
    private static Double destinationLat;
    private static Double originLong;
    private static Double destinationLong;
    private static Double radius;
    private static double distance;


    private static final transient Logger log = LoggerFactory.getLogger(GreatCircleDistance.class);

    public static long calculate(Map origin, Map destination, double earthRadius){
        String lat1 = (String)origin.get("latitude");
        String long1 = (String)origin.get("longitude");
        String lat2 = (String)destination.get("latitude");
        String long2 = (String)destination.get("longitude");

        double latitude1 = Math.toRadians(Double.parseDouble(lat1));
        double latitude2 = Math.toRadians(Double.parseDouble(lat2));
        double longitude1 = Math.toRadians(Double.parseDouble(long1));
        double longitude2 = Math.toRadians(Double.parseDouble(long2));

        double deltaSigma = arcLengthBetween(latitude1, longitude1, latitude2, longitude2);

        double distance = deltaSigma * earthRadius;

        //logging
        String newLog = "inputs: origin latitude = " + lat1 + ", origin longitude = " + long1
                + ", destination latitude = " + lat2 + ", destination longitude = " + long2 + ", earth radius = " + earthRadius +
                "\nGreatCircleDistance outputs: distance = " + distance;
        storeData(latitude1, latitude2, longitude1, longitude2, earthRadius, distance);
        log.debug("GreatCircleDistance -> {}", newLog);


        return Math.round(distance);
    }

    private static double arcLengthBetween(double originLat, double originLong, double destinationLat, double destinationLong){
        double diffLatitude = originLat - destinationLat;
        double diffLongitude = originLong - destinationLong;

        double arcLength = Math.pow(Math.sin(diffLatitude/2),2);
        arcLength += Math.cos(originLat)*Math.cos(destinationLat)*Math.pow(Math.sin(diffLongitude/2),2);
        arcLength = 2 * Math.asin(Math.sqrt(arcLength));
        return arcLength;
    }

    public String toString() {

        return "inputs: origin latitude = " + originLat + ", origin longitude = " + originLong
                + ", destination latitude = " + destinationLat + ", destination longitude = " + destinationLong + ", earth radius = " + radius +
                "\nGreatCircleDistance outputs: distance = " + distance;


    }

    private static void storeData(double lat1, double lat2, double long1, double long2, double er, double dis) {

        originLat = lat1;
        destinationLat = lat2;
        originLong = long1;
        destinationLong = long2;
        radius = er;
        distance = dis;
    }

}
