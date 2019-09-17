package com.fse.projmanagement.service;

import java.util.List;

import com.fse.projmanagement.model.User;

public interface UserManagementService {
	
	List<User> fetchUsers();
	
	User addUser(User userToAdd);
	
	User updateUser(User userToUpd);
	
	void deleteUser(User userToUpd);

}
