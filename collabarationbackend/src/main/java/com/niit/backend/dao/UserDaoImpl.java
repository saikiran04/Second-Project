package com.niit.backend.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.backend.model.User;

@Repository(value="IUserDao")
public class UserDaoImpl implements IUserDao {
	
	@SuppressWarnings("unused")
	private static final Logger logger=LoggerFactory.getLogger(UserDaoImpl.class);
	
    @Autowired
    private SessionFactory sessionFactory;
    
	public UserDaoImpl(SessionFactory sessionFactory) {
		System.out.println("in UserDaoImpl");
		this.sessionFactory=sessionFactory;
		
	}
    @Transactional
	public boolean save(User user) {
		
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    @Transactional
	public void update(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.update(user);
	}
    @Transactional
	public boolean delete(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

    @Transactional
	public User get(int userID) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class,userID);
		return user;
	}

	public User getName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> list() {
		String hql="from User";
		@SuppressWarnings("rawtypes")
		Query query= sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
		
	}
	@Transactional
	public User isValidUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		System.out.println("i am here in login");
		System.out.println("User name"+user.getUser_name());
		Query query=session.createQuery("from User where user_name=? and password=?");
		query.setString(0, user.getUser_name());
		query.setString(1, user.getPassword());
		List<User> list=query.list();
		
		if(list!=null) {
			System.out.println(list.size());
			return list.get(0);
		}else {
			return null;
		}
	}

	public void setOnline(int userID) {
		// TODO Auto-generated method stub

	}

	public void SetOffline(int userID) {
		// TODO Auto-generated method stub

	}

}
