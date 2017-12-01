package com.niit.backend.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niit.backend.model.Job;

public class JobDaoImpl implements IJobDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public JobDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	@Transactional
	public void save(Job job) {
		sessionFactory.getCurrentSession().save(job);
		
	}

	@Transactional
	public void update(Job job) {
		sessionFactory.getCurrentSession().update(job);
		
	}

	@Transactional
	public void delete(Job job) {
		sessionFactory.getCurrentSession().delete(job);
		
	}

	@Transactional
	public Job get(int id) {
		Session session=sessionFactory.getCurrentSession();
		Job job=(Job)session.get(Job.class, id);
		return job;
	}

	@Transactional
	public List<Job> getAllJobs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Job");
		return query.list();
	}

}
