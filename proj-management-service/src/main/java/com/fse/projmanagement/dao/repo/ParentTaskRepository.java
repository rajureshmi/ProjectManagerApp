package com.fse.projmanagement.dao.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fse.projmanagement.dao.ParentTaskDao;

@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTaskDao, Long> {
	
	ParentTaskDao findByParentTaskName(String parentTaskName);
	
	ParentTaskDao findByParentId(Long parentId);
	
	List<ParentTaskDao> findByParentIdIn(List<Long> parentId);

}
