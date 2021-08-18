package com.tripco.t18.TIP;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/** Verifies the operation of the TIP distance class and its buildResponse method.
 */
public class TestTIPDistance {

  /* Radius and location values shared by test cases */
  private final float radiusMiles = 3959;
  private Map<String, Object> csu;
  private Map<String, Object> neCo;
  private Map<String, Object> swCo;
  private final int version = 2;

  @Before
  public void createLocationsForTestCases() {
    csu = new HashMap<>();
    createLocation(csu, "40.576179","-105.080773", "Oval, Colorado State University, Fort Collins, Colorado, USA");
    neCo = new HashMap<>();
    createLocation(neCo, "41", "-102", "northeast Colorado");
    swCo = new HashMap<>();
    createLocation(swCo, "37", "-109", "southwest Colorado");
  }

  @Test
  public void testOriginDestinationSame() {
    TIPDistance trip = new TIPDistance(version, csu, csu, radiusMiles);
    trip.buildResponse();
    long expect = 0l;
    long actual = trip.getDistance();
    assertEquals("origin and destination are the same", expect, actual);
  }

  @Test
  public void BuildResponseFromNewJson(){
    String json = "{'requestType': distance, 'requestVersion': 1, 'origin': {'latitude': '41', 'longitude': '-102'}, 'destination': {'latitude': '37', 'longitude': '-109'}, 'earthRadius': 3959.0}";
    Gson jsonConvert = new Gson();
    TIPDistance d = jsonConvert.fromJson(json,TIPDistance.class);
    d.buildResponse();
    long expect = 466l;
    long actual = d.getDistance();
    assertEquals("Colorado Diagonal", expect, actual);
  }

  @Test
  public void testColoradoDiagonal(){
    TIPDistance trip = new TIPDistance(version, neCo, swCo, radiusMiles);
    trip.buildResponse();
    long expect = 466l;
    long actual = trip.getDistance();
    assertEquals("Colorado Diagonal", expect, actual);
  }

  @Test
  public void testEarthCircumference(){
    Map antiCSU = new HashMap<>();
    createLocation(antiCSU,"-40.576179", "74.919227", "Antipode of CSU");
    TIPDistance trip = new TIPDistance(version, csu, antiCSU, radiusMiles);
    trip.buildResponse();

    long expected = (long)12438;
    long actual = trip.getDistance();

    assertEquals("Half the circumference of the earth", expected, actual);
  }

  private void createLocation(Map<String, Object> location, String lat, String lon, String name){
    location.put("latitude", lat);
    location.put("longitude", lon);
    location.put("name", name);
  }
}
