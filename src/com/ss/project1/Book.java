package com.ss.project1;

//Brendan Raymond
//Data representation of Book
class Book extends Item {
	
	private int authorID;
	private int publisherID;
	
	public Book(int id, String name, int authorID, int publisherID) {
		super(id, name);
		this.authorID = authorID;
		this.publisherID = publisherID;
	}
	
	public Book(String[] data) {
		super(data);
		this.authorID = Integer.parseInt(data[2]);
		this.publisherID = Integer.parseInt(data[3]);
	}
	
	public int getAuthorID() {
		return this.authorID;
	}
	
	public int getPublisherID() {
		return this.publisherID;
	}
	
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	
	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}
	
	@Override
	public String toString() {
		return "Book Information:" +
			   "\nName:         " + this.getName() +
			   "\nID:           " + this.getID() +
			   "\nAuthor ID:    " + this.getAuthorID() +
			   "\nPublisher ID: " + this.getPublisherID();
	}
	
}
