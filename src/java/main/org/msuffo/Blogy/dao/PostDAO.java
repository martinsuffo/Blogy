package src.java.main.org.msuffo.Blogy.dao;

import java.util.List;
import org.msuffo.Blogy.models.Post;

public interface PostDAO {
	     
	public void saveOrUpdate(Post post);
	     
	public void delete(int postId);
	     
	public Post get(int postId);
	  
	public List<Post> list();
}
