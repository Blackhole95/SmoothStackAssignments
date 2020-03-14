package com.ss.project1;

//Brendan Raymond
//Data representation of Author
class Author extends Item {
	
	public Author(int id, String name) {
		super(id, name);
	}
	
	public Author(String[] data) {
		super(data);
	}
	
	@Override
	public String toString() {
		return "Author Information:" +
			   "\nName: " + this.getName() +
			   "\nID:   " + this.getID();
	}
	
}
