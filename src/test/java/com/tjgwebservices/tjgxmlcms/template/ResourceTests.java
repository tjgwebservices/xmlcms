package com.tjgwebservices.tjgxmlcms.template;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class ResourceTests {

	@Autowired
	private TestRestTemplate restTemplate;
        
	@Test
	void testResources() {
		HttpHeaders headers = restTemplate.headForHeaders("/css/style.css");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testJavaScriptLoads() {
		HttpHeaders headers = restTemplate.headForHeaders("/js/script.js");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

        @Test
	void testIndexPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/index");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

        @Test
	void testDisplayPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/display");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

        @Test
	void testLoginPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/login");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testWorkshopPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/workshop");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testForumPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/forum");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testConferencePage() {
		HttpHeaders headers = restTemplate.headForHeaders("/conference");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testReportsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/report");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
}
