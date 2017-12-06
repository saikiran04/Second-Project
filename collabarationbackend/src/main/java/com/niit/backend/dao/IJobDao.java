package com.niit.backend.dao;

import java.util.List;


import com.niit.backend.model.Job;

public interface IJobDao {
	
	public void save(Job job);
	
	public void update(Job job);
	
	public void delete(Job job);
	
	public Job get(int id);
	
	public List<Job> getAllJobs();

}
