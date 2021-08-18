package com.tripco.t18.misc;

//Schemas to be used for validation
public class JsonValidSchemas {

    public static final String ItinerarySchema = "{\n" +
            "  \"$id\": \"https://example.com/address.schema.json\",\n" +
            "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
            "  \"title\": \"itinerary\",\n" +
            "  \"description\": \"itinerary request/response\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"properties\": {\n" +
            "    \"requestVersion\": {\n" +
            "      \"description\":\"the TIP protocol version\",\n" +
            "      \"type\":\"integer\",\n" +
            "      \"minimum\": 2\n" +
            "    },\n" +
            "    \"requestType\": {\n" +
            "      \"description\":\"the TIP object type should be itinerary\",\n" +
            "      \"type\":\"string\",\n" +
            "      \"pattern\":\"^itinerary$\"\n" +
            "    },\n" +
            "    \"options\": {\n" +
            "      \"description\":\"options for this request\",\n" +
            "      \"type\":\"object\",\n" +
            "      \"properties\": {\n" +
            "        \"title\": {\"type\":\"string\"},\n" +
            "        \"earthRadius\": {\"type\":\"string\", \"pattern\":\"^[0-9]+(\\\\.[0-9]+)?$\"},\n" +
            "        \"optimization\": {\"type\":\"string\"}\n" +
            "      },\n" +
            "      \"required\":[\"earthRadius\"]\n" +
            "    },\n" +
            "    \"places\": {\n" +
            "      \"description\": \"list of places to visit\",\n" +
            "      \"type\":\"array\",\n" +
            "      \"items\": {\n" +
            "        \"type\":\"object\",\n" +
            "        \"properties\": {\n" +
            "          \"name\": {\"type\":\"string\"},\n" +
            "          \"latitude\": {\"type\":\"string\",\n" +
            "            \"pattern\":\"^[-+]?(?:90(?:(?:\\\\.0+)?)|(?:[0-9]|[1-8][0-9])(?:(?:\\\\.[0-9]+)?))$\"},\n" +
            "          \"longitude\": {\"type\":\"string\",\n" +
            "            \"pattern\":\"^[-+]?(?:180(?:(?:\\\\.0+)?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\\\.[0-9]+)?))$\"},\n" +
            "          \"id\": {\"type\":\"string\"},\n" +
            "          \"municipality\": {\"type\":\"string\"},\n" +
            "          \"altitude\": {\"type\":\"string\", \"pattern\":\"^-?[0-9]+$\"}\n" +
            "        },\n" +
            "        \"required\": [\"latitude\",\"longitude\"],\n" +
            "        \"additionalProperties\": true\n" +
            "      },\n" +
            "      \"minitems\": 0\n" +
            "    },\n" +
            "    \"distances\": {\n" +
            "      \"description\": \"distances between corresponding places\",\n" +
            "      \"type\":\"array\",\n" +
            "      \"items\": {\n" +
            "        \"type\":\"integer\"\n" +
            "      },\n" +
            "      \"minitems\": 0\n" +
            "    }\n" +
            "  },\n" +
            "  \"required\":[\"requestVersion\",\"requestType\",\"options\",\"places\"],\n" +
            "  \"additionalProperties\": false\n" +
            "}";

    public static final String FindSchema = "{\n" +
            "  \"$id\": \"https://example.com/address.schema.json\",\n" +
            "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
            "  \"title\": \"itinerary\",\n" +
            "  \"description\": \"itinerary request/response\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"properties\": {\n" +
            "    \"requestVersion\": {\n" +
            "      \"description\":\"the TIP protocol version\",\n" +
            "      \"type\":\"integer\",\n" +
            "      \"minimum\": 3\n" +
            "    },\n" +
            "    \"requestType\": {\n" +
            "      \"description\":\"the TIP object type should be itinerary\",\n" +
            "      \"type\":\"string\",\n" +
            "      \"pattern\":\"^find$\"\n" +
            "    },\n" +
            "    \"match\": {\n" +
            "      \"description\":\"an alphanumeric pattern used to find geographic locations.\",\n" +
            "      \"type\":\"string\",\n" +
            "    },\n" +
            "    \"narrow\": {\n" +
            "      \"type\": \"array\",\n" +
            "      \"items\":{\n" +
            "         \"name\":{\"type\":\"string\"},\n" +
            "         \"values\":{\n" +
            "            \"type\":\"array\",\n" +
            "            \"items\":{\n" +
            "               \"type\":\"string\",\n" +
            "               \"pattern\": \"^airport$|^heliport$|^balloonport$|^closed$\"\n" +
            "            },\n" +
            "            \"uniqueItems\": true\n" +
            "         }\n" +
            "      },\n" +
            "      \"additionalProperties\":false,\n" +
            "      \"required\":[\"name\", \"values\"]\n" +
            "    },\n" +
            "    \"limit\": {\n" +
            "      \"description\":\"the maximum number of matching places that may be returned.\",\n" +
            "      \"type\":\"integer\",\n" +
            "      \"minimum\":0\n" +
            "    },\n" +
            "    \"found\": {\n" +
            "      \"description\":\"the total number of matching places in the data source(s).\",\n" +
            "      \"type\":\"integer\",\n" +
            "      \"minimum\":0\n" +
            "    },\n" +
            "    \"places\": {\n" +
            "      \"description\": \"list of places places found\",\n" +
            "      \"type\":\"array\",\n" +
            "      \"items\": {\n" +
            "        \"type\":\"object\",\n" +
            "        \"properties\": {\n" +
            "          \"name\": {\"type\":\"string\"},\n" +
            "          \"latitude\": {\"type\":\"string\",\n" +
            "            \"pattern\":\"^[-+]?(?:90(?:(?:\\\\.0+)?)|(?:[0-9]|[1-8][0-9])(?:(?:\\\\.[0-9]+)?))$\"},\n" +
            "          \"longitude\": {\"type\":\"string\",\n" +
            "            \"pattern\":\"^[-+]?(?:180(?:(?:\\\\.0+)?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\\\.[0-9]+)?))$\"},\n" +
            "          \"id\": {\"type\":\"string\"},\n" +
            "          \"municipality\": {\"type\":\"string\"},\n" +
            "          \"altitude\": {\"type\":\"string\", \"pattern\":\"^-?[0-9]+$\"}\n" +
            "        },\n" +
            "        \"required\": [\"latitude\",\"longitude\"],\n" +
            "        \"additionalProperties\": true\n" +
            "      },\n" +
            "      \"minitems\": 0\n" +
            "    }\n" +
            "  },\n" +
            "  \"required\":[\"requestVersion\",\"requestType\",\"match\"],\n" +
            "  \"additionalProperties\": false\n" +
            "}";

    public static final String DistanceSchema = "{\n" +
            "  \"$id\": \"https://example.com/address.schema.json\",\n" +
            "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
            "  \"title\": \"distance\",\n" +
            "  \"description\": \"distance request/response\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"properties\": {\n" +
            "    \"requestVersion\": {\n" +
            "      \"description\":\"the TIP protocol version\",\n" +
            "      \"type\":\"integer\",\n" +
            "      \"minimum\": 1\n" +
            "    },\n" +
            "    \"requestType\": {\n" +
            "      \"description\":\"the TIP object type should be distance\",\n" +
            "      \"type\":\"string\",\n" +
            "      \"pattern\":\"^distance$\"\n" +
            "    },\n" +
            "    \"origin\":{\n" +
            "      \"description\":\"an object with the attributes to describe a place\",\n" +
            "      \"type\": \"object\",\n" +
            "      \"properties\": {\n" +
            "        \"name\":{\"type\":\"string\"},\n" +
            "        \"latitude\":{\"type\":\"string\",\n" +
            "          \"pattern\":\"^[-+]?(?:90(?:(?:\\\\.0+)?)|(?:[0-9]|[1-8][0-9])(?:(?:\\\\.[0-9]+)?))$\"},\n" +
            "        \"longitude\":{\"type\":\"string\",\n" +
            "          \"pattern\":\"^[-+]?(?:180(?:(?:\\\\.0+)?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\\\.[0-9]+)?))$\"}\n" +
            "      },\n" +
            "      \"required\":[\"latitude\",\"longitude\"],\n" +
            "      \"additionalProperties\":true\n" +
            "    },\n" +
            "    \"destination\":{\n" +
            "      \"description\":\"an object with the attributes to describe a place\",\n" +
            "      \"type\": \"object\",\n" +
            "      \"properties\": {\n" +
            "        \"name\":{\"type\":\"string\"},\n" +
            "        \"latitude\":{\"type\":\"string\",\n" +
            "          \"pattern\":\"^[-+]?(?:90(?:(?:\\\\.0+)?)|(?:[0-9]|[1-8][0-9])(?:(?:\\\\.[0-9]+)?))$\"},\n" +
            "        \"longitude\":{\"type\":\"string\",\n" +
            "          \"pattern\":\"^[-+]?(?:180(?:(?:\\\\.0+)?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\\\.[0-9]+)?))$\"}\n" +
            "      },\n" +
            "      \"required\":[\"latitude\",\"longitude\"],\n" +
            "      \"additionalProperties\":true\n" +
            "    },\n" +
            "    \"earthRadius\":{\n" +
            "      \"description\":\"the radius of the earth in some unit of measure\",\n" +
            "      \"type\":\"number\",\n" +
            "      \"exclusiveMinimum\":0\n" +
            "    },\n" +
            "    \"distance\":{\n" +
            "      \"description\":\"the great circle distance between the origin and destination using the radius measure\",\n" +
            "      \"type\":\"integer\",\n" +
            "      \"minimum\":0\n" +
            "    }\n" +
            "  },\n" +
            "  \"required\":[\"requestVersion\",\"requestType\",\"origin\",\"destination\",\"earthRadius\"],\n" +
            "  \"additionalProperties\": false\n" +
            "}";
}
