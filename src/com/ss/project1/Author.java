package com.ss.project1;

//Brendan Raymond
//Data representation of Author
class Author extends CatalogEntity {
	
	Author(int id, String name) {
		super(id, name);
	}
	
	@Override
	public String toString() {
		return "Author Information:" +
			   "\nName: " + this.getName() +
			   "\nID:   " + this.getID();
	}
	
}
