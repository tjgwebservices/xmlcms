package com.tjgwebservices.tjgxmlcms.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjgwebservices.tjgxmlcms.controller.WebSocketController;
import com.tjgwebservices.tjgxmlcms.services.rest.RestErrorHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ExtendWith({SpringExtension.class,MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
@Import(RestErrorHandler.class)
//@WebMvcTest(controllers = WebSocketController.class)
@AutoConfigureMockMvc
public class ResponseSocketTests {


    @Mock
    private RestTemplate restTemplate;
    private MockRestServiceServer server;
    private RestTemplateBuilder builder;

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    

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
    public void testSimpleGetResponse() throws Exception {
        ResponseActions expect = server.expect(manyTimes(),requestTo("/topics"));
        ResponseActions response = expect.andExpect(requestTo("/topics"));
        response.andRespond(withSuccess("Success", TEXT_PLAIN));
    }

    
    @Test
    @Disabled
    void testRequest4() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.post("/sockets")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("test");
        mockMvc.perform(rb).andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        
    }

    @Test
    @Disabled
    void testRequest5() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.post("/sockets")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("test");
        mockMvc.perform(rb).andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        
    }
    
}
