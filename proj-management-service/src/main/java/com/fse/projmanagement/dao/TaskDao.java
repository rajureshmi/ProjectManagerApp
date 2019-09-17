package com.fse.projmanagement.dao;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fse.projmanagement.model.Task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Task")
public class TaskDao {

	@Id
	@Column(name = "Task_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;

	@Column(name = "Task")
	private String taskName;

	@Column(name = "Start_Date")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "End_Date")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name = "Priority")
	private int priority;

	@Column(name = "Status")
	private boolean isActive;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Parent_ID")
	private ParentTaskDao parentTask;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Project_ID")
	private ProjectDao project;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "User_ID")
	private UserDao user;

	public TaskDao(Task task) {
		super();
		this.taskName = task.getTaskName();
		this.startDate = task.getStartDate();
		this.endDate = task.getEndDate();
		this.priority = task.getPriority();
	}

}
