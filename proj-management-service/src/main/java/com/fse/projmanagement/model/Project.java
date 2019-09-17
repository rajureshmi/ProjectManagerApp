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
	private int numberOfTask;
	private int numberOfCompletedTask;

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
		this.numberOfTask = (Objects.nonNull(projectDao.getTasks())) ? projectDao.getTasks().size() : 0;
		this.numberOfCompletedTask = (Objects.nonNull(projectDao.getTasks()))
				? (int) projectDao.getTasks().stream().filter(task -> !task.isActive()).count()
				: 0;
	}

}
