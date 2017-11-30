package com.niit.backend.dao;

import java.util.List;

import com.niit.backend.model.Blog;
import com.niit.backend.model.BlogComment;
import com.niit.backend.model.Forum;

public interface IForumDao {
public void save(Forum forum);
	
	public void update(Forum forum);
	
	public void delete(Forum forum);
	
	public Forum get(int id);
	
	public Forum getName(String name);
	
	public List<Forum> list();
	
	/*public boolean addComment(ForumComment Forumcomment);
	
	public List<ForumComment> listComment(int id);
	
	public List<ForumComment> listOfAllComment();
*/
}
