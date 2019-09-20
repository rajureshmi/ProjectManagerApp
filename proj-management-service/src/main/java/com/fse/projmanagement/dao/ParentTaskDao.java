package com.fse.projmanagement.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fse.projmanagement.model.ParentTask;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "parent_task")
public class ParentTaskDao {

	@Id
	@Column(name = "Parent_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long parentId;

	@Column(name = "Parent_Task")
	private String parentTaskName;

	public ParentTaskDao(ParentTask parentTask) {
		super();
		this.parentId = parentTask.getParentTaskId();
		this.parentTaskName = parentTask.getParentTaskName();
	}
	
	

}
