package com.fse.projmanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fse.projmanagement.model.ParentTask;

public interface ParentTaskControllerEndpoint {

	@GetMapping(path = "/all")
	public ResponseEntity<List<ParentTask>> fetchParentTasks();

	@PostMapping(path = "/add")
	public ResponseEntity<ParentTask> addParentTask(@RequestBody ParentTask parentTask);

}
