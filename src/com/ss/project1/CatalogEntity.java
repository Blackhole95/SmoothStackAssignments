package com.ss.project1;

//Brendan Raymond
//Superclass for entities handled by Catalog
abstract class CatalogEntity {
	
	//All entities have a name and id
	private int id;
	private String name;
	
	CatalogEntity(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	int getID() {
		return this.id;
	}
	
	String getName() {
		return this.name;
	}
	
	void setID(int authorID) {
		this.id = authorID;
	}
	
	void setName(String name) {
		this.name = name;
	}
		
}
