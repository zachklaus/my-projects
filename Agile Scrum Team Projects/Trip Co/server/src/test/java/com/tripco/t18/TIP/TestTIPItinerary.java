package com.tripco.t18.TIP;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TestTIPItinerary {

    private Map<String, Object> optionsNoOptimization;
    private Map<String, Object> optionsShortOptimization;
    private ArrayList<Map> places1;
    private ArrayList<Map> places2;
    private ArrayList<Map> places3;
    private ArrayList<Map> places4;
    private ArrayList<Map> places5;
    private ArrayList<Map> places6;
    private ArrayList<Map> places7;
    private ArrayList<Map> places8;
    private ArrayList<Map> places9;
    private ArrayList<Map> places10;
    private final int version = 3;

    private Map FortCollins = createPlace("Fort Collins", "40.585258", "-105.084419");
    private Map Denver = createPlace("Denver", "39.7392", "-104.9903");
    private Map Boulder = createPlace("Boulder", "40.01499", "-105.27055");
    private Map ColoradoSprings  = createPlace("Colorado Springs", "38.8339", "-104.8214");
    private Map Aurora = createPlace("Aurora", "39.7294", "-104.8319");
    private Map Lakewood = createPlace("Lakewood", "39.7047", "-105.0814");
    private Map Arvada = createPlace("Arvada", "39.8028", "-105.0875");
    private Map Pueblo = createPlace("Pueblo", "38.2544", "-104.6091");
    private Map Thornton = createPlace("Thornton", "39.8680", "-104.9719");
    private Map Phoenix = createPlace("Phoenix", "33.4484", "-112.0740");
    private Map Buffalo = createPlace("Buffalo", "42.8864", "-78.8784");
    private Map Chicago = createPlace("Chicago", "41.8781", "-87.6298");
    private Map Milwaukee = createPlace ("Milwaukee", "43.0389", "-87.9065");
    private Map Atlanta = createPlace("Atlanta", "33.7490", "-84.3880");
    private Map FortWayne = createPlace("Fort Wayne", "41.0793", "-85.1394");

    @Before
    public void createPlacesForTestCases() {

        optionsNoOptimization = new HashMap<>();
        optionsNoOptimization.put("earthRadius", "3958.761316");
        optionsNoOptimization.put("optimization", "none");

        optionsShortOptimization = new HashMap<>();
        optionsShortOptimization.put("earthRadius", "3958.761316");
        optionsShortOptimization.put("optimization", "short");

        // for tests without optimizations
        places1 = new ArrayList<>();

        places2 = new ArrayList<>();
        places2.add(Denver);
        places2.add(Boulder);
        places2.add(FortCollins);

        places3 = new ArrayList<>();
        places3.add(Denver);

        places4 = new ArrayList<>();
        places4.add(Denver);
        places4.add(Boulder);
        places4.add(Denver);

        places5 = new ArrayList<>();
        places5.add(FortCollins);
        places5.add(FortCollins);
        places5.add(Boulder);
        places5.add(Boulder);

        places6 = new ArrayList<>();
        places6.add(FortCollins);
        places6.add(FortCollins);

        places7 = new ArrayList<>();
        places7.add(Boulder);
        places7.add(Boulder);
        places7.add(Boulder);

        places8 = new ArrayList<>();
        places8.add(Aurora);
        places8.add(Arvada);
        places8.add(Lakewood);
        places8.add(Thornton);

        places9 = new ArrayList<>();
        places9.add(Denver);
        places9.add(Boulder);
        places9.add(ColoradoSprings);
        places9.add(FortCollins);
        places9.add(Pueblo);

        places10 = new ArrayList<>();
        places10.add(Phoenix);
        places10.add(Buffalo);
        places10.add(Milwaukee);
        places10.add(Chicago);
        places10.add(Atlanta);
        places10.add(FortWayne);

    }

    // tests with no optimization

    @Test
    public void testNoPlacesGiven() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsNoOptimization, places1);
        itinerary.buildResponse();
        ArrayList<Long> expect = new ArrayList<>();
        ArrayList<Long> actual = itinerary.getDistances();
        assertEquals("distances is [] when no places given", expect, actual);
    }

    @Test
    public void testThreePlacesGiven() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsNoOptimization, places2);
        itinerary.buildResponse();
        ArrayList<Long> expect = new ArrayList<>();
        expect.add(24l);
        expect.add(41l);
        expect.add(59l);
        ArrayList<Long> actual = itinerary.getDistances();
        assertEquals("distances is [24,41,59] when Denver, Boulder, Fort Collins given", expect, actual);
    }

    @Test
    public void testOnePlaceGiven() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsNoOptimization, places3);
        itinerary.buildResponse();
        ArrayList<Long> expect = new ArrayList<>();
        expect.add(0l);
        ArrayList<Long> actual = itinerary.getDistances();
        assertEquals("distances is [0] when one place given", expect, actual);
    }

    @Test
    public void testOneDuplicate() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsNoOptimization, places4);
        itinerary.buildResponse();
        ArrayList<Long> expect = new ArrayList<>();
        expect.add(24l);
        expect.add(24l);
        expect.add(0l);
        ArrayList<Long> actual = itinerary.getDistances();
        assertEquals("distances is [24,24,0] when Denver, Boulder, Denver given", expect, actual);
    }

    @Test
    public void testTwoDuplicate() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsNoOptimization, places5);
        itinerary.buildResponse();
        ArrayList<Long> expect = new ArrayList<>();
        expect.add(0l);
        expect.add(41l);
        expect.add(0l);
        expect.add(41l);
        ArrayList<Long> actual = itinerary.getDistances();
        assertEquals("distances is [0,41,0,41] when Fort Collins, Fort Collins, Boulder, Boulder given", expect, actual);
    }

    @Test
    public void testDoubleDuplicate() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsNoOptimization, places6);
        itinerary.buildResponse();
        ArrayList<Long> expect = new ArrayList<>();
        expect.add(0l);
        expect.add(0l);
        ArrayList<Long> actual = itinerary.getDistances();
        assertEquals("distances is [0,0] when Fort Collins, Fort Collins given", expect, actual);
    }

    @Test
    public void testTripleDuplicate() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsNoOptimization, places7);
        itinerary.buildResponse();
        ArrayList<Long> expect = new ArrayList<>();
        expect.add(0l);
        expect.add(0l);
        expect.add(0l);
        ArrayList<Long> actual = itinerary.getDistances();
        assertEquals("distances is [0,0,0] when Boulder, Boulder, Boulder given", expect, actual);
    }

    // tests with short optimization

    @Test
    public void testOptimizedNoPlacesGiven() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsShortOptimization, places1);
        itinerary.buildResponse();
        ArrayList<Long> expect = new ArrayList<>();
        ArrayList<Long> actual = itinerary.getDistances();
        assertEquals("distances is [] when no places given (short optimization)", expect, actual);
    }

    @Test
    public void testOptimizedOnePlaceGiven() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsShortOptimization, places3);
        itinerary.buildResponse();
        ArrayList<Long> expect = new ArrayList<>();
        expect.add(0l);
        ArrayList<Long> actual = itinerary.getDistances();
        assertEquals("distances is [0] when one place given (short optimization)", expect, actual);
    }

    @Test
    public void testOptimizedOneDuplicate() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsShortOptimization, places4);
        itinerary.buildResponse();
        ArrayList<Long> expect = new ArrayList<>();
        expect.add(0l);
        expect.add(24l);
        expect.add(24l);
        ArrayList<Long> actual = itinerary.getDistances();
        assertEquals("distances is [0,24,24] when Denver, Boulder, Denver given (short optimization)", expect, actual);
    }

    @Test
    public void testOptimizedTwoDuplicate() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsShortOptimization, places5);
        itinerary.buildResponse();
        ArrayList<Long> expect = new ArrayList<>();
        expect.add(0l);
        expect.add(41l);
        expect.add(0l);
        expect.add(41l);
        ArrayList<Long> actual = itinerary.getDistances();
        assertEquals("distances is [0,41,0,41] when Fort Collins, Fort Collins, Boulder, Boulder given (short optimization)", expect, actual);
    }

    @Test
    public void testOptimizedDoubleDuplicate() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsShortOptimization, places6);
        itinerary.buildResponse();
        ArrayList<Long> expect = new ArrayList<>();
        expect.add(0l);
        expect.add(0l);
        ArrayList<Long> actual = itinerary.getDistances();
        assertEquals("distances is [0,0] when Fort Collins, Fort Collins given (short optimization)", expect, actual);
    }

    @Test
    public void testOptimizedTripleDuplicate() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsShortOptimization, places7);
        itinerary.buildResponse();
        ArrayList<Long> expect = new ArrayList<>();
        expect.add(0l);
        expect.add(0l);
        expect.add(0l);
        ArrayList<Long> actual = itinerary.getDistances();
        assertEquals("distances is [0,0,0] when Boulder, Boulder, Boulder given (short optimization)", expect, actual);
    }


    @Test
    public void testOptimizationsFourPlaces() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsShortOptimization, places8);
        itinerary.buildResponse();
        ArrayList<Map> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(Aurora);
        expectedPlaces.add(Thornton);
        expectedPlaces.add(Arvada);
        expectedPlaces.add(Lakewood);
        ArrayList<Map> actual = itinerary.getPlaces();
        assertEquals("Optimized trip should be Aurora -> Thornton -> Arvada -> Lakewood -> Aurora", expectedPlaces, actual);
    }


    @Test
    public void testOptimizationsFivePlaces() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsShortOptimization, places9);
        itinerary.buildResponse();
        ArrayList<Map> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(Denver);
        expectedPlaces.add(ColoradoSprings);
        expectedPlaces.add(Pueblo);
        expectedPlaces.add(FortCollins);
        expectedPlaces.add(Boulder);
        ArrayList<Map> actual = itinerary.getPlaces();
        assertEquals("Optimized trip should be Denver -> Colorado Springs -> Pueblo -> Fort Collins -> Boulder -> Denver", expectedPlaces, actual);
    }

    @Test
    public void testOptimizationsSixPlaces() {
        TIPItinerary itinerary = new TIPItinerary(version, optionsShortOptimization, places10);
        itinerary.buildResponse();
        ArrayList<Map> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(Phoenix);
        expectedPlaces.add(Milwaukee);
        expectedPlaces.add(Chicago);
        expectedPlaces.add(FortWayne);
        expectedPlaces.add(Buffalo);
        expectedPlaces.add(Atlanta);
        ArrayList<Map> actual = itinerary.getPlaces();
        assertEquals("Optimized trip should be Phoenix -> Milwaukee -> Chicago -> Fort Wayne -> Buffalo -> Atlanta -> Phoenix", expectedPlaces, actual);
    }

    // other tests

    @Test
    public void testNonEmptyRequestDistances(){
        String request = "{\n" +
                "  \"requestType\"    : \"itinerary\",\n" +
                "  \"requestVersion\" : 3,\n" +
                "  \"options\"        : { \"title\":\"My Trip\", \n" +
                "                       \"earthRadius\":\"3958.761316\",\n" +
                "                       \"optimization\":\"none\" },\n" +
                "  \"places\"         : [{\"id\":\"dnvr\", \"name\":\"Denver\",       \"latitude\": \"39.7392\",   \"longitude\": \"-104.9903\"},\n" +
                "                      {\"id\":\"bldr\", \"name\":\"Boulder\",      \"latitude\": \"40.01499\",  \"longitude\": \"-105.27055\"},\n" +
                "                      {\"id\":\"foco\", \"name\":\"Fort Collins\", \"latitude\": \"40.585258\", \"longitude\": \"-105.084419\"}],\n" +
                "  \"distances\"      : [29, 58, 65]\n" +
                "}";

        Gson jsonConverter = new Gson();
        TIPItinerary itinerary = jsonConverter.fromJson(request, TIPItinerary.class);
        itinerary.buildResponse();
        assertEquals(new ArrayList<Long>(Arrays.asList(24L, 41L, 59L)), itinerary.getDistances());
    }

    @Test
    public void testDistanceFieldMissing(){
        String request = "{\n" +
                "  \"requestType\"    : \"itinerary\",\n" +
                "  \"requestVersion\" : 3,\n" +
                "  \"options\"        : { \"title\":\"My Trip\", \n" +
                "                       \"earthRadius\":\"3958.761316\",\n" +
                "                       \"optimization\":\"none\" },\n" +
                "  \"places\"         : [{\"id\":\"dnvr\", \"name\":\"Denver\",       \"latitude\": \"39.7392\",   \"longitude\": \"-104.9903\"},\n" +
                "                      {\"id\":\"bldr\", \"name\":\"Boulder\",      \"latitude\": \"40.01499\",  \"longitude\": \"-105.27055\"},\n" +
                "                      {\"id\":\"foco\", \"name\":\"Fort Collins\", \"latitude\": \"40.585258\", \"longitude\": \"-105.084419\"}]\n" +
                "}";

        Gson jsonConverter = new Gson();
        try {
            TIPItinerary itinerary = jsonConverter.fromJson(request, TIPItinerary.class);
            itinerary.buildResponse();
            assertEquals(new ArrayList<Long>(Arrays.asList(24L, 41L, 59L)), itinerary.getDistances());
        } catch (Exception e){
            Assert.fail("500 Error has been caused.");
        }

    }

    @Test
    public void testOptimizedNonEmptyRequestDistances(){
        String request = "{\n" +
                "  \"requestType\"    : \"itinerary\",\n" +
                "  \"requestVersion\" : 3,\n" +
                "  \"options\"        : { \"title\":\"My Trip\", \n" +
                "                       \"earthRadius\":\"3958.761316\",\n" +
                "                       \"optimization\":\"short\" },\n" +
                "  \"places\"         : [{\"id\":\"dnvr\", \"name\":\"Denver\",       \"latitude\": \"39.7392\",   \"longitude\": \"-104.9903\"},\n" +
                "                      {\"id\":\"bldr\", \"name\":\"Boulder\",      \"latitude\": \"40.01499\",  \"longitude\": \"-105.27055\"},\n" +
                "                      {\"id\":\"foco\", \"name\":\"Fort Collins\", \"latitude\": \"40.585258\", \"longitude\": \"-105.084419\"}],\n" +
                "  \"distances\"      : [29, 58, 65]\n" +
                "}";

        Gson jsonConverter = new Gson();
        TIPItinerary itinerary = jsonConverter.fromJson(request, TIPItinerary.class);
        itinerary.buildResponse();
        assertEquals(new ArrayList<Long>(Arrays.asList(24L, 41L, 59L)), itinerary.getDistances());
    }

    @Test
    public void tesOptimizedDistanceFieldMissing(){
        String request = "{\n" +
                "  \"requestType\"    : \"itinerary\",\n" +
                "  \"requestVersion\" : 3,\n" +
                "  \"options\"        : { \"title\":\"My Trip\", \n" +
                "                       \"earthRadius\":\"3958.761316\",\n" +
                "                       \"optimization\":\"short\" },\n" +
                "  \"places\"         : [{\"id\":\"dnvr\", \"name\":\"Denver\",       \"latitude\": \"39.7392\",   \"longitude\": \"-104.9903\"},\n" +
                "                      {\"id\":\"bldr\", \"name\":\"Boulder\",      \"latitude\": \"40.01499\",  \"longitude\": \"-105.27055\"},\n" +
                "                      {\"id\":\"foco\", \"name\":\"Fort Collins\", \"latitude\": \"40.585258\", \"longitude\": \"-105.084419\"}]\n" +
                "}";

        Gson jsonConverter = new Gson();
        try {
            TIPItinerary itinerary = jsonConverter.fromJson(request, TIPItinerary.class);
            itinerary.buildResponse();
            assertEquals(new ArrayList<Long>(Arrays.asList(24L, 41L, 59L)), itinerary.getDistances());
        } catch (Exception e){
            Assert.fail("500 Error has been caused.");
        }

    }

    private Map<String, Object> createPlace(String name, String lat, String lon){

        Map<String, Object> place = new HashMap<String, Object>();

        place.put("name", name);
        place.put("latitude", lat);
        place.put("longitude", lon);

        return place;
    }
}
