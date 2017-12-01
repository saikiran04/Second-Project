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

import com.niit.backend.dao.IBlogDao;
import com.niit.backend.dao.IUserDao;
import com.niit.backend.model.BaseDomain;
import com.niit.backend.model.Blog;
import com.niit.backend.model.BlogComment;
import com.niit.backend.model.User;

@RestController
public class BlogController {
	
	private static final Logger logger=LoggerFactory.getLogger(BlogController.class);
	
	@Autowired
	IBlogDao blogDao;
	
	@Autowired
	Blog blog;
	
	@Autowired
	IUserDao userDao;
	
	@RequestMapping(value="/blogs",method=RequestMethod.GET)
	public ResponseEntity<List<Blog>> listAllBlogs(){
		
		logger.debug("calling method listAllBlogs");
		List<Blog> blog=blogDao.viewBlogs();
		if(blog.isEmpty()) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
	}
	
	@RequestMapping(value="/blog",method=RequestMethod.POST)
	public ResponseEntity<?> createBlog(@RequestBody Blog blog,HttpSession httpSession){
		
		Date dt=new java.util.Date();
		blog.setCreation_date(dt.toString());
		User user=(User) httpSession.getAttribute("loggedInUser");
		blog.setUser_name(user.getUser_name());
		//blog.setUser_name("student1");
		blog.setStatus("Published");
		try {
			blogDao.save(blog);
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}catch(Exception e) {
			
			BaseDomain error=new BaseDomain();
			return new ResponseEntity<BaseDomain>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value="/updateBlog",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateBlog(@RequestBody Blog blog) {
		blogDao.update(blog);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteBlog/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBlog(@PathVariable int id){
		Blog blog=blogDao.get(id);
		blogDao.delete(blog);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/blog/{id}",method=RequestMethod.GET)
	public ResponseEntity<Blog> getBlog(@PathVariable("id") int blog_id){
		logger.debug("calling method getBlog for user id: "+blog_id);
		Blog blog=blogDao.get(blog_id);
		
		if(blog==null) {
			logger.debug("Blog does not exist with id:" +blog_id);
			blog=new Blog();
			blog.setErrorMessage("Blog does not exists with id:" +blog_id);
			return new ResponseEntity<Blog> (blog,HttpStatus.NOT_FOUND);
			}else {
				
				logger.debug("blog exists with id:" + blog_id);
				return new ResponseEntity<Blog> (blog,HttpStatus.OK);
			}
	}
	@RequestMapping(value="/addblogcomment",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
		if(session.getAttribute("username")==null){
			BaseDomain error=new BaseDomain();
			return new ResponseEntity<BaseDomain>(error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		User user=userDao.getName(username);
		blogComment.setUser_name(username);//set the value for foreign key 'username' in blogcomment table
		blogComment.setCommentDate(new Date());//set the value for commentedOn
		try{
		blogDao.addComment(blogComment);
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		}catch(Exception e){
			BaseDomain error=new BaseDomain();
			return new ResponseEntity<BaseDomain>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
   @RequestMapping(value="/getblogcomments/{blogPostId}")
	public ResponseEntity<?> getBlogComments(@PathVariable int blogPostId,HttpSession session){
		if(session.getAttribute("username")==null){
			BaseDomain error=new BaseDomain();
			return new ResponseEntity<BaseDomain>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogComment> blogComments=blogDao.getAllBlogComments(blogPostId);
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	}
	
	
	
}
