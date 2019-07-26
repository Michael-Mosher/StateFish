package org.wecancodeit.StateFish;

import static org.hamcrest.CoreMatchers.containsString;

import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.wecancodeit.StateFish.Model.Fish;
import org.wecancodeit.StateFish.Model.State;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class JsonTest
{
  @Autowired(required = false)
  private MockMvc fakeMvc;

  @Autowired(required = false)
  TestRestTemplate restTester;

  public static final MediaType APPLICATION_JSON_UTF8 =
      new MediaType(
          MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          Charset.forName("utf8"));

  @Test
  public void assertStateQueryReturnsJsonWithNameAndFishFields() throws Exception
  {
    // Assemble
    String target = "/states";
    String jsonMimeType = "application/json";
    // Action
    // Assert
    org.assertj.core.api.Assertions.assertThat(
        this.restTester.getForObject(target, String.class)
        ).contains("name");
    this.fakeMvc.perform(get(target)).andDo(print()).andExpect(status().isOk()
        ).andExpect(content().contentTypeCompatibleWith(jsonMimeType)
        ).andExpect(content().string(containsString("name:"))
        ).andExpect(content().string(containsString("fish:"))
    );
  }

  @Test
  public void assertStateQuery_OH_ReturnsNameMottoFish() throws Exception
  {
    // Assemble
    String target = "/states";
    String stateAbbreviation = "oh";
    // Action
    // Assert
    this.fakeMvc.perform(get(target + "/" + stateAbbreviation)).andDo(print()
        //).andExpect(status().isOk()
        ).andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_UTF8)
        ).andExpect(content().string(containsString("name:Ohio"))
        ).andExpect(content().string(containsString("citiesUrl:") )
        ).andExpect(content().string(containsString("motto:Birthplace of Aviation") ) 
    );
  }

  @Test
  public void assertGetStateHiQueryReturnsJsonWithNameAndCitiesUrl() throws Exception
  {
    // Assemble
    String url = "http://localhost:8080";
    String target = "/states/";
    String stateArg = "hi";
    String jsonMimeType = "application/json";
    Fish hawaiFish = new Fish(
        "Humuhumunukunukuāpuaʻa",
        "Rhinecanthus rectangulus",
        "https://upload.wikimedia.org/wikipedia/commons/5/52/Reef_Triggerfish_1.JPG");
    // Action
    // Assert
    this.fakeMvc.perform(get(target+stateArg)).andDo(print()).andExpect(status().isOk()
        ).andExpect(content().contentTypeCompatibleWith(jsonMimeType)
        ).andExpect(content().string(containsString("name:Hawai'i"))
        ).andExpect(content().string(containsString("citiesUrl:"))
        ).andExpect(content().string(containsString("fish:" + hawaiFish.getJson()))
    );
  }
  
  @Test
  public void assertPostIsOkAndEmptyWithValidBody() throws Exception
  {
    String target = "/post/state/";
    State fakeState = new State("Dischord", "DC", "Arrgh", new Fish("Slippery", "Icthocalamatus", "https://no-such.org/"));
    ObjectMapper mapper = new ObjectMapper();
    String body = mapper.writeValueAsString(fakeState);
    fakeMvc.perform(post(target).contentType(APPLICATION_JSON_UTF8)
        .content(body))
        .andExpect(status().isOk())
        .equals(null);
  }
}