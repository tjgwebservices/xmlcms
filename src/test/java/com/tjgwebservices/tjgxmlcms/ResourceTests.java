package com.tjgwebservices.tjgxmlcms;

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
    
}
