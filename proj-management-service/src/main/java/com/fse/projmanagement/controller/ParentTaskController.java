package com.fse.projmanagement.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.projmanagement.model.ParentTask;
import com.fse.projmanagement.service.ParentTaskManagementService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/projectmanagement/api/parenttask")
public class ParentTaskController implements ParentTaskControllerEndpoint {

	@Autowired
	private ParentTaskManagementService parentTaskManagementService;

	@Override
	public ResponseEntity<List<ParentTask>> fetchParentTasks() {
		List<ParentTask> projects = parentTaskManagementService.fetchParentTasks();
		if (Objects.nonNull(projects)) {
			return new ResponseEntity<>(projects, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ParentTask> addParentTask(ParentTask parentTaskToAdd) {
		if (Objects.nonNull(parentTaskToAdd)) {
			ParentTask addedParentTask = parentTaskManagementService.addParentTask(parentTaskToAdd);
			if (Objects.nonNull(addedParentTask)) {
				return new ResponseEntity<>(addedParentTask, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
