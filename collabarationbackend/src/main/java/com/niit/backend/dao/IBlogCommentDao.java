package com.niit.backend.dao;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import com.niit.backend.model.BlogComment;

@Repository
@ComponentScan("com")
public interface IBlogCommentDao {
	
	void addComment(BlogComment blogComment);
	List<BlogComment> viewComments(String blogid);

}
