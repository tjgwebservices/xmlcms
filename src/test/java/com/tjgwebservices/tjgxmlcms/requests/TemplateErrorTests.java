package com.tjgwebservices.tjgxmlcms.requests;

import com.tjgwebservices.tjgxmlcms.services.rest.RestErrorHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ExtendWith({SpringExtension.class})
@Import(RestErrorHandler.class)
public class TemplateErrorTests {
    
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
    public void testRestErrors() {
        Assertions.assertNotNull(this.builder);
        Assertions.assertNotNull(this.server);
        this.server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/sockets"))
                        .andExpect(method(HttpMethod.GET))
                        .andRespond(withStatus(HttpStatus.NOT_FOUND));
        restTemplate.getForObject("/sockets",Object.class);
       
        this.server.verify();
    }

    @Test
    public void testArticeList() {
        this.server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/articleList"))
                        .andExpect(method(HttpMethod.GET))
                        .andRespond(withStatus(HttpStatus.NOT_FOUND));
        restTemplate.getForObject("/articleList",Object.class);
       
        this.server.verify();
    }

    @Test
    public void testPublish() {
        server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/publish"))
                        .andExpect(method(HttpMethod.GET))
                        .andRespond(withStatus(HttpStatus.NOT_FOUND));
        restTemplate.getForObject("/publish",Object.class);
       
        server.verify();
    }

    @Test
    public void testTopics() {
        server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/topics"))
                        .andExpect(method(HttpMethod.GET))
                        .andRespond(withStatus(HttpStatus.NOT_FOUND));
        restTemplate.getForObject("/topics",Object.class);
       
        server.verify();
    }
    
    @AfterEach
    public void cleanUp(){
        //server.reset();
    }
    
}
