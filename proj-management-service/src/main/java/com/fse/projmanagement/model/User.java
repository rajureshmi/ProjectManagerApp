package com.fse.projmanagement.model;

import com.fse.projmanagement.dao.UserDao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

	private Long userId;
	private String firstName;
	private String lastName;
	private Long employeeId;
	private boolean isActive;
	
	public User(UserDao userDao) {
		super();
		this.userId = userDao.getUserId();
		this.firstName = userDao.getFirstName();
		this.lastName = userDao.getLastName();
		this.employeeId = userDao.getEmployeeId();
		this.isActive = userDao.isActive();
	}

	
}
