package com.ss.project1;

//Brendan Raymond
//Data representation of Book
class Book extends CatalogEntity {
	
	//Must be able to link to Author and Publisher of Book
	private int authorID;
	private int publisherID;
	
	Book(int id, String name, int authorID, int publisherID) {
		super(id, name);
		this.authorID = authorID;
		this.publisherID = publisherID;
	}
	
	int getAuthorID() {
		return this.authorID;
	}
	
	int getPublisherID() {
		return this.publisherID;
	}
	
	void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	
	void setPublisherID(int publisherID) {
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
