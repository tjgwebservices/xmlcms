package com.tjgwebservices.tjgxmlcms.requests;

import com.tjgwebservices.tjgxmlcms.services.SocketServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(SocketServiceImpl.class)
public class RequestServiceTest {

    @Autowired
    private MockRestServiceServer server;
    
    @Autowired
    private SocketServiceImpl client;
    
    @Test
    public void testServiceCall() {
        this.server.expect(ExpectedCount.manyTimes(),
                requestTo("/sockets"))
                .andRespond(withSuccess("<root></root>",
                        MediaType.TEXT_PLAIN));
        String userServiceResponse = client.testSocketService();
        Assertions.assertEquals("<root></root>",userServiceResponse);
        
    }

    @Test
    public void testServiceCall2() {
        this.server.expect(ExpectedCount.manyTimes(),
                requestTo("/sockets"))
                .andRespond(withSuccess("<root></root>",
                        MediaType.TEXT_PLAIN));
        String userServiceResponse = client.testSocketService();
        Assertions.assertEquals("<root></root>",userServiceResponse);
        
    }

    @Test
    public void testServiceCall3() {
        this.server.expect(ExpectedCount.manyTimes(),
                requestTo("/sockets"))
                .andRespond(withSuccess("<root></root>",
                        MediaType.TEXT_PLAIN));
        String userServiceResponse = client.testSocketService();
        Assertions.assertEquals("<root></root>",userServiceResponse);
        
    }

}
