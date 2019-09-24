package com.fse.projmanagement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fse.projmanagement.model.Project;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ProjManagementServiceApplication.class)
@ActiveProfiles("test")
public class ProjectControllerTest extends TestCase {

	@Value("${local.server.port}")
	private Integer port;
	private String baseUrl;
	private TestRestTemplate testRestTemplate;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		baseUrl = "http://localhost:".concat(port.toString()).concat("/projectmanagement/api/project");
		testRestTemplate = new TestRestTemplate();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		baseUrl = null;
		testRestTemplate = null;
	}

	@Test
	public void testFetchProjects() throws Exception {
		ResponseEntity<List<Project>> response = testRestTemplate.exchange(baseUrl.concat("/all"), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Project>>() {
				});

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertNotNull("Project list is null", response.getBody());
	}

	@Test
	public void testAddProject() throws Exception {
		Project project = new Project("Regulatory",new Date(2019,6,10),new Date(2019,12,30),15,3L,"Cindy","Melka",100102L);
		ResponseEntity<Project> response = testRestTemplate.postForEntity(baseUrl.concat("/add"), project,
				Project.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertNotNull("Project not added", response.getBody());
	}
	
	@Test
	public void testUpdateProject() throws Exception {
		ResponseEntity<List<Project>> resp = testRestTemplate.exchange(baseUrl.concat("/all"), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Project>>() {
				});
		List<Project> projs = resp.getBody();
		Project project = projs.get(1);

		ResponseEntity<Project> response = testRestTemplate.postForEntity(baseUrl.concat("/update"), project,
				Project.class);
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertNotNull("Project not updated", response.getBody());
	}
	
	@Test
	public void testSuspendProject() throws Exception {
		ResponseEntity<List<Project>> resp = testRestTemplate.exchange(baseUrl.concat("/all"), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Project>>() {
				});
		List<Project> projs = resp.getBody();
		Project project = projs.get(1);

		ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("/suspend"), project,
				String.class);
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		
	}

}