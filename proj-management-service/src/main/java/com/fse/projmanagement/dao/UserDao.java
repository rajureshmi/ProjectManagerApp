package com.fse.projmanagement.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fse.projmanagement.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class UserDao {

	@Id
	@Column(name = "User_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "First_Name")
	private String firstName;

	@Column(name = "Last_Name")
	private String lastName;

	@Column(name = "Employee_ID")
	private Long employeeId;

	@Column(name = "Active")
	private boolean active;

	public UserDao(User user) {
		super();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.employeeId = user.getEmployeeId();
	}

}
