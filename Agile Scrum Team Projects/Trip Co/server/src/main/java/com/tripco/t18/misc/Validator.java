package com.tripco.t18.misc;

import com.tripco.t18.TIP.TIPConfig;
import com.tripco.t18.TIP.TIPDistance;
import com.tripco.t18.TIP.TIPFind;
import com.tripco.t18.TIP.TIPItinerary;
import org.everit.json.schema.Schema;
import org.everit.json.schema.SchemaException;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;


/*
 * Following code assisted heavily from Schema Example
 * */
public class Validator {

    private static final transient Logger log = LoggerFactory.getLogger(Validator.class);

    public enum RequestType {Config ,Distance, Itinerary, Find}

    private static String getSchema(Type type){
        String schemaString;
        if(type == TIPDistance.class)
            schemaString = JsonValidSchemas.DistanceSchema;
        else if(type == TIPItinerary.class)
            schemaString = JsonValidSchemas.ItinerarySchema;
        else if(type == TIPFind.class)
            schemaString = JsonValidSchemas.FindSchema;
        else
            schemaString = "this will explode";
        return schemaString;
    }



    public static boolean isValid(String jsonStringToValidate, Type type){

        //configs are only sent from server, not received. No need to validate
        if(type == TIPConfig.class)
            return true;

        JSONObject jsonSchema;
        JSONObject jsonToValidate;

        try{
            //get schema json object
            jsonSchema = new JSONObject(getSchema(type));
            jsonToValidate = new JSONObject(jsonStringToValidate);
        }
        catch (JSONException jsonE){
            log.trace("Caught exception when constructing JSON object");
            log.trace(jsonE.getMessage());
            return false;
        }
        return validateSchema(jsonToValidate, jsonSchema);
    }

    private static boolean validateSchema(JSONObject toValidate, JSONObject schemaObj){
        boolean result=true;
        try{
            Schema schema = SchemaLoader.load(schemaObj);
            schema.validate(toValidate);
        }
        catch(SchemaException se){
            //this will only occur if our schema has bad syntax
            log.trace("Caught a schema exception");
            log.trace(se.getMessage() + '\n' + toValidate.toString());
            result=false;
        }
        catch(ValidationException ve){
            //occurs if the request is invalid.
  /*        log.trace("Caught validation exception when validating schema! Root message: {}", ve.getErrorMessage());
            log.trace("All messages from errors (including nested):");*/
            // For now, messages are probably just good for debugging, to see why something failed
            List<String> allMessages = ve.getAllMessages();
            for (String message : allMessages) {
                log.debug(message);
            }
            throw(ve);
        }
        return result;
    }
}
