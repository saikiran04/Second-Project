package com.niit.backend.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.niit.backend.dao.IUserDao;
import com.niit.backend.model.BaseDomain;
import com.niit.backend.model.User;


@RestController
public class UserController {
	
	private static final Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	IUserDao userDao;
	
	@RequestMapping(value="users",method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers(){
		logger.debug("Calling method list all users");
		List<User> user=userDao.list();
		if(user.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(user,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/registeruser",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user){
		try {
			System.out.println("I am here");
			
		
			user.setIsOnline('f');
			MultipartFile image=user.getImg();
			Path path;
			path=Paths.get("C:\\Users\\Asaikiran\\git1\\CollabarationFrontend\\WebContent\\img\\" +user.getUser_name() + ".jpg");
			System.out.println("Path=" + path);
			System.out.println("File name=" + user.getImg().getOriginalFilename());
			if(image !=null&& !image.isEmpty()) {
				try {
					image.transferTo(new File(path.toString()));
					System.out.println("Image saved in: " +path.toString());
					
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("Image not saved");
				}
			}
			userDao.save(user);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		
		}catch(Exception e){
			BaseDomain error=new BaseDomain();
			return new ResponseEntity<BaseDomain>(error,HttpStatus.INTERNAL_SERVER_ERROR);
					
		}
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> isValidUser(@RequestBody User user,HttpSession session){
		User validuser=userDao.isValidUser(user);
		System.out.println("in login method");
		if(validuser==null) {
			BaseDomain error = new BaseDomain();
			return new ResponseEntity<BaseDomain>(error,HttpStatus.UNAUTHORIZED);
		}
		validuser.setIsOnline('y');
		userDao.update(validuser);
		session.setAttribute("username",validuser.getUser_name());
		session.setAttribute("userid", validuser.getUser_id());

		return new ResponseEntity<User>(validuser,HttpStatus.OK);
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session){
		if(session.getAttribute("username")==null) {
			BaseDomain error=new BaseDomain();
			return new ResponseEntity<BaseDomain>(error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		Integer uid=(Integer)session.getAttribute("userid");
		User user=userDao.get(uid);
		user.setIsOnline('n');
		userDao.update(user);
		session.removeAttribute("username");
		session.removeAttribute("userid");
		session.invalidate();
		 
		
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") int user_id){
		logger.debug("calling method deleteUser for user id: "+user_id);
		User user=userDao.get(user_id);
		
		if(user==null) {
			logger.debug("user does not exist with id:" +user_id);
			user=new User();
			user.setErrorMessage("user does not exists with id:" +user_id);
			return new ResponseEntity<User> (user,HttpStatus.NOT_FOUND);
			}else {
				userDao.delete(user);
				logger.debug("user deleted succesfully");
				return new ResponseEntity<User> (user,HttpStatus.OK);
			}
	}
   /* @RequestMapping(value="/updateUser{id}",method=RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") int user_id,@RequestBody User user){
    	logger.debug("calling method updateUser"+user.getUser_id());
    	if(userDao.get(user_id)==null) {
    		logger.debug("User does not exist with id:"+user.getUser_id());
    		user=new User();
    		user.setErrorMessage("User does not Exist with id:"+user.getUser_id());
    		return new ResponseEntity<User> (user,HttpStatus.NOT_FOUND);
    			}else {
    				userDao.update(user);
    				logger.debug("user updated sucessfully");
    				return new ResponseEntity<User> (user,HttpStatus.OK);
    			}
    }*/
    
    @RequestMapping(value="/updateuser",method=RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody User user,HttpSession session){
    	
    	if(session.getAttribute("username")==null) {
    		BaseDomain error=new BaseDomain();
    		return new ResponseEntity<BaseDomain>(error,HttpStatus.UNAUTHORIZED);
    	}
    	try {
    		userDao.update(user);
    		System.out.println("update method");
    		return new ResponseEntity<Void>(HttpStatus.OK);
    	}catch(Exception e) {
    		BaseDomain error=new BaseDomain();
    		return new ResponseEntity<BaseDomain>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
