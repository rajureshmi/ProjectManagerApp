package com.fse.projmanagement.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fse.projmanagement.dao.ProjectDao;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectDao, Long> {

	ProjectDao findByProjectId(Long projectId);

}
