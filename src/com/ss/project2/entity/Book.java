package com.ss.project2.entity;

import java.util.List;

public class Book extends Entity {
	
	private static final long serialVersionUID = -6341760223870258400L;
	
	private Integer id;
	private String title;
	private Publisher publisher;
	private List<Entity> authorList;
	private List<Entity> bookCopyList;
	private List<Entity> bookLoanList;
	private List<Entity> genreList;
	
	public Book() {
		this.id = null;
		this.title = null;
		this.publisher = null;
		this.authorList = null;
		this.bookCopyList = null;
		this.bookLoanList = null;
		this.genreList = null;
	}
	
	public Book(Integer id, String title, Publisher publisher, List<Entity> authorList, List<Entity> bookCopyList, List<Entity> bookLoanList, List<Entity> genreList) {
		this.id = id;
		this.title = title;
		this.publisher = publisher;
		this.authorList = authorList;
		this.bookCopyList = bookCopyList;
		this.bookLoanList = bookLoanList;
		this.genreList = genreList;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public Publisher getPublisher() {
		return this.publisher;
	}
	
	public List<Entity> getAuthorList() {
		return this.authorList;
	}
	
	public List<Entity> getBookCopyList() {
		return this.bookCopyList;
	}
	
	public List<Entity> getBookLoanList() {
		return this.bookLoanList;
	}
	
	public List<Entity> getGenreList() {
		return this.genreList;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	public void setAuthorList(List<Entity> authorList) {
		this.authorList = authorList;
	}
	
	public void setBookCopyList(List<Entity> bookCopyList) {
		this.bookCopyList = bookCopyList;
	}
	
	public void setBookLoanList(List<Entity> bookLoanList) {
		this.bookLoanList = bookLoanList;
	}
	
	public void setGenreList(List<Entity> genreList) {
		this.genreList = genreList;
	}
	
}
