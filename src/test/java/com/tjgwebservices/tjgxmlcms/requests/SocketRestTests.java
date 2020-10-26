package com.tjgwebservices.tjgxmlcms.requests;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.MockRestServiceServer.bindTo;
import org.springframework.web.client.RestTemplate;


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
    
    @AfterEach
    public void cleanUp() throws Exception {
        server.reset();
    }
    
}

