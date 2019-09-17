package com.fse.projmanagement.dao.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fse.projmanagement.dao.TaskDao;

@Repository
public interface TaskRepository extends JpaRepository<TaskDao, Long> {

	TaskDao findByTaskId(Long taskId);

	void deleteByTaskId(Long taskId);

	List<TaskDao> findByTaskName(String taskName);

	List<TaskDao> findByTaskIdIn(List<Long> TaskId);
	
	List<TaskDao> findByPriorityBetween(int priorityFrom, int priorityTo);
	
	@Query("SELECT u FROM TaskDao u WHERE u.parentTask = :parentTaskId")
	List<TaskDao> findByParentTaskId( @Param("parentTaskId") Long parentTaskId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
