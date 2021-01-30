package com.tjgwebservices.tjgxmlcms.template;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class ResourceTests {

	@Autowired
	private TestRestTemplate restTemplate;

        @BeforeEach
        public void setUp() throws Exception {
            //restTemplate = new TestRestTemplate();

        }        
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
        @Disabled
	void testIndexPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/index");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

        @Test
        @Disabled
	void testDisplayPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/display");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

        @Test
        @Disabled
	void testLoginPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/login");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testWorkshopPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/conferences/workshop");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testForumPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/conferences/forum");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testConferencePage() {
		HttpHeaders headers = restTemplate.headForHeaders("/conferences/conference");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testReportsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/conferences/report");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testConferenceRoomPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/conferences/room");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testAddAdministratorsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addAdministrator");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testAddAdministratorGroupsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addAdministratorGroup");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testAddCoursesPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addCourse");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testAddLecturesPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addLecture");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testAddLecturersPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addLecturer");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	@Disabled        
	void testAddLectureNotesPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addLectureNote");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testAddSchoolsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addSchool");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	@Disabled        
	void testAddStudentsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/addStudent");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testAdministratorListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/schools/adminList");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testClientsListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/clients");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testEmployersListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/employers");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
        @Disabled
	void testHrGroupsListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/hrgroups");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
        void testAddClientsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/addClient");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
        void testAddEmployeesPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/addEmployer");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
        @Disabled
        void testAddHrGroupPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/addHrGroup");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testEditClientsPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/editClient/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testEditEmployersPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/editEmployer/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
        @Disabled
	void testEditHrGroupPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/hr/editHrGroup/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}


	@Test
        @Disabled
	void testTopicListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/topics");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testProjectListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/projects");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
        @Disabled
	void testResearchersListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/researchers");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	@Disabled        
	void testAddTopicPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/addTopic");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testAddProjectPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/addProject");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
        @Disabled
        void testAddResearcherPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/addResearcher");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testEditTopicPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/editTopic/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testEditProjectPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/editProject/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
        @Disabled
	void testEditResearcherPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/research/editResearcher/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
        @Disabled
	void testAddArtistPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/videos/addArtist");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testAddVideoPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/videos/addVideo");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
	@Test
        @Disabled
	void testVideoListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/videos/videoList");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testReviewListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/reviews/reviewList");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testAddReviewPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/reviews/addReview");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testEditReviewPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/reviews/editReview/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testEventListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/events/eventList");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testEventAdvertisementListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/events/eventAdvertisementList");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testEventAdministratorListPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/events/eventAdministratorList");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testEditEventAdministratorPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/events/editEventAdministrator/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
	void testEditEventAdvertisementPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/events/editEventAdvertisement/1");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
	void testAddEventAdministratorPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/events/addEventAdministrator");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

	@Test
        @Disabled
        void testAddEventAdvertisementPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/events/addEventAdvertisement");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
}
