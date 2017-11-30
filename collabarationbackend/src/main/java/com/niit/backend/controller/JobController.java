package com.niit.backend.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.backend.dao.IJobDao;
import com.niit.backend.dao.IUserDao;
import com.niit.backend.model.BaseDomain;
import com.niit.backend.model.Job;
import com.niit.backend.model.User;



@RestController
public class JobController {
	
private static final Logger logger=LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	IJobDao jobDao;
	
	@Autowired
	Job job;
	
	@Autowired
	IUserDao userDao;
	
	@RequestMapping(value="/saveJob",method=RequestMethod.GET)
	public ResponseEntity<?> save(@RequestBody Job job,HttpSession session){
		if(session.getAttribute("username")==null) {
			BaseDomain error=new BaseDomain();
			return new ResponseEntity<BaseDomain>(error,HttpStatus.UNAUTHORIZED);
		}
		Integer uid=(Integer)session.getAttribute("userid");
		User user=userDao.get(uid);
		if(user.getRole().equals("ADMIN")) {
			try {
				job.setCreation_date(new Date());
				jobDao.save(job);
				return new ResponseEntity<Job>(job,HttpStatus.OK);
			}catch(Exception e) {
				BaseDomain error=new BaseDomain();
				return new ResponseEntity<BaseDomain>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			BaseDomain error=new BaseDomain();
			return new ResponseEntity<BaseDomain>(error,HttpStatus.UNAUTHORIZED);
		}
	}
	
	/*@RequestMapping(value="/getAllJobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session){
		
	}
	*/

}
