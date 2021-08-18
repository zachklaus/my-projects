package com.tripco.t18.TIP;

import com.google.gson.Gson;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class TestTIPFind {

    private final String requestMissingPlaces = "{\n" +
            "  \"requestType\"    : \"find\",\n" +
            "  \"requestVersion\" : 3,\n" +
            "  \"match\"          : \"fort\",\n" +
            "  \"limit\"          : 5,\n" +
            "  \"found\"          : 0\n" +
            "}";

    private final String requestMissingLimit = "{\n" +
            "  \"requestType\"    : \"find\",\n" +
            "  \"requestVersion\" : 3,\n" +
            "  \"match\"          : \"fort\",\n" +
            "  \"found\"          : 0,\n" +
            "  \"places\"         : []\n" +
            "}";

    private final String requestMissingFound = "{\n" +
            "  \"requestType\"    : \"find\",\n" +
            "  \"requestVersion\" : 3,\n" +
            "  \"match\"          : \"fort\",\n" +
            "  \"limit\"          : 5,\n" +
            "  \"places\"         : []\n" +
            "}";

    private final String requestUnlimited = "{\n" +
            "  \"requestType\"    : \"find\",\n" +
            "  \"requestVersion\" : 3,\n" +
            "  \"match\"          : \"fort\",\n" +
            "  \"limit\"          : 0,\n" +
            "  \"found\"          : 0,\n" +
            "  \"places\"         : []\n" +
            "}";

    private static boolean isOnTravis(){
        String travis = System.getenv("TRAVIS");
        return (travis != null && travis.equals("true"));
    }

    @BeforeClass
    public static void shouldRunTests(){
        Assume.assumeTrue(isOnTravis());
    }

    @Test
    public void testLimitIsOptional(){
        Gson jsonConverter = new Gson();
        TIPFind find = jsonConverter.fromJson(requestMissingLimit, TIPFind.class);
        find.buildResponse();
        assertEquals(11, find.getFound());
        assertEquals(10, find.getPlaces().size());
    }

    @Test
    public void testPlacesIsOptional(){
        Gson jsonConverter = new Gson();
        TIPFind find = jsonConverter.fromJson(requestMissingPlaces, TIPFind.class);
        find.buildResponse();
        assertEquals(find.getFound(), 11);
        assertEquals(find.getPlaces().size(), 5);
    }

    @Test
    public void testFoundIsOptional() {
        Gson jsonConverter = new Gson();
        TIPFind find = jsonConverter.fromJson(requestMissingFound, TIPFind.class);
        find.buildResponse();
        assertEquals(find.getFound(), 11);
        assertEquals(find.getPlaces().size(), 5);
    }

    @Test
    public void testUnlimitedCap(){
        Gson jsonConverter = new Gson();
        TIPFind find = jsonConverter.fromJson(requestUnlimited, TIPFind.class);
        find.buildResponse();
        assertEquals(find.getFound(), 11);
        assertEquals(find.getPlaces().size(), 10);
    }
}
