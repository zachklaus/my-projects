package com.tripco.t18.misc;


//Group of strings used for testing valid json schemas
public class TestJsonStrings {

    public static final String emptyString = "";


    public static final String validDistance = "{\n" +
            "  \"requestType\"    : \"distance\",\n" +
            "  \"requestVersion\" : 3,\n" +
            "  \"origin\"         : {\"latitude\":  \"40.6\", \"longitude\": \"-105.1\", \"name\":\"Fort Collins, Colorado, USA\"},\n" +
            "  \"destination\"    : {\"latitude\": \"-33.9\", \"longitude\":  \"151.2\", \"name\":\"Sydney, New South Wales, Australia\"},\n" +
            "  \"earthRadius\"    : 3958.8,\n" +
            "  \"distance\"       : 0\n" +
            "}";



    public static final String validItinerary = "{\n" +
            "  \"requestType\"    : \"itinerary\",\n" +
            "  \"requestVersion\" : 3,\n" +
            "  \"options\"        : { \"title\":\"My Trip\", \n" +
            "                       \"earthRadius\":\"3958.8\",\n" +
            "                       \"optimization\":\"short\" },\n" +
            "  \"places\"         : [{\"name\":\"Denver\",       \"latitude\": \"39.7\", \"longitude\": \"-105.0\"},\n" +
            "                      {\"name\":\"Boulder\",      \"latitude\": \"40.0\", \"longitude\": \"-105.4\"},\n" +
            "                      {\"name\":\"Fort Collins\", \"latitude\": \"40.6\", \"longitude\": \"-105.1\"}],\n" +
            "  \"distances\"      : [24, 41, 59]\n" +
            "}";

    public static final String validFind = "{\n" +
            "  \"requestType\"    : \"find\",\n" +
            "  \"requestVersion\" : 4,\n" +
            "  \"match\"          : \"fort collins\",\n" +
            "  \"narrow\"         : [{\"name\":\"type\", \"values\":[\"airport\",\"heliport\"]}],\n" +
            "  \"limit\"          : 1,\n" +
            "  \"found\"          : 123,\n" +
            "  \"places\"         : [{\"name\":\"Fort Collins\", \"latitude\": \"40.6\", \"longitude\": \"-105.1\"}]\n" +
            "}";


    public static final String invalidItinerary = "{\n" +
            "  \"requestType\": \"trip\",\n" +
            "  \"requestVersion\": 3,\n" +
            "  \"options\": {\"title\": \"Colorado's County Seats\"},\n" +
            "  \"places\": [\n" +
            "    {\n" +
            "      \"id\": \"1\",\n" +
            "      \"name\": \"Adams County\",\n" +
            "      \"latitude\": \"39.87°N\",\n" +
            "      \"longitude\": \"104.33°W\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"2\",\n" +
            "      \"name\": \"Alamosa County\",\n" +
            "      \"latitude\": \"37.57°N\",\n" +
            "      \"longitude\": \"105.79°W\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"3\",\n" +
            "      \"name\": \"Arapahoe County\",\n" +
            "      \"latitude\": \"39.64°N\",\n" +
            "      \"longitude\": \"104.33°W\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"4\",\n" +
            "      \"name\": \"Archuleta County\",\n" +
            "      \"latitude\": \"37.20°N\",\n" +
            "      \"longitude\": \"107.05°W\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"5\",\n" +
            "      \"name\": \"Baca County\",\n" +
            "      \"latitude\": \"37.30°N\",\n" +
            "      \"longitude\": \"102.54°W\"\n" +
            "    }]\n" +
            "}";

}

