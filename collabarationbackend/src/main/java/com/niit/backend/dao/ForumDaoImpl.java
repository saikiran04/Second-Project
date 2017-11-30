package com.niit.backend.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niit.backend.model.Blog;
import com.niit.backend.model.Forum;

public class ForumDaoImpl implements IForumDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public ForumDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	@Transactional
	public void save(Forum forum) {
		sessionFactory.getCurrentSession().save(forum);
	}

	@Transactional
	public void update(Forum forum) {
		sessionFactory.getCurrentSession().update(forum);
	}

	@Transactional
	public void delete(Forum forum) {
		sessionFactory.getCurrentSession().delete(forum);
	}
    
	@Transactional
	public Forum get(int id) {
		Session session=sessionFactory.getCurrentSession();
		Forum forum=(Forum)session.get(Forum.class, id);
		return forum;
	}

	public Forum getName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<Forum> list() {
		List<Forum> list=sessionFactory.getCurrentSession().createCriteria(Forum.class).list();
		return list;
	}

}
