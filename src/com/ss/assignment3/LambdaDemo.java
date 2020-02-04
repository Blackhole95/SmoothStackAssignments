package com.ss.assignment3;

import java.util.Arrays;
import java.util.List;

public class LambdaDemo {
	
	public static void main(String[] args) {
		//stringList.stream().sorted((a, b) -> a.length() - b.length()).forEach(System.out::println);
		System.out.println("1) Basic Lambdas:");
		String[] org = {"Andrew", "Max", "David", "Sylvester", "Alexander", "Luke Skywalker", "J"};
		System.out.println("Original List:");
		for(String s : org)
			System.out.println(s);
		System.out.println("Sorted by Length:");
			
		System.out.println("Sorted by Reverse Length:");
		
		System.out.println("Sorted Alphabetically by First Character Only:");
		
		System.out.println("Strings Containing 'E' First:");
		
		System.out.println("The Previous One, But With A Static Helper Method:");
		
	}
	
}
