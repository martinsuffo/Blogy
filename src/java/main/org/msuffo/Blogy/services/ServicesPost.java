package src.java.main.org.msuffo.Blogy.services;

import org.msuffo.Blogy.dao.PostDAO;
import org.msuffo.Blogy.models.Post;
import org.msuffo.Blogy.exceptions.NoPermissionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesPost{
	@Autowired
	private PostDAO postDAO;
	
	public void RegPost(Post post) throws NoPermissionException{
		
		if(post.getAuthor().equals("msuffo"))
			postDAO.saveOrUpdate(post);
		else throw new NoPermissionException("No tenés permiso para hacer eso, gill");
	}
}
