package org.wecancodeit.StateFish.View;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.nio.charset.Charset;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class JsonTest {
  
  @Autowired
  private MockMvc fakeMvc;
  @Autowired
  TestRestTemplate restTester;
  public static final MediaType APPLICATION_JSON_UTF8 =
      new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  @Test
  public void assertStateQueryReturnsJsonWithNameAndFishFields() throws Exception
  {
    // Assemble
    String url = "http://localhost:8080";
    String target = "/states";
    String jsonMimeType = "application/json";
    // Action
    // Assert
    org.assertj.core.api.Assertions.assertThat(this.restTester.getForObject(target, String.class)).contains("name");
    this.fakeMvc.perform(get(target)).andDo(print()).andExpect(status().isOk()
        ).andExpect(content().contentTypeCompatibleWith(jsonMimeType)
        ).andExpect(content().string(containsString("name:"))
        ).andExpect(content().string(containsString("fish:"))
    );
  }
  
  @Test
  public void assertGetStateOhQueryReturnsJsonWithNameAndCitiesUrl() throws Exception
  {
    // Assemble
    String url = "http://localhost:8080";
    String target = "/states/";
    String stateArg = "oh";
    String jsonMimeType = "application/json";
    // Action
    // Assert
    this.fakeMvc.perform(get(target+stateArg)).andDo(print()).andExpect(status().isOk()
        ).andExpect(content().contentTypeCompatibleWith(jsonMimeType)
        ).andExpect(content().string(containsString("name:Ohio"))
        ).andExpect(content().string(containsString("citiesUrl:"))
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
    // Action
    // Assert
    this.fakeMvc.perform(get(target+stateArg)).andDo(print()).andExpect(status().isOk()
        ).andExpect(content().contentTypeCompatibleWith(jsonMimeType)
        ).andExpect(content().string(containsString("name:Hawai'i"))
        ).andExpect(content().string(containsString("citiesUrl:"))
    );
  }
  
  @Test
  public void assertPostIsOkAndEmptyWithValidBody() throws Exception
  {
    String target = "/post/state/";
    State fakeState = new State("Dischord", "DC", "Arrgh", new Fish("Slippery", "Icthocalamatus", "https://no-such.org/"));
    ObjectMapper mapper = new ObjectMapper();
    String body = mapper.writeValueAsString(fakeState);
    
//    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//    String requestJson=ow.writeValueAsString(anObject);

    fakeMvc.perform(post(target).contentType(APPLICATION_JSON_UTF8)
        .content(body))
        .andExpect(status().isOk())
        .equals(null);
  }
}
