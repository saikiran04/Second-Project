package com.niit.backend.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import com.niit.backend.model.BlogComment;

@Transactional
@Repository
@ComponentScan("com")
public class BlogCommentDaoImpl implements IBlogCommentDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public void addComment(BlogComment blogComment) {
		sessionFactory.getCurrentSession().save(blogComment);

	}

	public List<BlogComment> viewComments(String blogid) {
		System.out.println("in coment dao impl");
		Criteria ct=sessionFactory.getCurrentSession().createCriteria(BlogComment.class);
		ct.add(Restrictions.eq("blogid",blogid));
		List list=ct.list();
		
		return list;
	}

}
