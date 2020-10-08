package com.tjgwebservices.tjgxmlcms.template;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import static org.assertj.core.api.Assertions.assertThat;
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
		assertThat(headers.getContentLength()).isEqualTo(140);
	}

	@Test
	void testJavaScriptLoads() {
		HttpHeaders headers = restTemplate.headForHeaders("/js/script.js");
		assertThat(headers.getContentLength()).isEqualTo(20);
	}

        @Test
	void testIndexPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/index");
		assertThat(headers.getContentLength()).isEqualTo(4031);
	}

        @Test
	void testDisplayPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/display");
		assertThat(headers.getContentLength()).isEqualTo(2936);
	}

        @Test
	void testLoginPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/login");
		assertThat(headers.getContentLength()).isEqualTo(3157);
	}

	@Test
	void testWorkshopPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/workshop");
		assertThat(headers.getContentLength()).isEqualTo(4359);
	}

	@Test
	void testForumPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/forum");
		assertThat(headers.getContentLength()).isEqualTo(4506);
	}

	@Test
	void testConferencePage() {
		HttpHeaders headers = restTemplate.headForHeaders("/conference");
		assertThat(headers.getContentLength()).isEqualTo(4452);
	}

	@Test
	void testReportsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/report");
		assertThat(headers.getContentLength()).isEqualTo(4339);
	}
        
}
