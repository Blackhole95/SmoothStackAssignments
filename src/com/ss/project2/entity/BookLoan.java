package com.ss.project2.entity;

import java.time.LocalDate;
import java.util.Date;

public class BookLoan extends Entity {
	
	private static final long serialVersionUID = 5712475053560115106L;
	
	private Date dateIn;
	private Date dateOut;
	private Date dueDate;
	private Book book;
	private Borrower borrower;
	private LibraryBranch libraryBranch;
	
	public BookLoan() {
		this.dateIn = null;
		this.dateOut = null;
		this.dueDate = null;
		this.book = null;
		this.borrower = null;
		this.libraryBranch = null;
	}
	
	public BookLoan(Date dateIn, Date dateOut, Date dueDate, Book book, Borrower borrower, LibraryBranch libraryBranch) {
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
		this.book = book;
		this.borrower = borrower;
		this.libraryBranch = libraryBranch;
	}
	
	public LocalDate getDateIn() {
		return this.dateIn;
	}
	
	public LocalDate getDateOut() {
		return this.dateOut;
	}
	
	public LocalDate getDueDate() {
		return this.dueDate;
	}
	
	public Book getBook() {
		return this.book;
	}
	
	public Borrower getBorrower() {
		return this.borrower;
	}
	
	public LibraryBranch getBranch() {
		return this.libraryBranch;
	}
	
	public void setDateIn(LocalDate dateIn) {
		this.dateIn = dateIn;
	}
	
	public void setDateOut(LocalDate dateOut) {
		this.dateOut = dateOut;
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	
	public void setBranch(LibraryBranch libraryBranch) {
		this.libraryBranch = libraryBranch;
	}
	
}
