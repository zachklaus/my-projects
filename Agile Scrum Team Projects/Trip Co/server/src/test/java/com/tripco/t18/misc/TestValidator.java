package com.tripco.t18.misc;

import com.tripco.t18.TIP.TIPFind;
import org.everit.json.schema.ValidationException;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

import com.tripco.t18.TIP.TIPDistance;
import com.tripco.t18.TIP.TIPItinerary;


public class TestValidator
{
    @Test
    public void TestValidateDistance(){

        boolean isValid = Validator.isValid(TestJsonStrings.validDistance, TIPDistance.class);
        assert(isValid);

        isValid = Validator.isValid(TestJsonStrings.emptyString, TIPDistance.class);
        assert(!isValid);
    }

    @Test
    public void TestValidateItinerary(){


        boolean isValid = Validator.isValid(TestJsonStrings.validItinerary, TIPItinerary.class);
        assert(isValid);

        isValid = Validator.isValid(TestJsonStrings.emptyString, TIPItinerary.class);
        assert(!isValid);
//
//        isValid = Validator.isValid(TestJsonStrings.invalidItinerary, TIPItinerary.class);
//        assert(!isValid);
    }

    @Test
    public void TestValidateFind(){
        boolean isValid = Validator.isValid(TestJsonStrings.validFind, TIPFind.class);
        assert(isValid);

        isValid = Validator.isValid(TestJsonStrings.emptyString, TIPFind.class);
        assert(!isValid);
    }

}
