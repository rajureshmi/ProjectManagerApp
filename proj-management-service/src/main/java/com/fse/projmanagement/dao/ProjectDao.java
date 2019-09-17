package com.fse.projmanagement.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fse.projmanagement.model.Project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Project")
public class ProjectDao {

	 @Id
	    @Column(name = "Project_ID")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long projectId;

	    @Column(name = "Project")
	    private String projectName;

	    @Column(name = "start_date")
	    @Temporal(TemporalType.DATE)
	    private Date startDate;

	    @Column(name = "end_date")
	    @Temporal(TemporalType.DATE)
	    private Date endDate;

	    @Column(name = "Priority")
	    private int priority;

	    @ManyToOne(cascade = CascadeType.MERGE)
	    @JoinColumn(name = "User_ID")
	    private UserDao user;

	    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinColumn(name = "Project_ID")
	    private List<TaskDao> tasks;

		public ProjectDao(Project project) {
			super();
			this.projectName = project.getProjectName();
			this.startDate = project.getStartDate();
			this.endDate = project.getEndDate();
			this.priority = project.getPriority();
			UserDao user = new UserDao(project.getManagerId(),project.getManagerFirstName(),
					project.getManagerLastName(),project.getManagerEmployeeId(),true);
			this.user = user;			
		}
	
	    
}
