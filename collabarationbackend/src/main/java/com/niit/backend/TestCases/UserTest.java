package com.niit.backend.TestCases;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.backend.dao.IUserDao;
import com.niit.backend.model.User;

public class UserTest {
	
	static IUserDao userDao;
	@BeforeClass
	public static void initialize(){
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.scan("com.niit");
		annotationConfigApplicationContext.refresh();
		userDao=(IUserDao) annotationConfigApplicationContext.getBean("userDao");
		}

	@Test
	public void test() {
		User user=new User();
		user.setUser_name("user");
		user.setRole("USER");
		user.setEmail("user@gmail.com");
		user.setFirstname("user");
		user.setLastname("user");
		user.setPassword("user");
		user.setContact(123456789);
		user.setUser_id(1234);
		assertTrue("problem in adding",userDao.save(user));
	}

}
