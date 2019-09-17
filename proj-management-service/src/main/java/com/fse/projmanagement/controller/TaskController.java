package com.fse.projmanagement.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.projmanagement.model.Task;
import com.fse.projmanagement.service.TaskManagementService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/projectmanagement/api/task")
public class TaskController implements TaskControllerEndpoint {
	
	@Autowired
	private TaskManagementService taskManagementService;

	@Override
	public ResponseEntity<Object> addTask(Task taskToBeAdded) {
		if (Objects.nonNull(taskToBeAdded)) {
			Task addedTask = taskManagementService.addTask(taskToBeAdded);
			if (Objects.nonNull(addedTask)) {
				return new ResponseEntity<>(addedTask, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> updateTask(Task taskToUpdate) {
		if (Objects.nonNull(taskToUpdate)) {
			Task updatedTask = taskManagementService.updateTask(taskToUpdate);
			if (Objects.nonNull(updatedTask)) {
				return new ResponseEntity<>(updatedTask, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Task>> getTasks() {
		List<Task> tasks = taskManagementService.fetchTasks();
		if (Objects.nonNull(tasks)) {
			return new ResponseEntity<>(tasks, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Object> completeTask(Task taskToComplete) {
		if (Objects.nonNull(taskToComplete)) {
			taskManagementService.endTask(taskToComplete);	
			return new ResponseEntity<>( HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		
		
		
		
		
	}

}
