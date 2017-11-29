package com.niit.backend.dao;

import java.util.List;

import com.niit.backend.model.Blog;
import com.niit.backend.model.BlogComment;
import com.niit.backend.model.User;

public interface IBlogDao {
	
public void save(Blog blog);
	
	public void update(Blog blog);
	
	public void delete(Blog blog);
	
	public Blog get(int id);
	
	public Blog getName(String name);
	
	public List<Blog> viewBlogs();
	
	List<Blog> viewAllBlogs();
	
	public boolean addComment(BlogComment blogcomment);
	
	public List<BlogComment> listComment(int id);
	
	public List<BlogComment> listOfAllComment();

}
