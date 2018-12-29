package src.java.main.org.msuffo.Blogy.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Post {
	
	private Integer id;
	private String  title;
	private String  content;
	@JsonFormat(pattern="dd.MM.yyyy")
	private Date    date;
	private String  author;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Post(Integer id, String title, String content, Date date, String author) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.date = new Date();
		this.author = author;
	}		
	
}
