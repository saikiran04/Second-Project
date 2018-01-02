package com.niit.backend.controller;

import java.util.ArrayList;
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
import com.niit.backend.dao.IUserDao;
import com.niit.backend.model.Friend;
import com.niit.backend.model.User;

@RestController
public class FriendController {

	@Autowired
	IFriendDao friendDao;
	
	@Autowired
	Friend friend;
	
	@Autowired
	IUserDao userDao;
	
	@Autowired
	User user;
	
	@Autowired
	HttpSession session;
	
	
	/*@RequestMapping(value="/addFriend",headers="accept=Application/json",method=RequestMethod.POST)
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
*/	
	@RequestMapping(value="/myFriends",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getAllMyFriends(){
		System.out.println("calling method list all friends in friend controller");
		String loggedInUsername=(String) session.getAttribute("username");
		
		List<Friend> myFriends=new ArrayList<Friend>();
		if(loggedInUsername==null) {
			myFriends.add(friend);
			return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
		}
		
		System.out.println("getting friends of:"+loggedInUsername);
		myFriends=friendDao.viewFriends(loggedInUsername);
		if(myFriends.isEmpty())
		{
			System.out.println("friends does not exists for the user"+loggedInUsername);
			friend.setErrorCode("404");
			friend.setErrorMessage("you do not have friends:"+loggedInUsername);
			myFriends.add(friend);
		}
		System.out.println("send the friend list");
		return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
	}
	
	@RequestMapping(value="/addFriend/{friendId}",method=RequestMethod.POST)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendId") String friend_id){
		System.out.println("Friend Id:"+friend_id);
		int fid=Integer.parseInt(friend_id);
		String name=(String)session.getAttribute("username");
		System.out.println("username"+name);
		
		int uid=((Integer)session.getAttribute("userid")).intValue();
		System.out.println("user id"+uid);
		friend.setUser_id(uid);
		friend.setFriend_id(fid);
		friend.setIsOnline("N");
		friend.setRequest_status('N');
		friend.setName(name);
		
		user=userDao.get(fid);
		System.out.println("Friend name is:"+user.getUser_name());
		friend.setFriendname(user.getUser_name());
		
		friendDao.addFriend(friend);
		System.out.println("friend request succesfully sent.."+friend_id);
		
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/friend/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Friend> updateFriend(@PathVariable("id") String Friend_id, @RequestBody Friend friend){
		System.out.println("calling method updateFriend:"+friend.getFriend_id());
		
		if(friendDao.viewFriends(Friend_id)==null) {
			System.out.println("friend does not exists with id:"+friend.getFriend_id());
			friend=new Friend();
			return new ResponseEntity<Friend>(friend,HttpStatus.NOT_FOUND);
		}
		friendDao.updateFriend(friend);
		System.out.println("friend updated succesfully...in friend Controller");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);

	}
	@RequestMapping(value="unFriend/{friendId}",method=RequestMethod.GET)
	public ResponseEntity<Friend> unFriend(@PathVariable("friendId") String friend_id){
		int fid=Integer.parseInt(friend_id);
		System.out.println("calling method unfriend");
		updateRequest(fid,"U");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	@RequestMapping(value="rejectFriend/{friendId}",method=RequestMethod.GET)
	public ResponseEntity<Friend> rejectFriend(@PathVariable("friendId") String friend_id){
		int fid=Integer.parseInt(friend_id);
		System.out.println("calling method unfriend");
		updateRequest(fid,"R");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptFriend/{friendId}",method=RequestMethod.GET)
	public ResponseEntity<Friend> acceptFriend(@PathVariable("friendId") String friend_id){
		int fid=Integer.parseInt(friend_id);
		System.out.println("calling method unfriend");
		updateRequest(fid,"A");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getMyfriendRequests/",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriendRequest(){
		System.out.println("calling method getMyFriendRequest");
		String loggedInUsername= (String)session.getAttribute("username");
		
		List<Friend> myfriendreq=friendDao.getMyNewFriendRequest(loggedInUsername);
		
		return new ResponseEntity<List<Friend>>(myfriendreq,HttpStatus.OK);
	}
	
private Friend updateRequest(int friend_id,String status) {
	System.out.println("common method update request in friend Controller");
	int uid=((Long)session.getAttribute("userid")).intValue();
	/*if((status=="N")||(status=="R"))
		friend=friendDao.get(friend_id,uid);
		else
			friend=friendDao.get(uid,friend_id);*/
		
	friend.setIsOnline(status);
	
	friendDao.updateFriend(friend);
	friend.setErrorCode("200");
	friend.setErrorMessage("Req from "+friend.getUser_id()+"to"+friend.getFriend_id()+"has updated to"+status);
	System.out.println("loggedInUserId::"+uid+"friend id::"+friend_id+"status::"+status);
	return friend;
	
}

	
	
}
