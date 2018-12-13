package org.wecancodeit.StateFish;

import javax.xml.ws.Endpoint;

import org.springframework.boot.SpringApplication;
import org.wecancodeit.StateFish.Controller.StateServiceSoap;

public class StateServicePublisher {
  public static void main(String[] args)
  {
    SpringApplication.run(StateFishApplication.class, args);
    Endpoint.publish("http://localhost:1234/stateservice", 
      new StateServiceSoap());
  }
}
