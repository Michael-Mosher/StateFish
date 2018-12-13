package org.wecancodeit.StateFish.Controller;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.wecancodeit.StateFish.Model.Fish;
import org.wecancodeit.StateFish.Model.State;

@WebService
public interface StateService {
  @WebMethod
  Collection<State> getStates();
  
  @WebMethod
  State getState(String stateAbbreviation);
  
  @WebMethod
  boolean addState(String name, String abbreviation, String motto, Fish fish);
}
