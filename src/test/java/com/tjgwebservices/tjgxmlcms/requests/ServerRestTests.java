package com.tjgwebservices.tjgxmlcms.requests;

import com.tjgwebservices.tjgxmlcms.services.Post;
import com.tjgwebservices.tjgxmlcms.services.RestErrorHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import org.springframework.http.ResponseEntity;
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
@ExtendWith({SpringExtension.class,MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
@Import(RestErrorHandler.class)
public class ServerRestTests {

    @Mock
    private RestTemplate restTemplate;
    private MockRestServiceServer server;
    private RestTemplateBuilder builder;

    @BeforeEach
    public void setUp() throws Exception {
        builder = new RestTemplateBuilder();
        restTemplate = builder
                .errorHandler(new RestErrorHandler())
                .build();
        server = MockRestServiceServer.createServer(restTemplate);        
    }

    @Test
    public void testSimpleGet() throws Exception {
        server.expect(manyTimes(),requestTo("/topics"))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withSuccess("Success", TEXT_PLAIN));
    }

    @Test
    public void testSimplePost() throws Exception {
        server.expect(manyTimes(),requestTo("/topics"))
        .andExpect(method(HttpMethod.POST))
        .andRespond(withSuccess("Success", TEXT_PLAIN));
    }

    @Test
    public void testSimpleHead() throws Exception {
        server.expect(manyTimes(),requestTo("/topics"))
        .andExpect(method(HttpMethod.HEAD))
        .andRespond(withSuccess("Success", TEXT_PLAIN));
    }

    @Test
    public void testSimplePut() throws Exception {
        server.expect(manyTimes(),requestTo("/topics"))
        .andExpect(method(HttpMethod.PUT))
        .andRespond(withSuccess("Success", TEXT_PLAIN));
    }

    @Test
    public void testSimpleTrace() throws Exception {
        server.expect(manyTimes(),requestTo("/topics"))
        .andExpect(method(HttpMethod.TRACE))
        .andRespond(withSuccess("Success", TEXT_PLAIN));
    }

    @Test
    @Disabled
    public void testSimpleGetJson() throws Exception {
        String url = "/articleList";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        headers.set("X-Request-Source","Desktop");
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
               url,
               HttpMethod.GET,
               request,
               String.class,
               1
        );
        String getstring = restTemplate.postForObject("/articleList",1,String.class);
        System.out.println(getstring);
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println(response.getBody());
            
        } else {
            System.out.println("Request Failed");
        }
       //server.verify();
    }

    @Test
    @Disabled
    public void testMultipleGetJson() throws Exception {
        String url = "/articleList";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        headers.set("X-Request-Source","Desktop");
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<Post> response = restTemplate.exchange(
               url,
               HttpMethod.POST,
               request,
               Post.class
        );
        String getstring = restTemplate.postForObject("/articleList",1,String.class);
        System.out.println(getstring);
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println(response.getBody());
            
        } else {
            System.out.println("Request Failed");
        }
       //server.verify();
    }
    
    @Test
    @Disabled
    public void testConcurrentMessages() throws Exception {
        System.out.println("Test Concurrent Messages");
        //RestTemplate restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
    List<String> uris = new ArrayList<String>();
        uris.add("/topics/1");
        uris.add("/topics/2");
        uris.add("/topics/3");
    List<String> responses= new ArrayList<String>();
    uris.stream()
            .forEach(str->
                responses.add(restTemplate.postForObject("/topics",str,String.class))
            );
    CompletableFuture.allOf(responses.stream()
            .map(response ->sendMessage(response))
            .toArray(CompletableFuture<?>[]::new))
            .join();
    }


    private CompletableFuture sendMessage(String request) {
        System.out.println(request);
        CompletableFuture<String> cf1 =
                CompletableFuture.completedFuture("one");
        return cf1;
    }

    @AfterEach
    public void cleanUp() throws Exception {
        //server.reset();
    }
    
}
