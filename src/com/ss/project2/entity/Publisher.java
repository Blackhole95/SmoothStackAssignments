package com.ss.project2.entity;

import java.util.List;

public class Publisher extends Entity {
	
	private static final long serialVersionUID = -8369126953027266244L;
	
	private Integer id;
	private String name;
	private String address;
	private List<Entity> bookList;
	
	public Publisher() {
		this.id = null;
		this.name = null;
		this.address = null;
		this.bookList = null;
	}
	
	public Publisher(Integer id, String name, String address, List<Entity> bookList) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.bookList = bookList;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAddress() {
		return this.address;
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
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setBookList(List<Entity> bookList) {
		this.bookList = bookList;
	}
	
}
