package com.tjgwebservices.tjgxmlcms.client;

import com.tjgwebservices.tjgxmlcms.services.rest.RestErrorHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ExtendWith({SpringExtension.class})
public class WebClientTests {

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
    

    
    @AfterEach
    public void cleanUp(){
        //server.reset();
    }
    
}
