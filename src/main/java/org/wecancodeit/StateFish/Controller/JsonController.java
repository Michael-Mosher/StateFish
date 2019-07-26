package org.wecancodeit.StateFish.Controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.wecancodeit.StateFish.Model.StateRepository;
import org.wecancodeit.StateFish.Model.State;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {
  @Resource
  private StateRepository stateRepo;

  @RequestMapping(value = "/states", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<String> getStates(Model model) {
    final HttpHeaders headers = new HttpHeaders();
    Collection<State> states = (Collection<State>) stateRepo.findAll();
    String answer = "[";
    for (State s : states) {
      if (answer.length() > 1) {
        answer += "," + s.getJson();
      } else {
        answer += s.getJson();
      }
    }
    answer += "]";
    return new ResponseEntity<String>(answer, headers, HttpStatus.OK);
  }

  @RequestMapping(value = "/states/{state}", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<String> getState(@PathVariable(value = "state") String stateAbbreviation, Model model) {
    final HttpHeaders headers = new HttpHeaders();
    Optional<State> state = stateRepo.findByAbbreviation(stateAbbreviation.toUpperCase());
    if (state.isPresent()) {
      return new ResponseEntity<String>(state.get().getJson(), headers, HttpStatus.OK);
    }
    return new ResponseEntity<String>(
        "[{name:" + null + ",abbreviation:" + null + ",motto:" + null + ",citiesUrl:" + null + ",fish:" + null + "}]",
        headers, HttpStatus.OK);
  }

  @RequestMapping(value = "/post/state/", method = RequestMethod.POST)
  public void postState(@RequestBody HttpEntity<String> postBody)
  {
    if(postBody.hasBody()){
      Optional<State> checkByAbbreviation;
      Optional<State> checkByName;
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
      checkByAbbreviation = stateRepo.findByAbbreviation(proposedState.getAbbreviation());
      checkByName = stateRepo.findByName(proposedState.getName());
      if(!checkByAbbreviation.isPresent() && !checkByName.isPresent()){
        stateRepo.save(proposedState);
      }
    }
  }
}