package com.fse.projmanagement.model;

import java.util.Date;
import java.util.Objects;

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
	
	private Long projectId;
	private String projectName;
	private Long parentId;
	private String parentTaskName;
	private Long userId;
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
		if (Objects.nonNull(taskDao.getProject())) {
			ProjectDao project = taskDao.getProject();
			this.projectId = project.getProjectId();
			this.projectName = project.getProjectName();
		}
		if (Objects.nonNull(taskDao.getParentTask())) {
			ParentTaskDao parentTask = taskDao.getParentTask();
			this.parentId = parentTask.getParentId();
			this.parentTaskName = parentTask.getParentTaskName();
		}

	}

	public Task(String taskName, Date startDate, Date endDate, int priority, boolean status, Long userId,
			Long projectId, String project, Long parentId, String parentTaskName, String userFirstName,
			String userLastName, Long userEmployeeId) {
		super();
		this.taskName = taskName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.status = status;
		this.userId = userId;
		this.projectId = projectId;
		this.projectName = project;
		this.parentId = parentId;
		this.parentTaskName = parentTaskName;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmployeeId = userEmployeeId;
	}

}
