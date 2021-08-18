package com.tripco.t18.TIP;

import com.tripco.t18.misc.GreatCircleDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.lang.Math;



/** Defines the TIP distance object.
 *
 * For use with restful API services,
 * An object is created from the request JSON by the MicroServer using GSON.
 * The buildResponse method is called to determine the distance.
 * The MicroServer constructs the response JSON from the object using GSON.
 *
 * For unit testing purposes,
 * An object is created using the constructor below with appropriate parameters.
 * The buildResponse method is called to determine the distance.
 * The getDistance method is called to obtain the distance value for comparisons.
 *
 */
public class TIPDistance extends TIPHeader {
  private Map origin;
  private Map destination;
  private Double earthRadius;
  private Long distance;

  private final transient Logger log = LoggerFactory.getLogger(TIPDistance.class);


  TIPDistance(int version, Map origin, Map destination, double earthRadius) {
    this();
    this.requestVersion = version;
    this.origin = origin;
    this.destination = destination;
    this.earthRadius = earthRadius;
    this.distance = 0l;
  }


  private TIPDistance() {
    this.requestType = "distance";
  }


  @Override
  public void buildResponse() {

    if (this.origin.equals(this.destination)){
      this.distance = 0l;
    }
    else{
      this.distance = GreatCircleDistance.calculate(origin, destination, this.earthRadius);
    }
    
    log.trace("buildResponse -> {}", this);

  }


  long getDistance() {
    return distance;
  }

  public String toString(){
    return String.format("Origin Latitude: %s, Origin Longitude: %s, Destination Latitude: %s, Destination Longitude: %s, earthRadius: %f, Distance: %d",
            this.origin.get("latitude"), this.origin.get("longitude"), this.destination.get("latitude"), this.destination.get("longitude"), this.earthRadius, this.distance);
  }

}
