package src.java.main.org.msuffo.Blogy.dao;

import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.msuffo.Blogy.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PostDAOImpl implements PostDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void saveOrUpdate(Post post) {
	    if (post.getId() > 0) {
	        // update
	        String sql = "UPDATE post SET title=?, content=? WHERE _id=?";
	        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), post.getId());
	    } else {
	        // insert
	        String sql = "INSERT INTO post (title, content, author, date)"
	                    + " VALUES (?, ?, ?, ?)";
	        jdbcTemplate.update(sql, post.getTitle(), post.getContent(),
	                post.getAuthor(), post.getDate());
	    }
	}
	
	@Override
	public void delete(int postId) {
	    String sql = "DELETE FROM post WHERE _id=?";
	    jdbcTemplate.update(sql, postId);
	}

	@Override
	public  List<Post> list() {
	    String sql = "SELECT * FROM post";
	    List<Post> listPost = jdbcTemplate.query(sql, new RowMapper<Post>() {
	    	@Override
	        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Post aPost = new Post(rs.getInt("_id"), rs.getString("title"), rs.getString("content"), rs.getDate("date"), rs.getString("author"));

	            return aPost;
	        }
	 
	    });
	 
	    return listPost;
	}

	@Override
	//Retorna NULL si no encuentra post
	public Post get(int postId){
	    String sql = "SELECT * FROM post WHERE _id=" + postId;
	    return jdbcTemplate.query(sql, (rs) -> {
	        if (rs.next()) {
	            Post post = new Post(rs.getInt("_id"), rs.getString("title"), rs.getString("content"), rs.getDate("date"), rs.getString("author"));
	        return post;
	    }
	    return null;
	    });
	}
	
}
