package com.ss.project1;

//Brendan Raymond
//Superclass for entities handled by Catalog
abstract class Item{
	
	private int id;
	private String name;
	
	public Item(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Item(String[] data) {
		this.id = Integer.parseInt(data[0]);
		this.name = data[1];
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setID(int authorID) {
		this.id = authorID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
}
