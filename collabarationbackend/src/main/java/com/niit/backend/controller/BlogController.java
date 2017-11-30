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
	
	@RequestMapping(value="/blogcomment/{id}",method=RequestMethod.POST)
	public ResponseEntity<BlogComment> createBlogComment(@PathVariable("id") int blog_id,@RequestBody BlogComment blogcomment,HttpSession httpSession){
		logger.debug("calling method createBlogComment" + blogcomment.getBlog_id());
		Integer loggedInUserID=(Integer) httpSession.getAttribute("loggedInUserID");
		User username=(User) httpSession.getAttribute("loggedInUser");
		blogcomment.setUser_id(loggedInUserID);
		Date dt=new java.util.Date();
		blogcomment.setCommentDate(dt.toString());
		blogcomment.setUser_name(username.getUser_name());
		blogcomment.setBlog_id(blog_id);
		if(blogDao.addComment(blogcomment)==true){
			blogcomment.setErrorCode("200");
			blogcomment.setErrorMessage("Comment saved");
		
		}else{
			return new ResponseEntity<BlogComment>(blogcomment,HttpStatus.BAD_REQUEST);
		}
				
		return new ResponseEntity<BlogComment>(blogcomment,HttpStatus.OK);
			}

	@RequestMapping(value="/blogscommentlistperblog/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> listAllBlogsCommentsPerBlog(@PathVariable("id") int blog_id){
		logger.debug("calling method listAllBlogs");
		List<BlogComment> blogcomment=blogDao.listComment(blog_id);
		if(blogcomment.isEmpty()){
			return new ResponseEntity<List<BlogComment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BlogComment>>(blogcomment,HttpStatus.OK);
	}


	@RequestMapping(value="/blogscommentlist/",method=RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> listAllBlogsComments(){
		logger.debug("calling method listAllBlogs");
		List<BlogComment> blogcomment=blogDao.listOfAllComment();
		if(blogcomment.isEmpty()){
			return new ResponseEntity<List<BlogComment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BlogComment>>(blogcomment,HttpStatus.OK);
	}

	
	
}
