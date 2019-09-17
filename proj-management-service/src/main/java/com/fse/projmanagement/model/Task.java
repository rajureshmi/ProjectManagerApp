package com.fse.projmanagement.model;

import java.util.Date;

import com.fse.projmanagement.dao.ParentTaskDao;
import com.fse.projmanagement.dao.ProjectDao;
import com.fse.projmanagement.dao.TaskDao;
import com.fse.projmanagement.dao.UserDao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Task {

	private Long taskId;
	private String taskName;
	private Date startDate;
	private Date endDate;
	private int priority;
	private boolean status;
	private Long userId;
	private Long projectId;
	private String project;
	private Long parentId;
	private String parentTaskName;
	private String userFirstName;
	private String userLastName;
	private Long userEmployeeId;

	public Task(TaskDao taskDao) {
		super();
		this.taskId = taskDao.getTaskId();
		this.taskName = taskDao.getTaskName();
		this.startDate = taskDao.getStartDate();
		this.endDate = taskDao.getEndDate();
		this.priority = taskDao.getPriority();
		this.status = taskDao.isActive();
		UserDao user = taskDao.getUser();
		this.userId = user.getUserId();
		this.userFirstName = user.getFirstName();
		this.userLastName = user.getLastName();
		this.userEmployeeId = user.getEmployeeId();
		ProjectDao project = taskDao.getProject();
		this.projectId = project.getProjectId();
		this.project = project.getProjectName();
		ParentTaskDao parentTask = taskDao.getParentTask();
		this.parentId = parentTask.getParentId();
		this.parentTaskName = parentTask.getParentTaskName();

	}

}
