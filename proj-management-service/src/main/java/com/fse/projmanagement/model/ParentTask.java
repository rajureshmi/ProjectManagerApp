package com.fse.projmanagement.model;

import com.fse.projmanagement.dao.ParentTaskDao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParentTask {

	private Long parentTaskId;
	private String parentTaskName;
	
	public ParentTask(ParentTaskDao parentTaskDao) {
		super();
		this.parentTaskId = parentTaskDao.getParentId();
		this.parentTaskName = parentTaskDao.getParentTaskName();
	}

	
}
