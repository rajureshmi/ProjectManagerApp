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

import com.fse.projmanagement.model.Task;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ProjManagementServiceApplication.class)
@ActiveProfiles("test")
public class TaskControllerTest extends TestCase {

	@Value("${local.server.port}")
	private Integer port;
	private String baseUrl;
	private TestRestTemplate testRestTemplate;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		baseUrl = "http://localhost:".concat(port.toString()).concat("/projectmanagement/api/task");
		testRestTemplate = new TestRestTemplate();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		baseUrl = null;
		testRestTemplate = null;
	}

	@Test
	public void testFetchTasks() throws Exception {
		ResponseEntity<List<Task>> response = testRestTemplate.exchange(baseUrl.concat("/all"), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Task>>() {
				});

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertNotNull("Task list is null", response.getBody());		
	}

	@Test
	public void testAddTask() throws Exception {
		Task newTask = new Task("Task 6",new Date(2019,6,10),new Date(2019,8,22),16,
				true,1L,1L,"XYZ",null, null,"Cindy","Melka",100102L);
		ResponseEntity<Task> response = testRestTemplate.postForEntity(baseUrl.concat("/add"), newTask, Task.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertNotNull("Task not added", response.getBody());
	}
	
	@Test
	public void testUpdateTask() throws Exception {
		
		ResponseEntity<List<Task>> resp = testRestTemplate.exchange(baseUrl.concat("/all"), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Task>>() {
				});
		List<Task> taskList = resp.getBody();
		//change task name and priority
		Task updatedTask = taskList.get(1);
		updatedTask.setTaskName("Add test cases");
		updatedTask.setPriority(20);
		ResponseEntity<Task> response = testRestTemplate.postForEntity(baseUrl.concat("/update"), updatedTask, Task.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertNotNull("Task not updated", response.getBody());
	}
	
	@Test
	public void testCompleteTask() throws Exception {
		ResponseEntity<List<Task>> resp = testRestTemplate.exchange(baseUrl.concat("/all"), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Task>>() {
				});
		List<Task> taskList = resp.getBody();
		Task completedTask = taskList.get(1);
		ResponseEntity<Task> response = testRestTemplate.postForEntity(baseUrl.concat("/complete"), completedTask, Task.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		
	}


}