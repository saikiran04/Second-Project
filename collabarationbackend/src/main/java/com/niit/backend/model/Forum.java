package com.niit.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
@Entity
@Table(name="FORUM")
@Component
public class Forum {
	
	@Id
	@GeneratedValue(generator="InvSeq")
	@SequenceGenerator(name="InvSeq",sequenceName="FORUM_SEQ", allocationSize=1)
	private int forum_id;
	@NotEmpty(message="Please Fill the blog title")
	private String forum_title;
	private String creation_date;
	private String status;
	@NotEmpty(message="Please fill the description")
	private String description;
	private String user_name;
	
	private String category;

	public int getForum_id() {
		return forum_id;
	}

	public void setForum_id(int forum_id) {
		this.forum_id = forum_id;
	}

	public String getForum_title() {
		return forum_title;
	}

	public void setForum_title(String forum_title) {
		this.forum_title = forum_title;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
}
