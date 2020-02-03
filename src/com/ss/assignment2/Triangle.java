package com.ss.assignment2;

//Brendan Raymond
public class Triangle implements Shape {
	
	private int base;
	private int height;
	
	public Triangle(int base, int height) {
		this.base = base;
		this.height = height;
	}
	
	public int calculateArea() {
		return (this.base * this.height) / 2;
	}
	
	@Override
	public void display() {
		System.out.println("Triangle Man hates Particle Man.");
	}
	
}
