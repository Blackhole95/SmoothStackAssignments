package com.ss.project2.entity;

import java.util.List;

public class Author extends Entity {
	
	private static final long serialVersionUID = 3914243648872856985L;
	
	private Integer id;
	private String name;
	private List<Entity> bookList;
	
	public Author() {
		this.id = null;
		this.name = null;
		this.bookList = null;
	}
	
	public Author(Integer id, String name, List<Entity> bookList) {
		this.id = id;
		this.name = name;
		this.bookList = bookList;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<Entity> getBookList() {
		return this.bookList;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBookList(List<Entity> bookList) {
		this.bookList = bookList;
	}
	
}
