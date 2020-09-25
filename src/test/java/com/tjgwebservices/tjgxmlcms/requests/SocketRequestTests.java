package com.tjgwebservices.tjgxmlcms.requests;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import static org.springframework.http.HttpMethod.POST;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.client.ExpectedCount.twice;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.MockRestServiceServer.bindTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import org.springframework.web.client.RestTemplate;

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class SocketRequestTests {

    private RestTemplate restTemplate;
    private MockRestServiceServer server;
    
    @BeforeEach
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();        
    }
    

    
    @Test
    public void testJsonRequest() throws Exception {
        System.out.println("Test Json Request");
        String string = "\"[{\"id\":\"1\",\"message\":\"test\"}]\"";
        byte[] b = string.getBytes(StandardCharsets.UTF_8);
        RestTemplate restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
        server.expect(manyTimes(),requestTo("/topics"))
          .andExpect(content().json(new String(b, "UTF-8")))
          .andRespond(withSuccess(b, APPLICATION_JSON));
        //server.verify();

    }

    @Test
    public void testArticleRequest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
        server.expect(manyTimes(),requestTo("/articleList"))
          .andExpect(content().json("[{}]"))
          .andRespond(withSuccess("success", APPLICATION_JSON));
        //server.verify();
    }

    @Test
    public void testPublishRequest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
        server.expect(manyTimes(),requestTo("/publish"))
          .andExpect(method(POST))
          .andExpect(content().json("[{}]"))
          .andRespond(withSuccess("success", APPLICATION_JSON));
        //server.verify();
    }

    @Test
    public void testSocketRequest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
        server.expect(manyTimes(),requestTo("/socket"))
          .andExpect(method(POST))
          .andExpect(content().json("[{}]"))
          .andRespond(withSuccess("success", APPLICATION_JSON));
        //server.verify();
    }
    
    @Test
    public void testTemplate() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        ResponseEntity<Map> serviceResponse = 
        new ResponseEntity<Map>(map, HttpStatus.OK);
        server.expect(manyTimes(),
                requestTo("/topics"))
                .andRespond(withSuccess(
                        "{\"id\":\"1\",\"message\":\"test\"}",
                        MediaType.APPLICATION_JSON));
        //server.verify();
    }
    
    @Test
    public void testTemplateOnce() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        ResponseEntity<Map> serviceResponse = 
        new ResponseEntity<Map>(map, HttpStatus.OK);
        server.expect(once(),
                requestTo("/topics"))
                .andRespond(withSuccess(
                        "{\"id\":\"1\",\"message\":\"test\"}",
                        MediaType.APPLICATION_JSON));
        //server.verify();
    }

    @Test
    public void testTemplateTwice() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        ResponseEntity<Map> serviceResponse = 
        new ResponseEntity<Map>(map, HttpStatus.OK);
        server.expect(twice(),
                requestTo("/topics"))
                .andRespond(withSuccess(
                        "{\"id\":\"1\",\"message\":\"test\"}",
                        MediaType.APPLICATION_JSON));
        //server.verify();
    }

    @Test
    public void testTemplateFiveTimes() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        ResponseEntity<Map> serviceResponse = 
        new ResponseEntity<Map>(map, HttpStatus.OK);
        server.expect(times(5),
                requestTo("/topics"))
                .andRespond(withSuccess(
                        "{\"id\":\"1\",\"message\":\"test\"}",
                        MediaType.APPLICATION_JSON));
        //server.verify();
    }

    @AfterEach
    public void cleanUp() throws Exception {
        server.reset();
    }
    
}
