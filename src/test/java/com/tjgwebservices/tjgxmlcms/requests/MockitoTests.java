package com.tjgwebservices.tjgxmlcms.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjgwebservices.tjgxmlcms.controller.MainController;
import com.tjgwebservices.tjgxmlcms.model.article.Article;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith({SpringExtension.class})
public class MockitoTests {
    
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
    public void givenMockShouldReturnMock()throws Exception{
        Article art = new Article("test","test","test","test","test");
        Mockito
                .when(restTemplate.getForEntity(
                        "/topics",
                        Article.class
                ))
                .thenReturn(new ResponseEntity(art,HttpStatus.OK));
        Map<String,String> map = new HashMap<String,String>();
        ModelAndView model = new ModelAndView("articleList", 
                map);
        mockServer.verify();
    }

        @Test
    public void givenMockOnSockets()throws Exception{
        Article art = new Article("test","test","test","test","test");
        Mockito
                .when(restTemplate.getForEntity(
                        "/sockets",
                        Article.class
                ))
                .thenReturn(new ResponseEntity(art,HttpStatus.OK));
        Map<String,String> map = new HashMap<String,String>();
        ModelAndView model = new ModelAndView("articleList", 
                map);
        mockServer.verify();
    }

    @Test
    public void givenMockArticleShouldReturnMockArticle()throws Exception{
        Article art = new Article("test","test","test","test","test");
        Mockito
                .when(restTemplate.getForEntity(
                        "/sockets",
                        Article.class
                ))
                .thenReturn(new ResponseEntity(art,HttpStatus.OK));
        Map<String,String> map = new HashMap<String,String>();
        ModelAndView model = new ModelAndView("articleList", 
                map);
        mockServer.verify();
    }

    @Test
    public void givenMockArticleonArticleList()throws Exception{
        Article art = new Article("test","test","test","test","test");
        Mockito
                .when(restTemplate.getForEntity(
                        "/articles/articleList",
                        Article.class
                ))
                .thenReturn(new ResponseEntity(art,HttpStatus.OK));
        Map<String,String> map = new HashMap<String,String>();
        ModelAndView model = new ModelAndView("articles/articleList", 
                map);
        mockServer.verify();
    }
    
    @Test
    public void testMockito() {      
        Mockito
        .when(restTemplate.getForEntity(
                "/articles/articleList",
                Article.class
        ))
        .thenReturn(new ResponseEntity(new String("[{}]"),HttpStatus.OK));
        mockServer.verify();
    }
}
