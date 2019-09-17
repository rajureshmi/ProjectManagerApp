package com.fse.projmanagement.service;

import java.util.List;

import com.fse.projmanagement.model.Task;


public interface TaskManagementService {

	Task addTask(Task newTask);

	Task updateTask(Task taskToBeUpdated);

	List<Task> fetchTasks();

	void endTask(Task endTask);
}
