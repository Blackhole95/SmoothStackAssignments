package com.ss.project2.entity;

import java.util.List;

public class LibraryBranch extends Entity {
	
	private static final long serialVersionUID = -355491984998859349L;
	
	private Integer id;
	private String name;
	private String address;
	private List<Entity> bookCopyList;
	private List<Entity> bookLoanList;
	
	public LibraryBranch() {
		this.id = null;
		this.name = null;
		this.address = null;
		this.bookCopyList = null;
		this.bookLoanList = null;
	}
	
	public LibraryBranch(Integer id, String name, String address, List<Entity> bookCopyList, List<Entity> bookLoanList) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.bookCopyList = bookCopyList;
		this.bookLoanList = bookLoanList;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public List<Entity> getBookCopyList() {
		return bookCopyList;
	}
	
	public List<Entity> getBookLoanList() {
		return bookLoanList;
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
	
	public void setBookCopyList(List<Entity> bookCopyList) {
		this.bookCopyList = bookCopyList;
	}
	
	public void setBookLoanList(List<Entity> bookLoanList) {
		this.bookLoanList = bookLoanList;
	}
	
}
