package com.tjgwebservices.tjgxmlcms.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjgwebservices.tjgxmlcms.controller.WebSocketController;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscriber;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ExtendWith({SpringExtension.class,MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
//@WebMvcTest(controllers = WebSocketController.class)
@AutoConfigureMockMvc
public class ResponseBodyTests {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    @Disabled
    void testSockets() throws Exception {
        mockMvc.perform(post("/socket"))
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    void testSocketsAndParameter() throws Exception {
        SocketSubscriber socket = new SocketSubscriber();
        mockMvc.perform(post("/socket/1"))
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    void testTopics() throws Exception {
        mockMvc.perform(post("/topics/1"))
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    void testRequest1() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/topics/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();
        
        mockMvc.perform(post("/topics/1"))
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    void testRequest2() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/topics/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();
        
        mockMvc.perform(post("/topics/1"))
                .andExpect(status().isOk());
    }
 
    @Test
    @Disabled
    void testRequest3() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.post("/topics/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("test");
        mockMvc.perform(rb).andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        
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
