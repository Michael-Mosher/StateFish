package org.wecancodeit.StateFish.View;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class JsonView {
  @Resource
  private StateRepository stateRepo;
  
  @RequestMapping(value="/states", method=RequestMethod.GET, produces="application/json")
  public ResponseEntity<String> getStates(Model model)
  {
    final HttpHeaders headers = new HttpHeaders();
    Collection<State> states = stateRepo.findAll();
    String answer = "[";
    for (State s : states) {
      if(answer.length()>1){
        answer += ",{" + s.getJson() "}";
      } else {
        
      }
    }
//    return new ResponseEntity<String>("[{\"abbreviation\":\"OH\",\"name\":\"Ohio\"," + 
//        "\"motto\":\"Birthplace of Aviation\",\"fish\":null,\"citiesUrl\":\"/states/oh/cities\"},{" + 
//        "\"abbreviation\": \"HI\",\"name\":\"Hawaii\",\"motto\":\"The Islands of Aloha\"," + 
//        "\"fish\": {\"name\":\"Humuhumunukunukuāpuaʻa\"," + 
//        "\"binomialNomenclature\":\"Rhinecanthus rectangulus\",\"imgUrl\":" + 
//        "\"https://upload.wikimedia.org/wikipedia/commons/5/52/Reef_Triggerfish_1.JPG\"" + 
//        "},\"citiesUrl\":\"/states/hi/cities\"}]",headers, HttpStatus.OK);
  }
  @RequestMapping(value="/states/{state}", method=RequestMethod.GET, produces="application/json")
  public ResponseEntity<String> getState(@PathVariable(value="state")String state, Model model)
  {
    final HttpHeaders headers = new HttpHeaders();
    return new ResponseEntity<String>("[{\"abbreviation\":\"OH\",\"name\":\"Ohio\"," + 
        "\"motto\":\"Birthplace of Aviation\",\"fish\":null,\"citiesUrl\":\"/states/oh/cities\"}]",
        headers, HttpStatus.OK);
  }
}
