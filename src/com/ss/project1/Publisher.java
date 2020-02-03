package com.ss.project1;

//Brendan Raymond
//Data representation of Author
class Publisher extends CatalogEntity {
	
	//Must be able to find Publisher in real world
	private String address;
	
	Publisher(int id, String name, String address) {
		super(id, name);
		this.address = address;
	}
	
	String getAddress() {
		return this.address;
	}
	
	void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Publisher Information:" +
			   "\nName:       " + this.getName() +
			   "\nID:         " + this.getID() +
			   "\nAddress ID: " + this.getAddress();
	}
	
}
