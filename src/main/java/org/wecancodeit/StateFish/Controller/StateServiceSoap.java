package org.wecancodeit.StateFish.Controller;

import java.util.Collection;
import java.util.Optional;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.wecancodeit.StateFish.Model.Fish;
import org.wecancodeit.StateFish.Model.State;
import org.wecancodeit.StateFish.Model.StateRepository;

@WebService(endpointInterface="org.wecancodeit.StateFish.Controller.StateService")
public class StateServiceSoap implements StateService{
  @Inject
  StateRepository stateRepo;
  @WebMethod
  public Collection<State> getStates()
  {
    return (Collection<State>)stateRepo.findAll();
  }

  @WebMethod
  public State getState(String stateAbbreviation)
  {
    Optional<State> s = stateRepo.findByAbbreviation(stateAbbreviation);
    if(s.isPresent()) return s.get();
    return new State();
  }
    
  @WebMethod
  public boolean addState(String name, String abbreviation, String motto, Fish fish)
  {
    Optional<State> stateByAbbr = stateRepo.findByAbbreviation(abbreviation);
    Optional<State> stateByName = stateRepo.findByName(name);
    if(!stateByAbbr.isPresent() && !stateByName.isPresent()){
      State proposedState = new State(name, abbreviation, motto, fish);
      stateRepo.save(proposedState);
      return true;
    }
    return false;
  }
}
