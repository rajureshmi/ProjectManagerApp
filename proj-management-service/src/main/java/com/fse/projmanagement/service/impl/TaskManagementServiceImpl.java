package com.fse.projmanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projmanagement.dao.ParentTaskDao;
import com.fse.projmanagement.dao.ProjectDao;
import com.fse.projmanagement.dao.TaskDao;
import com.fse.projmanagement.dao.UserDao;
import com.fse.projmanagement.dao.repo.ParentTaskRepository;
import com.fse.projmanagement.dao.repo.ProjectRepository;
import com.fse.projmanagement.dao.repo.TaskRepository;
import com.fse.projmanagement.dao.repo.UserRepository;
import com.fse.projmanagement.model.Project;
import com.fse.projmanagement.model.Task;
import com.fse.projmanagement.service.TaskManagementService;

@Service
public class TaskManagementServiceImpl implements TaskManagementService {

	private static final Logger logger = LoggerFactory.getLogger(TaskManagementServiceImpl.class);

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	ParentTaskRepository parentTaskRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Transactional
	@Override
	public Task addTask(Task newTask) {
		TaskDao taskDao = new TaskDao(newTask);
		taskDao.setActive(true);
		taskDao.setParentTask(getParentTaskForTask(newTask));
		taskDao.setUser(getUserForTask(newTask));
		taskDao.setProject(getProjectForTask(newTask));
		TaskDao addedTaskDao = taskRepository.saveAndFlush(taskDao);
		if (Objects.nonNull(addedTaskDao)) {
			logger.info("Task suceesfully added with ID {}" + addedTaskDao.getTaskId());
			newTask.setTaskId(addedTaskDao.getTaskId());
			return newTask;
		}
		return null;
	}

	private ProjectDao getProjectForTask(Task newTask) {
		return Objects.nonNull(newTask.getProjectId()) ? projectRepository.findByProjectId(newTask.getProjectId())
				: null;
	}

	private UserDao getUserForTask(Task newTask) {
		return Objects.nonNull(newTask.getUserId()) ? userRepository.findByUserId(newTask.getUserId()) : null;
	}

	private ParentTaskDao getParentTaskForTask(Task newTask) {
		return Objects.nonNull(newTask.getParentId()) ? parentTaskRepository.findByParentId(newTask.getParentId())
				: null;
	}

	@Override
	public Task updateTask(Task taskToBeUpdated) {
		TaskDao taskDao = taskRepository.findByTaskId(taskToBeUpdated.getTaskId());
		taskDao.setStartDate(taskToBeUpdated.getStartDate());
		taskDao.setEndDate(taskToBeUpdated.getEndDate());
		taskDao.setPriority(taskToBeUpdated.getPriority());
		taskDao.setParentTask(getParentTaskForTask(taskToBeUpdated));
		taskDao.setUser(getUserForTask(taskToBeUpdated));
		taskDao.setProject(getProjectForTask(taskToBeUpdated));

		TaskDao updatedTask = taskRepository.saveAndFlush(taskDao);
		if (Objects.nonNull(updatedTask)) {
			logger.info("Project updated successfully for Id: : " + updatedTask.getTaskId());
			return taskToBeUpdated;
		}
		return null;
	}

	@Override
	public List<Task> fetchTasks() {
		List<TaskDao> tasksList = taskRepository.findAll();
		if (Objects.nonNull(tasksList)) {
			logger.info("Projects retrieved: " + tasksList.size());
			return tasksList.stream().map(taskDao -> new Task(taskDao)).collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public void endTask(Task endTask) {
		TaskDao taskDao = taskRepository.findByTaskId(endTask.getTaskId());
		taskDao.setActive(false);
		taskRepository.saveAndFlush(taskDao);
		logger.info("Task inActivated successfully for Id: : " + taskDao.getTaskId());
	}
}
