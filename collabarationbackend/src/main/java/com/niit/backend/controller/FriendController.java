package com.niit.backend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.backend.dao.IFriendDao;
import com.niit.backend.model.Friend;

@RestController
public class FriendController {

	@Autowired
	IFriendDao friendDao;
	
	@Autowired
	Friend friend;
	
	@RequestMapping(value="/addFriend",headers="accept=Application/json",method=RequestMethod.POST)
	public void addFriend(@RequestBody Friend friend)
	{
		friendDao.addFriend(friend);
	}
	
	@RequestMapping(value="/viewFriends/{name}",headers="accept=Application/json",method=RequestMethod.GET)
	public List<Friend> viewFriends(@PathVariable("name")String name)
	{
		return friendDao.viewFriends(name);
	}
	
	@RequestMapping(value="/deleteFriend",headers="accept=Application/json",method=RequestMethod.POST)
	public void deleteFriend(@RequestBody Friend friend)
	{
		friendDao.deleteFriend(friend);
	}
	
	@RequestMapping(value="/updateFriend",headers="accept=Application/json",method=RequestMethod.PUT)
	public void updateFriend(@RequestBody Friend friend)
	{
		friendDao.updateFriend(friend);
	}
	
	@RequestMapping(value="/rejectFriend/{id}",method=RequestMethod.GET)
	public ResponseEntity<Friend> rejectFriend(@PathVariable("id")int id,HttpSession session){
		updateFriend(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptFriend/{id}",method=RequestMethod.GET)
	public ResponseEntity<Friend> acceptFriend(@PathVariable("id")int id,HttpSession session){
		updateFriend(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getMyFriendRequests",method=RequestMethod.GET)
	public ResponseEntity<Friend> getMyFriendRequests(HttpSession session){
		
		int uid=(Integer) session.getAttribute("userid");
		String uname=(String) session.getAttribute("username");
		friendDao.getMyNewFriendRequest(uname);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
}
