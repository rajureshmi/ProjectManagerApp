package com.fse.projmanagement.model;

import java.util.Date;
import java.util.Objects;

import com.fse.projmanagement.dao.ProjectDao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Project {

	private Long projectId;
	private String projectName;
	private Date startDate;
	private Date endDate;
	private int priority;
	private Long managerId;
	private String managerFirstName;
	private String managerLastName;
	private Long managerEmployeeId;
	private int tasksCount;
	private int completedTasksCount;

	public Project(ProjectDao projectDao) {
		super();
		this.projectId = projectDao.getProjectId();
		this.projectName = projectDao.getProjectName();
		this.startDate = projectDao.getStartDate();
		this.endDate = projectDao.getEndDate();
		this.priority = projectDao.getPriority();
		this.managerId = projectDao.getUser().getEmployeeId();
		this.managerFirstName = projectDao.getUser().getFirstName();
		this.managerLastName = projectDao.getUser().getLastName();
		this.managerEmployeeId = projectDao.getUser().getEmployeeId();
		this.tasksCount = (Objects.nonNull(projectDao.getTasks())) ? projectDao.getTasks().size() : 0;
		this.completedTasksCount = (Objects.nonNull(projectDao.getTasks()))
				? (int) projectDao.getTasks().stream().filter(task -> !task.isActive()).count()
				: 0;
	}

	public Project(String projectName, Date startDate, Date endDate, int priority, Long managerId,
			String managerFirstName, String managerLastName, Long managerEmployeeId) {
		super();
		this.projectName = projectName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.managerId = managerId;
		this.managerFirstName = managerFirstName;
		this.managerLastName = managerLastName;
		this.managerEmployeeId = managerEmployeeId;
	}

}
