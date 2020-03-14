package com.ss.project2.entity;

public class BookCopy extends Entity {
	
	private static final long serialVersionUID = -7007087596339055539L;
	
	private Integer noOfCopies;
	private Book book;
	private LibraryBranch libraryBranch;
	
	public BookCopy() {
		this.noOfCopies = null;
		this.book = null;
		this.libraryBranch = null;
	}
	
	public BookCopy(int noOfCopies, Book book, LibraryBranch libraryBranch) {
		this.noOfCopies = noOfCopies;
		this.book = book;
		this.libraryBranch = libraryBranch;
	}
	
	public int getNoOfCopies() {
		return noOfCopies;
	}
	
	public Book getBook() {
		return book;
	}
	
	public LibraryBranch getLibraryBranch() {
		return libraryBranch;
	}
	
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public void setLibraryBranch(LibraryBranch libraryBranch) {
		this.libraryBranch = libraryBranch;
	}
	
}
