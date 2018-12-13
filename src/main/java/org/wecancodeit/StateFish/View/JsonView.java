package org.wecancodeit.StateFish.View;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wecancodeit.StateFish.Model.City;
import org.wecancodeit.StateFish.Model.CityRepository;
import org.wecancodeit.StateFish.Model.State;
import org.wecancodeit.StateFish.Model.StateNotFoundException;
import org.wecancodeit.StateFish.Model.StateRepository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
public class JsonView {
  @Resource
  private StateRepository stateRepo;
  @Resource
  private CityRepository citiesRepo;
  
  @RequestMapping(value="/states", method=RequestMethod.GET, produces="application/json")
  public ResponseEntity<String> getStates(Model model)
  {
    final HttpHeaders headers = new HttpHeaders();
    Collection<State> states = (Collection<State>) stateRepo.findAll();
    String answer = "[";
    for (State s : states) {
      if(answer.length()>1){
        answer += "," + s.getJson();
      } else {
        answer += s.getJson();
      }
    }
    answer += "]";
    return new ResponseEntity<String>(answer, headers, HttpStatus.OK);
  }
  
  @RequestMapping(value="/states/{state}", method=RequestMethod.GET, produces="application/json")
  public ResponseEntity<String> getState(@PathVariable(value="state")String stateAbbreviation, Model model)
  {
    final HttpHeaders headers = new HttpHeaders();
    Optional<State> state = stateRepo.findByAbbreviation(stateAbbreviation.toUpperCase());
    if(state.isPresent()) {
      return new ResponseEntity<String>(state.get().getJson(),
          headers, HttpStatus.OK);
    }
    return new ResponseEntity<String>( "[{name:"+null+",abbreviation:"+null+",motto:"+null+",citiesUrl:"+null+",fish:"+null+"}]",headers, HttpStatus.OK);
  }
  
  @RequestMapping(value="/post/state/", method=RequestMethod.POST/*, consumes=APPLICATION_JSON_UTF8*/)
  public void postState(@RequestBody HttpEntity<String> postBody)
  {
    if(postBody.hasBody()){
      ObjectMapper mapper = new ObjectMapper();
      State proposedState = null;
      try {
        proposedState = mapper.readValue(postBody.getBody(), State.class);
      } catch (JsonParseException e) {
        e.printStackTrace();
      } catch (JsonMappingException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      Optional<State> checkByAbbreviation = stateRepo.findByAbbreviation(proposedState.getAbbreviation());
      Optional<State> checkByName = stateRepo.findByName(proposedState.getName());
      if(!checkByAbbreviation.isPresent() && !checkByName.isPresent()){
        stateRepo.save(proposedState);
      }
    }
  }
  
  @RequestMapping(value="/states/{stateAbbreviation}/cities", method=RequestMethod.GET, produces="application/json")
  public ResponseEntity<String> getCities(@PathVariable(value="stateAbbreviation") String stateAbbreviation, Model model) throws StateNotFoundException
  {
    String answer = "[{}]";
    final HttpHeaders headers = new HttpHeaders();
    Optional<State> queriedState = stateRepo.findByAbbreviation(stateAbbreviation.toUpperCase());
    if(queriedState.isPresent()){
      Collection<City> cities = citiesRepo.findByEnclosingStateId(queriedState.get().getId());
      answer = "[";
      boolean firstCity = true;
      for (City city : cities) {
        if(firstCity){
          answer += city.toJson();
          firstCity = false;
        } else {
          answer += "," + city.toJson();
        }
      }
      answer += "]";
    } else {
      throw new StateNotFoundException();
    }
    return new ResponseEntity<String>(answer, headers, HttpStatus.OK);
  }
  
  @RequestMapping(value="/post/state/{stateAbbreviation}/city", method=RequestMethod.POST)
  public void postCity(@PathVariable(value="stateAbbreviation")String stateAbbreviation, @RequestBody HttpEntity<String> postBody)
  {
    Optional<State> stateToAddTo = stateRepo.findByAbbreviation(stateAbbreviation);
    if(postBody.hasBody() && stateToAddTo.isPresent()){
      ObjectMapper mapper = new ObjectMapper();
      City proposedCity = null;
      try {
        proposedCity = mapper.readValue(postBody.getBody(), City.class);
      } catch (JsonParseException e) {
        e.printStackTrace();
      } catch (JsonMappingException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      Collection<City> incumbentCities = stateToAddTo.get().getCities();
      if(!incumbentCities.contains(proposedCity)){
        citiesRepo.save(new City(proposedCity.getName(), proposedCity.getPopulation(), stateToAddTo.get()));
      }
    }
  }
}
