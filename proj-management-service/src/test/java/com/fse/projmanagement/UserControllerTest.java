package com.fse.projmanagement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

import com.fse.projmanagement.model.User;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ProjManagementServiceApplication.class)
@ActiveProfiles("test")
public class UserControllerTest extends TestCase {

	@Value("${local.server.port}")
	private Integer port;
	private String baseUrl;
	private TestRestTemplate testRestTemplate;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		baseUrl = "http://localhost:".concat(port.toString()).concat("/projectmanagement/api/user");
		testRestTemplate = new TestRestTemplate();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		baseUrl = null;
		testRestTemplate = null;
	}

	@Test
	public void testFetchUsers() throws Exception {
		ResponseEntity<List<User>> response = testRestTemplate.exchange(baseUrl.concat("/all"), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertNotNull("User list is null", response.getBody());
	}

	@Test
	public void testAddUser() throws Exception {
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("Testco");
		user.setEmployeeId(100110L);
		user.setActive(true);
		ResponseEntity<User> response = testRestTemplate.postForEntity(baseUrl.concat("/add"), user, User.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertNotNull("User not added", response.getBody());
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		User user = new User();
		user.setUserId(6L);
		user.setFirstName("Testing");
		user.setLastName("Testcoo");
		user.setEmployeeId(100110L);
		user.setActive(true);
		ResponseEntity<User> response = testRestTemplate.postForEntity(baseUrl.concat("/update"), user, User.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertNotNull("User not added", response.getBody());
	}

}