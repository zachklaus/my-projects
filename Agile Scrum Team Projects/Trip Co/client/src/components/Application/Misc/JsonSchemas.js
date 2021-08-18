
import Ajv from 'ajv';

export default class JsonSchemas {

    static validate(value, schemaName) {
        let schemaText = '';
        switch (schemaName) {
            case this.Schemas.ITINERARY:
                schemaText = this.ValidateItinerarySchema;
                break;
            case this.Schemas.DISTANCE:
                schemaText = this.ValidateDistanceSchema;
                break;
            case this.Schemas.FIND:
                //schemaText = not yet implemented
                break;
            case this.Schemas.CONFIG:
                schemaText = this.ValidateConfigSchema;
                break;
            default:
                break;
        }
        // console.log(schemaText);
        let schema = JSON.parse(schemaText);
        let ajv = new Ajv({allErrors: true});
        const validator = ajv.compile(schema);
        let test = validator(value);
        if(!test){
            console.log(validator.errors);
            console.log(value);
        }
        return test;
    }

}
//Javascript version of enum
JsonSchemas.Schemas = {'ITINERARY':'itinerary', 'DISTANCE': 'distance', 'CONFIG':'config', 'FIND':'find'};
//Outside class = static field
JsonSchemas.ValidateItinerarySchema= "{\n" +
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

JsonSchemas.ValidateConfigSchema = "{\n" +
    "  \"$id\": \"https://example.com/address.schema.json\",\n" +
    "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
    "  \"title\": \"config\",\n" +
    "  \"description\": \"config response\",\n" +
    "  \"type\": \"object\",\n" +
    "  \"properties\": {\n" +
    "    \"requestVersion\": {\n" +
    "      \"description\":\"the TIP protocol version\",\n" +
    "      \"type\":\"integer\",\n" +
    "      \"minimum\": 1\n" +
    "    },\n" +
    "    \"requestType\": {\n" +
    "      \"description\":\"the TIP object type should be config\",\n" +
    "      \"type\":\"string\",\n" +
    "      \"pattern\":\"^config$\"\n" +
    "    },\n" +
    "    \"serverName\": {\n" +
    "      \"description\":\"identify the server instance\",\n" +
    "      \"type\":\"string\",\n" +
    "      \"minLength\":3\n" +
    "    },\n" +
    "    \"placeAttributes\": {\n" +
    "      \"description\":\"list of attributes used to described places\",\n" +
    "      \"type\":\"array\",\n" +
    "      \"items\": {\n" +
    "        \"type\":\"string\"\n" +
    "      },\n" +
    "      \"minItems\":6,\n" +
    "      \"uniqueItems\": true,\n" +
    "      \"required\":[\"name\", \"latitude\", \"longitude\", \"id\", \"municipality\", \"region\", \"country\", \"continent\", \"altitude\"]\n" +
    "    },\n" +
    "    \"optimizations\": {\n" +
    "      \"description\":\"list of optimization levels available from the server\",\n" +
    "      \"type\":\"array\",\n" +
    "      \"items\": {\n" +
    "        \"type\":\"string\",\n" +
    "        \"minLength\":1\n" +
    "      },\n" +
    "      \"minItems\":2,\n" +
    "      \"uniqueItems\": true,\n" +
    "      \"required\":[\"none\", \"short\"]\n" +
    "    },\n" +
    "    \"filters\": {\n" +
    "      \"description\":\"list describing one or more filters that may be applied to finds on the server\",\n" +
    "      \"type\":\"array\",\n" +
    "      \"items\": {\n" +
    "        \"type\":\"object\",\n" +
    "        \"properties\":{\n" +
    "           \"name\": {\"type\":\"string\"},\n" +
    "           \"type\": {\"type\":\"string\"},\n" +
    "           \"values\": {\n" +
    "               \"type\":\"array\",\n" +
    "               \"items\": {\n" +
    "                   \"type\":\"string\",\n" +
    "                   \"minLength\":1\n" +
    "                },\n" +
    "                \"required\": [\"airport\", \"heliport\", \"balloonport\", \"closed\"],\n" +
    "                \"uniqueItems\": true\n" +
    "            }\n" +
    "        }\n" +
    "     },\n" +
    "      \"required\":[\"name\",\"values\"],\n" +
    "      \"minItems\":1,\n" +
    "      \"uniqueItems\": true\n" +
    "    }\n" +
    "  },\n" +
    "  \"required\":[\"requestVersion\",\"requestType\",\"serverName\",\"placeAttributes\",\"optimizations\"],\n" +
    "  \"additionalProperties\": false\n" +
    "}";

JsonSchemas.ValidateDistanceSchema = "{\n" +
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

