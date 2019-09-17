package com.fse.projmanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projmanagement.dao.UserDao;
import com.fse.projmanagement.dao.repo.UserRepository;
import com.fse.projmanagement.model.User;
import com.fse.projmanagement.service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	private static final Logger logger = LoggerFactory.getLogger(UserManagementServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> fetchUsers() {
		List<UserDao> usersList = userRepository.findAll();
		if (Objects.nonNull(usersList)) {
			logger.info("Users retrieved: " + usersList.size());
			return usersList.stream().filter(uDao -> uDao.isActive()).map(userDao -> new User(userDao))
					.collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public User addUser(User userToAdd) {
		UserDao userDao = new UserDao(userToAdd);
		userDao.setActive(true);
		UserDao addedUser = userRepository.saveAndFlush(userDao);
		if (Objects.nonNull(addedUser)) {
			logger.info("User added successfully. Id: : " + addedUser.getUserId());
			userToAdd.setUserId(addedUser.getUserId());
			return userToAdd;
		}
		return null;
	}

	@Transactional
	@Override
	public User updateUser(User userToUpd) {
		UserDao userDao = userRepository.findByUserId(userToUpd.getUserId());
		userDao.setFirstName(userToUpd.getFirstName());
		userDao.setLastName(userToUpd.getLastName());
		userDao.setEmployeeId(userToUpd.getEmployeeId());
		UserDao updatedUser = userRepository.saveAndFlush(userDao);
		if (Objects.nonNull(updatedUser)) {
			logger.info("User updated successfully for Id: : " + updatedUser.getUserId());
			return userToUpd;
		}
		return null;
	}

	@Override
	public void deleteUser(User userToDelete) {
		UserDao userDao = userRepository.findByUserId(userToDelete.getUserId());
		userDao.setActive(false);
		userRepository.saveAndFlush(userDao);
		logger.info("User inActivated successfully for Id: : " + userToDelete.getUserId());
	}

}
