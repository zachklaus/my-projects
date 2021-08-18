package com.tripco.t18.TIP;

import com.tripco.t18.misc.DatabaseFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TIPFind extends TIPHeader {
    // Response Headers
    private String match;
    private int limit;
    private int found;
    private List<Map> places;
    private List<Map<String, Object>> narrow;
    private transient List<String> attributes = Arrays.asList("w.name as name", "w.latitude", "w.longitude", "w.id", "w.municipality", "r.name as region", "c.name as country", "ct.name as continent", "w.altitude");


    private final transient Logger logger = LoggerFactory.getLogger(TIPFind.class);

    TIPFind() {
        this(3, null, 10);
    }

    TIPFind(int requestVersion, String match, int limit) {
        this.requestType = "find";
        this.requestVersion = requestVersion;
        this.match = match;
        this.limit = limit;
    }


    @Override
    public void buildResponse() {
        if(limit == 0){
            limit = 10;
        }

        found = DatabaseFacade.countLocationsBySearchString(match, narrow);
        places = DatabaseFacade.getLocationsBySearchString(match, limit, attributes, narrow);

        logger.trace("buildResponse -> {}", this);
    }

    @Override
    public String toString() {
        return "TIPFind{" +
                "match='" + match + '\'' +
                ", limit=" + limit +
                ", found=" + found +
                ", places=" + places +
                ", attributes=" + attributes +
                "} " + super.toString();
    }

    public int getLimit(){
        return this.limit;
    }

    public List<Map> getPlaces(){
        return this.places;
    }

    public int getFound(){
        return this.found;
    }
}
