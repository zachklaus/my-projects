package com.tripco.t18.misc;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class TestOptimizations {
    @Test
    public void test2_OptSimpleCrossed(){
        // -109.0467 to -102.0467 W
        // 37 to 41 N
        Map bottomLeft = createLocation("37", "-109.0467", "a");
        Map bottomRight = createLocation("37", "-102.0467", "b");
        Map upLeft = createLocation("41", "-109.0467", "c");
        Map upRight = createLocation("41", "-102.0467", "d");

        ArrayList<Map> places = new ArrayList<>(Arrays.asList(bottomLeft, bottomRight, upLeft, upRight));
        int[] tour = {0, 1, 2, 3};

        Optimizer optimizer = new Optimizer(Optimizer.Optimization.fromString("shorter"), places, 3959);
        optimizer.twoOptShorten(tour);

        String result = "";
        for(int i : tour){
            result += String.valueOf(i);
        }

        System.out.println("Initial tour: 0123");
        System.out.println("Optimized tour: " + result);
        System.out.println("'Optimized' Length: " + optimizer.getTourDistance(tour));
        System.out.println("Best Length: " + optimizer.getTourDistance(new int[] {0,1,3,2}));

        String expected = "0231023,0132013";
        assertTrue("Places should be in the correct order.", expected.contains(result));
    }



    private HashMap<String, Object> createLocation(String lat, String lon, String name){
        HashMap<String, Object> location = new HashMap<>();
        location.put("latitude", lat);
        location.put("longitude", lon);
        location.put("name", name);

        return location;
    }
}
