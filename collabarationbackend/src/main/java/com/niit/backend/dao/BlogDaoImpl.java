package com.niit.backend.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.backend.model.Blog;
import com.niit.backend.model.BlogComment;
import com.niit.backend.model.User;

@SuppressWarnings("deprication")
@Repository(value="BlogDao")
public class BlogDaoImpl implements IBlogDao {

	@Autowired
	private SessionFactory sessionFactory;
	

	public BlogDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
    @Transactional
	public void save(Blog blog) {
		sessionFactory.getCurrentSession().save(blog);
	}
    
    @Transactional
	public void update(Blog blog) {
    	sessionFactory.getCurrentSession().update(blog);
	}
    
	@Transactional
	public void delete(Blog blog) {
    	sessionFactory.getCurrentSession().delete(blog);
	}
	

	@Transactional
	public Blog get(int id) {
		Session session=sessionFactory.getCurrentSession();
		Blog blog=(Blog)session.get(Blog.class, id);
		return blog;
	}

	@Transactional
	public Blog getName(String name) {
		String hql="from Blog where blogID=" + "'" +name + "'";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Blog> list=(List<Blog>) query.list();
		
		if(list !=null&&!list.isEmpty()) {
			System.out.println("blog retreived from Daoimpl");
			return list.get(0);
		}else {
			return null;
		}
	}

	@Transactional
	public List<Blog> viewBlogs() {
		List<Blog> list=sessionFactory.getCurrentSession().createCriteria(Blog.class).list();
		return list;
	}

	@Transactional
	public boolean addComment(BlogComment blogcomment) {
		try {
			sessionFactory.getCurrentSession().save(blogcomment);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public List<BlogComment> listComment(int id) {
		System.out.println("in comment daoimpl");
		Criteria ct=sessionFactory.getCurrentSession().createCriteria(BlogComment.class);
		ct.add(Restrictions.eq("blogid",id));
		List list=ct.list();
		return list;
		
	}

	@Transactional
	public List<BlogComment> getAllBlogComments(int id) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogComment where id=?");
		query.setInteger(0, id);
		return query.list();
	}
	
	public List<Blog> viewAllBlogs() {
		Session session=sessionFactory.getCurrentSession();
		Criteria ct=session.createCriteria(Blog.class);
		ct.add(Restrictions.eq("status","true"));
		List<Blog> list=ct.list();
		return list;
	}
	

}
