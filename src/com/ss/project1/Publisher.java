package com.ss.project1;

//Brendan Raymond
//Data representation of Author
class Publisher extends Item {
	
	private String address;
	
	public Publisher(int id, String name, String address) {
		super(id, name);
		this.address = address;
	}
	
	public Publisher(String[] data) {
		super(data);
		this.address = data[3];
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
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
