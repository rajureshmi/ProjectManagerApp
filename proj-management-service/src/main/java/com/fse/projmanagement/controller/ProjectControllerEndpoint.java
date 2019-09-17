package com.fse.projmanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fse.projmanagement.model.Project;

public interface ProjectControllerEndpoint {

	@GetMapping(path = "/all")
	public ResponseEntity<List<Project>> fetchProjects();

	@PostMapping(path = "/add")
	public ResponseEntity<Project> addProject(@RequestBody Project projToAdd);

	@PostMapping(path = "/update")
	public ResponseEntity<Project> updateProject(@RequestBody Project projToUpd);

	@PostMapping(path = "/suspend")
	public ResponseEntity<String> suspendProject(@RequestBody Project projToUpd);

}
