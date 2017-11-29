package com.niit.backend.dao;

import java.util.List;

import com.niit.backend.model.Blog;
import com.niit.backend.model.BlogComment;
import com.niit.backend.model.Forum;

public interface IForumDao {
public boolean save(Forum forum);
	
	public boolean update(Forum forum);
	
	public boolean delete(Forum forum);
	
	public Forum get(int forumID);
	
	public Forum getName(String name);
	
	public List<Forum> list();
	
	/*public boolean addComment(ForumComment Forumcomment);
	
	public List<ForumComment> listComment(int id);
	
	public List<ForumComment> listOfAllComment();
*/
}
