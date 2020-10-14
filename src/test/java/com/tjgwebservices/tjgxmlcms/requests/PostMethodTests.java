package com.tjgwebservices.tjgxmlcms.requests;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.MockRestServiceServer.bindTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class PostMethodTests {


    private RestTemplate restTemplate;
    private MockRestServiceServer server;

    @BeforeEach
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();        
    }

    @Test
    void testAddReseacher() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
        Map<String,String> postParameters = new HashMap<String,String>();
        postParameters.put("researcher","1");
        server.expect(manyTimes(),requestTo("/research/addResearchers"))
          .andExpect(method(HttpMethod.POST))
          .andRespond(withSuccess(postParameters.toString(), APPLICATION_JSON));
    }

    @Test
    void testAddTopic() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
        Map<String,String> postParameters = new HashMap<String,String>();
        postParameters.put("topic","1");
        server.expect(manyTimes(),requestTo("/research/addTopic"))
          .andExpect(method(HttpMethod.POST))
          .andRespond(withSuccess(postParameters.toString(), APPLICATION_JSON));
    }

    @Test
    void testAddProject() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
        Map<String,String> postParameters = new HashMap<String,String>();
        postParameters.put("project","1");
        server.expect(manyTimes(),requestTo("/research/addProject"))
          .andExpect(method(HttpMethod.POST))
          .andRespond(withSuccess(postParameters.toString(), APPLICATION_JSON));
    }

    @Test
    public void testPost1() throws Exception {
        server.expect(manyTimes(),requestTo("/research/editTopic/1"))
        .andExpect(method(HttpMethod.POST))
        .andRespond(withSuccess("Success", TEXT_PLAIN));
    }

    @Test
    public void testPost2() throws Exception {
        server.expect(manyTimes(),requestTo("/research/editProject/1"))
        .andExpect(method(HttpMethod.POST))
        .andRespond(withSuccess("Success", TEXT_PLAIN));
    }

    @Test
    public void testPost3() throws Exception {
        server.expect(manyTimes(),requestTo("/research/editResearcher/1"))
        .andExpect(method(HttpMethod.POST))
        .andRespond(withSuccess("Success", TEXT_PLAIN));
    }

    @Test
    public void testHrClientsList() {
        this.server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/hr/clients"))
                        .andExpect(method(HttpMethod.GET))
                        .andRespond(withStatus(HttpStatus.OK));
        restTemplate.getForObject("/hr/clients",Object.class);
       
        this.server.verify();
    }

    @Test
    public void testHrEmployersList() {
        this.server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/hr/employers"))
                        .andExpect(method(HttpMethod.GET))
                        .andRespond(withStatus(HttpStatus.OK));
        restTemplate.getForObject("/hr/employers",Object.class);
       
        this.server.verify();
    }

    @Test
    public void testHrGroupsList() {
        this.server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/hr/hrgroups"))
                        .andExpect(method(HttpMethod.GET))
                        .andRespond(withStatus(HttpStatus.OK));
        restTemplate.getForObject("/hr/hrgroups",Object.class);
       
        this.server.verify();
    }

    @Test
    public void testTopicsList() {
        this.server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/research/topics"))
                        .andExpect(method(HttpMethod.GET))
                        .andRespond(withStatus(HttpStatus.OK));
        restTemplate.getForObject("/research/topics",Object.class);
       
        this.server.verify();
    }

    @Test
    public void testProjectsList() {
        this.server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/research/projects"))
                        .andExpect(method(HttpMethod.GET))
                        .andRespond(withStatus(HttpStatus.OK));
        restTemplate.getForObject("/research/projects",Object.class);
       
        this.server.verify();
    }

    @Test
    public void testReseachersList() {
        this.server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/research/researchers"))
                        .andExpect(method(HttpMethod.GET))
                        .andRespond(withStatus(HttpStatus.OK));
        restTemplate.getForObject("/research/researchers",Object.class);
       
        this.server.verify();
    }
    
}
