package com.tjgwebservices.tjgxmlcms.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import com.tjgwebservices.tjgxmlcms.controller.MainController;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class TopicRestTests {

    @Mock
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();
    
    @InjectMocks
    private MainController mc = new MainController();
    
    @BeforeEach
    public void setup(){
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

   @Test
    public void testTopics()throws Exception{
        String testMock = "{\"topic\":\"test\"}";
        Mockito
                .when(restTemplate.getForEntity(
                        "/topics/1",
                        String.class
                ))
                .thenReturn(new ResponseEntity(testMock,HttpStatus.OK));
        Map<String,String> map = new HashMap<String,String>();
        ModelAndView model = new ModelAndView("articleList", 
                map);
        mockServer.verify();
    }
    
}
