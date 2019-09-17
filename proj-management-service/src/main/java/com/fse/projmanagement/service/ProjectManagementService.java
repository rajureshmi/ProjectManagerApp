package com.fse.projmanagement.service;

import java.util.List;

import com.fse.projmanagement.model.Project;

public interface ProjectManagementService {
	
	List<Project> fetchProjects();
	
	Project addProject(Project projToAdd);
	
	Project updateProject(Project projToUpd);
	
	void suspendProject(Project projToUpd);

}
