package com.it_academyproject.domains;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.it_academyproject.exceptions.EmptyFieldException;
import com.it_academyproject.tools.View;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "subclass", discriminatorType = DiscriminatorType.STRING)
@Table(name="users")
public abstract class MyAppUser {

	// Order according to former implementation
	// This order helps SQL data import (IT role=0, Student=1...)
	public enum Role {
		IT, STUDENT, TEACHER, ADMIN
	}

	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@JsonView(View.Summary.class)
	private String id;
	
	@JsonView(View.Summary.class)
	private String firstName;
	
	@JsonView(View.Summary.class)
	private String lastName;

	@JsonView(View.SummaryWithOthers.class)
	private String email;
	
	@JsonView(View.SummaryWithOthers.class)
	private char gender;

	@JsonView(View.SummaryWithOthers.class)
	private int age;
	
	@JsonView(View.SummaryWithOthers.class)
	private String portrait;

	private String password;
	private boolean enabled;
	private Date lastLogin;

	private Role role;
	
	@OneToMany (targetEntity = Course.class, cascade = CascadeType.ALL)
	@JsonView(View.Summary.class)
	private List <Course> courses = new ArrayList <Course>();
	@OneToMany (targetEntity = UserExercise.class, cascade = CascadeType.ALL)
	private List <UserExercise> userExercises = new ArrayList <UserExercise>();
	@OneToMany (targetEntity = Emails.class, cascade = CascadeType.ALL)
	private List <Emails> emails = new ArrayList <Emails>();
	
	public MyAppUser() {
	}
	
	public MyAppUser(String firstName, String lastName, String email, char gender,
					 String portrait, String password, boolean enabled, Role role) {

		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.portrait = portrait;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}
	
 	public MyAppUser(String email, String password) throws EmptyFieldException
	    {
	        if ((email != "")&&(password!=""))
	        {
	            this.email = email;
	            this.password = password;
	            this.lastLogin = new Date();
	            this.enabled = true;
	        }
	        else if (email == "")
	        {
	            throw (new EmptyFieldException("email"));
	        }
	        else if ( password == "" )
	        {
	            throw (new EmptyFieldException("password"));
	        }
	    }

	public String getId() {
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id.toString();
	}
	public void setId()
	{
		UUID uuid = UUID.randomUUID();
		this.id = uuid.toString();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/*	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + ", portrait=" + portrait + ", password=" + password
				+ ", enabled=" + enabled + "]";
	}	*/

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}