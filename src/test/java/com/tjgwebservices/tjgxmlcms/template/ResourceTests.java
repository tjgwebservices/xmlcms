package com.tjgwebservices.tjgxmlcms.template;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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

	@Test
	void testAddAdministratorsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addAdministrator");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testAddAdministratorGroupsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addAdministratorGroup");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testAddCoursesPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addCourse");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testAddLecturesPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addLecture");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testAddLecturersPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addLecturer");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testAddLectureNotesPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addLectureNote");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testAddSchoolsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addSchool");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testAddStudentsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addStudent");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testAdministratorListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/adminList");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
}
