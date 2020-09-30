package com.tjgwebservices.tjgxmlcms.services;

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
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ExtendWith({SpringExtension.class})
@Import(RestErrorHandler.class)
public class LoginPageTests {
    
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
    public void testLogin() {
        Assertions.assertNotNull(this.builder);
        Assertions.assertNotNull(this.server);
        this.server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/login"))
                        .andExpect(method(HttpMethod.GET))
                        .andExpect(content().string(""))
                        .andRespond(withStatus(HttpStatus.OK));                
                        restTemplate.getForObject("/login",Object.class);
       
        this.server.verify();
    }

    @Test
    public void testAuthenticated() {
        this.server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/authenticated"))
                        .andExpect(method(HttpMethod.GET))
                        .andExpect(content().string(""))
                        .andRespond(withStatus(HttpStatus.NOT_FOUND));
                        restTemplate.getForObject("/authenticated",Object.class);
       
        this.server.verify();
    }

    @Test
    public void testAuthenticatedAdmin() {
        server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/authenticated/admin"))
                        .andExpect(method(HttpMethod.GET))
                        .andExpect(content().string(""))
                        .andRespond(withStatus(HttpStatus.OK));
                        restTemplate.getForObject("/authenticated/admin",String.class);
        server.verify();
    }

    @Test
    public void testLoginOk() {
        server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/login"))
                        .andExpect(method(HttpMethod.GET))
                        .andExpect(content().string(""))
                        .andRespond(withStatus(HttpStatus.OK));
                        restTemplate.getForObject("/login",String.class);
        server.verify();
    }
    
    @Test
    public void testAuthenticatedUser() {
        server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/authenticated/user"))
                        .andExpect(method(HttpMethod.GET))
                        .andExpect(content().string(""))
                        .andRespond(withStatus(HttpStatus.OK));
                        restTemplate.getForObject("/authenticated/user",Object.class);
        server.verify();
    }

    @Test
    public void testLoginPage() {
        server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/login"))
                        .andExpect(method(HttpMethod.GET))
                        .andExpect(content().string(""))
                        .andRespond(withStatus(HttpStatus.OK));
                        restTemplate.getForObject("/login",Object.class);
        server.verify();
    }

    
    @AfterEach
    public void cleanUp(){
        //server.reset();
    }
    
}
