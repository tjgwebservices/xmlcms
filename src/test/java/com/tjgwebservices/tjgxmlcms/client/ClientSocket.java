package com.tjgwebservices.tjgxmlcms.client;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import com.tjgwebservices.tjgxmlcms.services.rest.RestErrorHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class ClientSocket {

	@Autowired
	private TestRestTemplate testRestTemplate;

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
	void testSocket() {
		HttpHeaders headers = testRestTemplate.headForHeaders("/socket");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testSocket1() {
		HttpHeaders headers = testRestTemplate.headForHeaders("/socket/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testSocketTemplate() {
                builder = new RestTemplateBuilder();
                RestTemplate rt = builder.build();

                server = MockRestServiceServer
                        .createServer(rt);
                server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/socket/1"))
                        .andExpect(method(HttpMethod.POST))
                        .andExpect(content().string(""))
                        .andRespond(withStatus(HttpStatus.OK));
                        String responseString = rt.postForObject("/socket/1",
                                "test",String.class
                                );
                        Assertions.assertEquals("test",responseString);
                server.verify();
 
        }

	@Test
	void testSocketTemplate2() {
                builder = new RestTemplateBuilder();
                RestTemplate rt = builder.build();
                

                server = MockRestServiceServer
                        .createServer(rt);
                server
                .expect(ExpectedCount.manyTimes(),
                        requestTo("/socket/2"))
                        .andExpect(method(HttpMethod.POST))
                        .andExpect(content().string(""))
                        .andRespond(withStatus(HttpStatus.OK));
                        String responseString = rt.postForObject("/socket/2",
                                "test",String.class
                                );
                        Assertions.assertEquals("test",responseString);
                server.verify();
 
        }

    @Test
    public void testGetSocketClient() throws Exception {
        HttpResponse response = Request.Get("/socket")
                .bodyForm(Form.form()
        .build())
        .execute().returnResponse();		
        Assertions.assertEquals(200,response.getStatusLine().getStatusCode());

    }

        
    @Test
    public void testSocketClient() throws Exception {
        HttpResponse response = Request.Post("/socket")
                .bodyForm(Form.form()
        .build())
        .execute().returnResponse();		
        Assertions.assertEquals(200,response.getStatusLine().getStatusCode());

    }

    @Test
    public void testSocketClient1() throws Exception {
        HttpResponse response = Request.Post("/socket/1")
                .bodyForm(Form.form()
        .build())
        .execute().returnResponse();		
        Assertions.assertEquals(200,response.getStatusLine().getStatusCode());

    }
    
}
