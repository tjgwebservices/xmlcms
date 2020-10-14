package com.tjgwebservices.tjgxmlcms.template;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
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
		HttpHeaders headers = restTemplate.headForHeaders("/conferences/workshop");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testForumPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/conferences/forum");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testConferencePage() {
		HttpHeaders headers = restTemplate.headForHeaders("/conferences/conference");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testReportsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/conferences/report");
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

	@Test
	void testClientsListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/clients");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testEmployersListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/employers");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
	void testHrGroupsListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/hrgroups");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testAddClientsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/addClient");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testAddEmployeesPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/addEmployer");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
	void testAddHrGroupPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/addHrGroup");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testEditClientsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/editClient/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testEditEmployersPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/editEmployer/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
	void testEditHrGroupPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/editHrGroup/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}


	@Test
	void testTopicListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/topics");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testProjectListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/projects");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
	void testResearchersListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/researchers");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testAddTopicPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/addTopic");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testAddProjectPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/addProject");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
	void testAddResearcherPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/addResearcher");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testEditTopicPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/editTopic/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testEditProjectPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/editProject/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
	void testEditResearcherPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/editResearcher/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
        
}
