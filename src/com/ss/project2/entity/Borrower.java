package com.ss.project2.entity;

import java.util.List;

public class Borrower extends Entity {
	
	private static final long serialVersionUID = -7719412145348914093L;
	
	private Integer cardNo;
	private String name;
	private String address;
	private String phone;
	private List<Entity> bookLoanList;
	
	public Borrower() {
		this.cardNo = null;
		this.name = null;
		this.address = null;
		this.phone = null;
		this.bookLoanList = null;
	}
	
	public Borrower(Integer cardNo, String name, String address, String phone, List<Entity> bookLoanList) {
		this.cardNo = cardNo;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.bookLoanList = bookLoanList;
	}
	
	public Integer getCardNo() {
		return this.cardNo;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public List<Entity> getBookLoanList() {
		return this.bookLoanList;
	}
	
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setBookLoanList(List<Entity> bookLoanList) {
		this.bookLoanList = bookLoanList;
	}
	
}
