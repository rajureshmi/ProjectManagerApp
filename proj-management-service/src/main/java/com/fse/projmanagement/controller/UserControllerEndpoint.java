package com.fse.projmanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fse.projmanagement.model.User;

public interface UserControllerEndpoint {

	@GetMapping(path = "/all")
	public ResponseEntity<List<User>> fetchUsers();

	@PostMapping(path = "/add")
	public ResponseEntity<User> addUser(@RequestBody User projToAdd);

	@PostMapping(path = "/update")
	public ResponseEntity<User> updateUser(@RequestBody User projToUpd);

	@PostMapping(path = "/delete")
	public ResponseEntity<String> suspendUser(@RequestBody User projToUpd);

}
