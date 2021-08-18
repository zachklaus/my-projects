package com.tripco.t18.TIP;

import com.tripco.t18.misc.DatabaseFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/** This class defines the Config response that provides the client
 * with server specific configuration information.
 *  
 * When used with restful API services,
 * An object is created from the request JSON by the MicroServer using GSON.
 * The buildResponse method is called to set the configuration information.
 * The MicroServer constructs the response JSON from the object using GSON.
 *  
 * When used for testing purposes,
 * An object is created using the constructor below.
 * The buildResponse method is called to set the configuration information.
 * The getDistance method is called to obtain the distance value for comparisons.
 */
public class TIPConfig extends TIPHeader {
  private String serverName;
  private List<String> placeAttributes;
  private List<String> optimizations;
  private List<Map<String, Object>> filters;

  private final transient Logger log = LoggerFactory.getLogger(TIPConfig.class);


  public TIPConfig() {
    this.requestType = "config";
    this.requestVersion = 4;
  }


  @Override
  public void buildResponse() {
    this.serverName = "T18 Ctrl Alt Elite";
    this.placeAttributes = Arrays.asList("name", "latitude", "longitude", "id", "municipality", "region", "country", "continent", "altitude");
    this.optimizations = Arrays.asList("none", "short", "shorter");
    this.filters = buildFilters();

    log.trace("buildResponse -> {}", this);
  }

  @Override
  public String toString() {
    return "TIPConfig{" +
            "serverName='" + serverName + '\'' +
            ", placeAttributes=" + placeAttributes +
            ", optimizations=" + optimizations +
            ", filters="+filters+
            "} ";
  }

  private static ArrayList<Map<String, Object>> buildFilters(){
    ArrayList<Map<String, Object>> filters = new ArrayList<>();

    HashMap<String, Object> typeFilter = new HashMap<>();
    typeFilter.put("name", "type");
    typeFilter.put("values", Arrays.asList("airport","heliport","balloonport","closed"));

    HashMap<String, Object> countryFilter = new HashMap<>();
    countryFilter.put("name", "country");
    countryFilter.put("values", DatabaseFacade.getCountryNames());

    filters.add(typeFilter);
    filters.add(countryFilter);
    return filters;
  }

  String getServerName() { return this.serverName; }
  int getVersion() {
    return this.requestVersion;
  }
  List<String> getPlaceAttributes() { return this.placeAttributes; }
  List<String> getOptimizations() { return this.optimizations; }

}
