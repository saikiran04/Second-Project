package com.niit.backend.TestCases;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.backend.dao.IForumDao;
import com.niit.backend.model.Forum;

public class ForumTest {

	static IForumDao forumDao;
	@BeforeClass
	public static void initialize(){
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.scan("com.niit");
		annotationConfigApplicationContext.refresh();
		forumDao=(IForumDao) annotationConfigApplicationContext.getBean("forumDao");
		}
	@Ignore
	@Test
	public void addForum() {
		Forum forum=new Forum();
		forum.setCategory("forum");
		forum.setForum_id(123);
		forum.setForum_title("forum");
		forum.setStatus("offline");
		forum.setUser_name("user");
		
	}
	@Ignore
	@Test
	public void deleteForumTest()
	{
		Forum forum=(Forum)forumDao.get(2);
		
	}
	@Ignore
	@Test
	public void updateForumTest()
	{
		Forum forum=(Forum)forumDao.get(1);
		forum.setCategory("forum2");;
		forum.setForum_title("vanilla name");
		
	}
	@Test
	public void getAllForumsTest()
	{
		List<Forum>forumList=(List<Forum>)forumDao.list();
		assertNotNull("",forumList.get(0));
		for(Forum forum:forumList)
		{
			System.out.println("forum name="+forum.getForum_title()+":::"+"forum Contente::"+forum.getCategory());
					
		}
	}

}
