package com.fse.projmanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fse.projmanagement.model.Task;

public interface TaskControllerEndpoint {

	@PostMapping(path = "/add")
	public ResponseEntity<Task> addTask(@RequestBody Task task);

	@PutMapping(path = "/update")
	public ResponseEntity<Task> updateTask(@RequestBody Task task);

	@GetMapping(path = "/all")
	public ResponseEntity<List<Task>> getTasks();

	@PutMapping(path = "/complete")
	public ResponseEntity<String> completeTask(@RequestBody Task task);

}
