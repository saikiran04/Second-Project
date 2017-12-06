package com.niit.backend.TestCases;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.backend.dao.IBlogDao;
import com.niit.backend.model.Blog;
public class BlogTest {

	static IBlogDao blogDao;
	@BeforeClass
	public static void initialize(){
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.scan("com.niit");
		annotationConfigApplicationContext.refresh();
		blogDao=(IBlogDao) annotationConfigApplicationContext.getBean("blogDao");
		}

	
	@Test
	public void addBlog() {
		Blog blog = new Blog();
		blog.setBlog_title("blog");
		blog.setBlog_id(123);
		blog.setDescription("blog");
		blog.setLikes(100);
		blog.setStatus("online");
		
		blog.setCreation_date("12/11/2015");
		

	}
	@Ignore
	@Test
	public void deleteBlogTest()
	{
		Blog blog=(Blog)blogDao.get(1);
		
	}
	@Ignore
	@Test
	public void updateBlogTest()
	{
		Blog blog=(Blog)blogDao.get(2);
		blog.setBlog_title("java");
		blog.setCategory("vanilla java");
		
	}
	@Ignore
	@Test
	public void getBlogTest()
	{
		Blog blog=blogDao.get(2);
		assertNotNull("",blog);
		System.out.println("blog name "+blog.getBlog_title());
		System.out.println("blog content"+blog.getDescription());
	}
	@Ignore
	@Test
	public void getAllBlogsTest()
	{
		List<Blog>blogList=(List<Blog>)blogDao.viewBlogs();
		assertNotNull("blog is null",blogList.get(0));
		for(Blog blog:blogList)
		{
			System.out.println("blog is"+blog.getBlog_id()+":::"+"blog name::"+blog.getBlog_title()+"");
		}
	}
	

}
