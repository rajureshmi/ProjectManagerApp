package com.fse.projmanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projmanagement.dao.ProjectDao;
import com.fse.projmanagement.dao.UserDao;
import com.fse.projmanagement.dao.repo.ProjectRepository;
import com.fse.projmanagement.model.Project;
import com.fse.projmanagement.service.ProjectManagementService;

@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {

	private static final Logger logger = LoggerFactory.getLogger(ProjectManagementServiceImpl.class);

	@Autowired
	ProjectRepository projectRepository;

	@Override
	public List<Project> fetchProjects() {
		List<ProjectDao> projectsList = projectRepository.findAll();
		if (Objects.nonNull(projectsList)) {
			logger.info("Projects retrieved: " + projectsList.size());
			return projectsList.stream().map(projDao -> new Project(projDao)).collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public Project addProject(Project projToAdd) {
		ProjectDao projDao = new ProjectDao(projToAdd);
		ProjectDao addedProject = projectRepository.saveAndFlush(projDao);
		if (Objects.nonNull(addedProject)) {
			logger.info("Project added successfully. Id: : " + addedProject.getProjectId());
			projToAdd.setProjectId(addedProject.getProjectId());
			return projToAdd;
		}
		return null;
	}

	@Transactional
	@Override
	public Project updateProject(Project projToUpd) {
		ProjectDao projDao = projectRepository.findByProjectId(projToUpd.getProjectId());
		projDao.setStartDate(projToUpd.getStartDate());
		projDao.setEndDate(projToUpd.getEndDate());
		projDao.setPriority(projToUpd.getPriority());
		UserDao user = new UserDao(projToUpd.getManagerId(), projToUpd.getManagerFirstName(),
				projToUpd.getManagerLastName(), projToUpd.getManagerEmployeeId(), true);
		projDao.setUser(user);
		ProjectDao updatedProject = projectRepository.saveAndFlush(projDao);
		if (Objects.nonNull(updatedProject)) {
			logger.info("Project updated successfully for Id: : " + updatedProject.getProjectId());
			return projToUpd;
		}
		return null;
	}

	@Override
	public void suspendProject(Project projToUpd) {
		ProjectDao projDao = projectRepository.findByProjectId(projToUpd.getProjectId());
		projectRepository.delete(projDao);
		logger.info("Project deleted successfully for Id: : " + projToUpd.getProjectId());
	}

}
