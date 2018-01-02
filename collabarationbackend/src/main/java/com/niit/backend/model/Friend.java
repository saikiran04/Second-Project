package com.niit.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Friend extends BaseDomain {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String friendname;
	private String isOnline;
	private char request_status;
	private int friend_id;
	private int user_id;
	
	public int getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFriendname() {
		return friendname;
	}
	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	public char getRequest_status() {
		return request_status;
	}
	public void setRequest_status(char request_status) {
		this.request_status = request_status;
	}
	

	
}
