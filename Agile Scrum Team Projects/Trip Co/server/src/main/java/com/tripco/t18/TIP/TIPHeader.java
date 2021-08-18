package com.tripco.t18.TIP;

public abstract class TIPHeader {
  protected Integer requestVersion;
  protected String requestType;

  public abstract void buildResponse();

  @Override
  public String toString() {
    return "TIPHeader{" +
            "requestVersion=" + requestVersion +
            ", requestType='" + requestType + '\'' +
            '}';
  }
}
