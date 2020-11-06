package com.tjgwebservices.tjgxmlcms.services;

import com.tjgwebservices.tjgxmlcms.services.rest.RestErrorHandler;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import org.apache.http.HttpEntity;
import org.junit.jupiter.api.Test;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Assertions;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.MockRestServiceServer.bindTo;
import org.springframework.web.client.RestTemplate;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ExtendWith({SpringExtension.class})
@Import(RestErrorHandler.class)
public class FormUploadTests {
    private RestTemplate restTemplate;
    private MockRestServiceServer server;
    private String port;
    
    @Autowired
    Environment environment;


    @BeforeEach
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();    
        port = environment.getProperty("local.server.port");
        
    }
    
    @Test
    @Disabled
    public void testHTTPClient()
	throws ClientProtocolException, IOException {
		HttpResponse response = Request.Post("http://localhost:"+port+"/login").bodyForm(Form.form()
		.add("username","username").add("password","pass").build())
		.execute().returnResponse();		
		Assertions.assertEquals(200,response.getStatusLine().getStatusCode());
	}

    @Test
    @Disabled
    public void testMainPage()
	throws ClientProtocolException, IOException {
		HttpResponse response = Request.Post("http://localhost:"+port+"/")
                        .bodyForm(Form.form()
		.add("username","username").add("password","pass").build())
		.execute().returnResponse();
		
		Assertions.assertEquals(405,response.getStatusLine().getStatusCode());
	}

    @Test
    @Disabled
    public void whenSendMultipartRequest() throws ClientProtocolException, IOException {
	CloseableHttpClient client = HttpClients.createDefault();
	HttpPost httpPost = new HttpPost("http://localhost:"+port+"/login");
	MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	builder.addTextBody("username","username");
	builder.addBinaryBody("file", new File("sample.xml"), ContentType.APPLICATION_OCTET_STREAM,
	"file.ext");
	HttpEntity multipart = builder.build();
	httpPost.setEntity(multipart);
	CloseableHttpResponse response = client.execute(httpPost);
	Assertions.assertEquals(response.getStatusLine().getStatusCode(), 200);
	client.close();	
    }

    @Test
    @Disabled
    public void testRestTemplate(){
        final String uri = "http://localhost:"+port+"/login";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        headers.set("X-Request-Source","Desktop");
        RestTemplate restTemplate = new RestTemplate();
        org.springframework.http.HttpEntity request = new org.springframework.http.HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
               uri,
               HttpMethod.GET,
               request,
               String.class,
               1
        );
        Assertions.assertEquals("200 OK",response.getStatusCode().OK.toString());
        server.verify();
    }

    @Test
    @Disabled
    public void testRestHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        headers.set("X-Request-Source","Desktop");

        final String uri = "http://localhost:"+port+"/login";
        RestTemplate restTemplate = new RestTemplate();
        org.springframework.http.HttpEntity request = new org.springframework.http.HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
               uri,
               HttpMethod.GET,
               request,
               String.class,
               1
        );
        Assertions.assertEquals("200 OK",response.getStatusCode().OK.toString());
        server.verify();
    }
    
    
    @AfterEach
    public void cleanUp() throws Exception {
        restTemplate = new RestTemplate();        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        headers.set("X-Request-Source","Desktop");
        org.springframework.http.HttpEntity request = new org.springframework.http.HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
               "http://localhost:"+port+"/display",
               HttpMethod.GET,
               request,
               String.class,
               1
        );
        Assertions.assertEquals("200 OK",response.getStatusCode().OK.toString());
        server.verify();
    }
}
