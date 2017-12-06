package com.niit.backend.controller;

import java.util.Date;
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
	
	@RequestMapping(value="/saveJob",method=RequestMethod.POST)
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
		}

			
		
		else {
			BaseDomain error=new BaseDomain();
			return new ResponseEntity<BaseDomain>(error,HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/getalljobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session){
		if(session.getAttribute("username")==null){
			BaseDomain error=new BaseDomain();
			return new ResponseEntity<BaseDomain>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Job> jobs=jobDao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	@RequestMapping(value="/getjobbyid/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getJobById(@PathVariable int id,HttpSession session){
		if(session.getAttribute("username")==null){
			BaseDomain error=new BaseDomain();
			return new ResponseEntity<BaseDomain>(error,HttpStatus.UNAUTHORIZED);
		}
		Job job=jobDao.get(id);
		return new ResponseEntity<Job>(job,HttpStatus.OK);
		
	}
}
