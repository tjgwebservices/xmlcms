package com.tjgwebservices.tjgxmlcms.requests;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscriber;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.MockRestServiceServer.bindTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class RequestResponseTests {


    private RestTemplate restTemplate;
    private MockRestServiceServer server;

    @BeforeEach
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();        
    }

    @Test
    void testObjectTopicsPost() throws Exception {
        String string = "\"[{\"id\":\"1\",\"message\":\"test\"}]\"";
        server.expect(manyTimes(),requestTo("/topics"))
          .andExpect(method(HttpMethod.GET))
          .andRespond(withSuccess(string, APPLICATION_JSON));
    }

   
    @Test
    void testTopicsPost() throws Exception {
        String string = "\"[{\"id\":\"1\",\"message\":\"test\"}]\"";
        byte[] b = string.getBytes(StandardCharsets.UTF_8);
        server.expect(manyTimes(),requestTo("/topics"))
          .andExpect(method(HttpMethod.GET))
          .andRespond(withSuccess(b, APPLICATION_JSON));
        //server.verify();
    }
    
    @Test
    void testTopicsRequest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
        String string = "\"[{\"id\":\"1\",\"message\":\"test\"}]\"";
        byte[] b = string.getBytes(StandardCharsets.UTF_8);
        server.expect(manyTimes(),requestTo("/topics"))
          .andExpect(method(HttpMethod.GET))
          .andRespond(withSuccess(b, APPLICATION_JSON));
        //server.verify();     
    }

    @Test
    void testTopicsPostTo() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
        String string = "\"[{\"id\":\"1\",\"message\":\"test\"}]\"";
        byte[] b = string.getBytes(StandardCharsets.UTF_8);
        server.expect(manyTimes(),requestTo("/topics"))
          .andExpect(method(HttpMethod.GET))
          .andRespond(withSuccess(b, APPLICATION_JSON));
        //server.verify();        
    }

    @Test
    void testTopicsArticleListTo() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
        String string = "\"[{\"id\":\"1\",\"message\":\"test\"}]\"";
        byte[] b = string.getBytes(StandardCharsets.UTF_8);
        server.expect(manyTimes(),requestTo("/articleList"))
          .andExpect(method(HttpMethod.GET))
          .andRespond(withSuccess(b, APPLICATION_JSON));
        //server.verify();        
    }

    @Test
    void testSocketsTo() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
        String string = "\"[{\"id\":\"1\",\"message\":\"test\"}]\"";
        byte[] b = string.getBytes(StandardCharsets.UTF_8);
        server.expect(manyTimes(),requestTo("/sockets"))
          .andExpect(method(HttpMethod.GET))
          .andRespond(withSuccess(b, APPLICATION_JSON));
        //server.verify();        
    }
    
    @Test
    void testPublishRequest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
        Map<String,SocketSubscriber> postParameters = new HashMap<String,SocketSubscriber>();
        SocketSubscriber subscriber = new SocketSubscriber("publisher");
        postParameters.put("publisher",subscriber);
        server.expect(manyTimes(),requestTo("/topics"))
          .andExpect(method(HttpMethod.POST))
          .andRespond(withSuccess(postParameters.toString(), APPLICATION_JSON));
    }

    
}
