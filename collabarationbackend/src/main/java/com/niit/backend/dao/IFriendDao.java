package com.niit.backend.dao;

import java.util.List;

import com.niit.backend.model.Friend;

public interface IFriendDao {
	void addFriend(Friend friend);
	void updateFriend(Friend friend);
	void deleteFriend(Friend friend);
	List<Friend> viewFriends(String name);

}
