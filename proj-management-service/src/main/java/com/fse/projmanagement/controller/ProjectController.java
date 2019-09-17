package com.fse.projmanagement.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.projmanagement.model.Project;
import com.fse.projmanagement.service.ProjectManagementService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/projectmanagement/api/project")
public class ProjectController implements ProjectControllerEndpoint {

	@Autowired
	private ProjectManagementService projectManagementService;

	@Override
	public ResponseEntity<List<Project>> fetchProjects() {
		List<Project> projects = projectManagementService.fetchProjects();
		if (Objects.nonNull(projects)) {
			return new ResponseEntity<>(projects, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Project> addProject(Project projToAdd) {
		if (Objects.nonNull(projToAdd)) {
			Project addedProj = projectManagementService.addProject(projToAdd);
			if (Objects.nonNull(addedProj)) {
				return new ResponseEntity<>(addedProj, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Project> updateProject(Project projToUpd) {
		if (Objects.nonNull(projToUpd)) {
			Project updatedProj = projectManagementService.updateProject(projToUpd);
			if (Objects.nonNull(updatedProj)) {
				return new ResponseEntity<>(updatedProj, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> suspendProject(Project projToUpd) {
		if (Objects.nonNull(projToUpd)) {
			projectManagementService.suspendProject(projToUpd);	
			return new ResponseEntity<>( HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
