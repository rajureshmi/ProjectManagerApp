package com.fse.projmanagement.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.projmanagement.model.User;
import com.fse.projmanagement.service.UserManagementService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/projectmanagement/api/user")
public class UserController implements UserControllerEndpoint {

	@Autowired
	private UserManagementService userManagementService;

	@Override
	public ResponseEntity<List<User>> fetchUsers() {
		List<User> projects = userManagementService.fetchUsers();
		if (Objects.nonNull(projects)) {
			return new ResponseEntity<>(projects, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<User> addUser(User projToAdd) {
		if (Objects.nonNull(projToAdd)) {
			User addedProj = userManagementService.addUser(projToAdd);
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
	public ResponseEntity<User> updateUser(User projToUpd) {
		if (Objects.nonNull(projToUpd)) {
			User updatedProj = userManagementService.updateUser(projToUpd);
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
	public ResponseEntity<String> suspendUser(User projToUpd) {
		if (Objects.nonNull(projToUpd)) {
			userManagementService.deleteUser(projToUpd);	
			return new ResponseEntity<>( HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
