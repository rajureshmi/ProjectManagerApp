package com.fse.projmanagement.service;

import java.util.List;

import com.fse.projmanagement.model.ParentTask;

public interface ParentTaskManagementService {
	
	List<ParentTask> fetchParentTasks();
	
	ParentTask addParentTask(ParentTask parentTaskToAdd);

}
