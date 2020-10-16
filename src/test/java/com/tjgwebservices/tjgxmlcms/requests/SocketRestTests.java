package com.tjgwebservices.tjgxmlcms.requests;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import com.tjgwebservices.tjgxmlcms.controller.WebSocketHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.springframework.test.web.client.ExpectedCount.times;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.MockRestServiceServer.bindTo;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
//@RunWith(MockitoJunitRunner.class)
public class SocketRestTests {

    private RestTemplate restTemplate;
    private MockRestServiceServer server;
    
    @BeforeEach
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();        
    }
    

    @Test
    public void verifyOpenSocketConnection(){
        WebSocketSession session = mock(WebSocketSession.class);
        TextMessage textMsg = new TextMessage("Test Message".getBytes());
        when(session.isOpen()).thenReturn(true);
        WebSocketHandler textHandler = new WebSocketHandler();
        try {
            textHandler.handleTextMessage(session, textMsg);
            server.verify();
                //verify(session, times(1)).sendMessage(textMsg);
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(SocketRestTests.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    @AfterEach
    public void cleanUp() throws Exception {
        server.reset();
    }
    
}

