package com.ss.assignment3;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaDemo {
	
	public static void main(String[] args) {
		//Variables
		String[] stringArray = {"andrew", "Meg", "Robert", "Samantha", "Alex", "Luke Skywalker", "Xe", "Abc", "abe", "aaa"};
		List<Integer> integerList = Arrays.asList(1, 22, 333, 4444, 555, 66, 7);
		List<String> stringList = Arrays.asList(stringArray);
		
		//LAMBDAS, FUNCTIONAL INTERFACES, AND STREAMS
		//1) Basic Lambdas
		System.out.println("LAMBDAS, FUNCTIONAL INTERFACES, AND STREAMS\n\n" +
						   "1) Basic Lambdas\n\n" +
						   "Original Array:");
		LambdaDemo.printStringArray(stringArray);
		
		//Sorted by Length
		System.out.println("Sorted by Length:");
		Arrays.sort(stringArray, (a, b) -> a.length() - b.length());
		LambdaDemo.printStringArray(stringArray);
		
		//Sorted by Reverse Length
		System.out.println("Sorted by Reverse Length:");
		Arrays.sort(stringArray, (a, b) -> b.length() - a.length());
		LambdaDemo.printStringArray(stringArray);
		
		//Sorted Alphabetically by First Character Only
		System.out.println("Sorted Alphabetically by First Character Only:");
		Arrays.sort(stringArray, (a, b) -> a.charAt(0) - b.charAt(0));
		LambdaDemo.printStringArray(stringArray);
		
		//Strings Containing 'E' First
		System.out.println("Strings Containing 'E' First:");
		Arrays.sort(stringArray, (a, b) -> (a.contains("e") && !b.contains("e")) ? -1 : 1);
		LambdaDemo.printStringArray(stringArray);
		
		//Strings Containing 'E' First:, But With A Static Helper Method
		System.out.println("Re-Sorted by Length:");
		Arrays.sort(stringArray, (a, b) -> a.length() - b.length());
		LambdaDemo.printStringArray(stringArray);
		System.out.println("Strings Containing 'E' First:, But With A Static Helper Method:");
		Arrays.sort(stringArray, (a, b) -> LambdaDemo.eFirst(a, b));
		LambdaDemo.printStringArray(stringArray);
		
		//2) Integer List
		System.out.println("\n2) Integer List\n\n" +
						   "Original List:");
		LambdaDemo.printIntegerList(integerList);
		System.out.println("String Representation:\n" + 
						   LambdaDemo.integerListToString(integerList));
		
		//3) String List
		System.out.println("\n\n3) String List\n\n" +
						   "Original List:");
		LambdaDemo.printStringList(stringList);
		System.out.println("Filtered List:");
		stringList = LambdaDemo.specificList(stringList);
		LambdaDemo.printStringList(stringList);
		
		//DATE-TIME API
		//1) Which class would you use to store your birthday in years, months, days, seconds, and nanoseconds?
		System.out.println("DATE-TIME API\n\n" +
						   "1) Q: Which class would you use to store your birthday in years, months, days, seconds, and nanoseconds?\n" +
						   "   A: One can use 'LocalDateTime' to store your birthday in years, months, days, seconds, and nanoseconds.\n");
		//2) Given a random date, how would you find the date of the previous Thursday?
		System.out.println("2) Q: Given a random date, how would you find the date of the previous Thursday?\n" +
					 	   "   A: Given a random date, 'date', one can use 'date.with(TemporalAdjusters.previous(DayOfWeek.THURSDAY)))' to find the previous Thursday.\n");
		//3) What is the difference between a ZoneId and a ZoneOffset?
		System.out.println("3) Q: What is the difference between a ZoneId and a ZoneOffset?\n" +
			 	   		   "   A: ZoneId is used to represent a given time-zone, while ZoneOffset is used to represent the time offset for a given time-zone (relative to global time).\n");
		//4) How would you convert an Instant to a ZonedDateTime? How would you convert a ZonedDateTime to an Instant?
		System.out.println("4) Q: How would you convert an Instant to a ZonedDateTime? How would you convert a ZonedDateTime to an Instant?\n" +
	 	   		   		   "   A: One can use 'Instant.atZone(ZoneId zone)' to convert from Instant to ZonedDateTime, and 'ZonedDateTime.toInstant()' to convert from ZonedDateTime to Instant.\n");
		//5) Write an example that, for a given year, reports the length of each month within that year.
		System.out.println("5) The following are all the month lengths (in days) for 2012 and 2019, respectively:\n");
		LambdaDemo.monthLengths(2012);
		LambdaDemo.monthLengths(2019);
		//6) Write an example that, for a given month of the current year, lists all of the Mondays in that month.
		System.out.println("6) The following are all the Mondays in January and August of this year (YYYY-MM-DD):\n");
		LambdaDemo.allMondays("JANUARY");
		LambdaDemo.allMondays("AUGUST");
		//7) Write an example that tests whether a given date occurs on Friday the 13th.
		System.out.println("7) The following are the results of paasing '2020/2/13' and '2020/3/13' to a method to determine whether or not a given date occurs on Friday the 13th:\n");
		LambdaDemo.fridayThirteenth(2020, 2, 13);
		LambdaDemo.fridayThirteenth(2020, 3, 13);
		
	}
	
	//Print contents of string array
	private static void printStringArray(String[] stringArray) {
		System.out.print(stringArray[0]);
		for(int i = 1; i < stringArray.length; i++)
			System.out.print(", " + stringArray[i]);
		System.out.print("\n\n");
	}
	
	//Return -1 if a contains 'e' and b does not, else returns 1
	//Used to sort string array so that strings containing 'e' come first
	private static int eFirst(String a, String b) {
		return (a.contains("e") && !b.contains("e")) ? -1 : 1;
	}
	
	//Print contents of List
	private static void printIntegerList(List<Integer> integerList) {
		System.out.print(integerList.get(0));
		for(int i = 1; i < integerList.size(); i++)
			System.out.print(", " + integerList.get(i));
		System.out.print("\n\n");
	}
	
	//Print contents of List
	private static void printStringList(List<String> stringList) {
		System.out.print(stringList.get(0));
		for(int i = 1; i < stringList.size(); i++)
			System.out.print(", " + stringList.get(i));
		System.out.print("\n\n");
	}
	
	//Converts integer list to string, with each integer marked as even or odd
	private static String integerListToString(List<Integer> integerList) {
		return integerList
				.stream()
				.map(i -> ((((i / 2) * 2) == i) ? "e" : "o") + i.toString())
				.collect(Collectors.joining(","));
	}
	
	//Return list containing only strings of length 3 that start with 'a'
	private static List<String> specificList(List<String> stringList) {
		return stringList
				.stream()
				.filter(s -> (((String)s).substring(0, 1).equals("a")) && (((String)s).length() == 3))
				.collect(Collectors.toList());
	}
	
	//Print length of each month in a given year
	private static void monthLengths(int year) {
		System.out.println("Year:      " + year);
		for(Month month : Month.values()) {
			YearMonth yearMonth = YearMonth.of(year, month);
			//System.out.println(month + ": " + yearMonth.lengthOfMonth() + " Days");
			System.out.printf("%-10s %d Days%n", month + ":", yearMonth.lengthOfMonth());
		}
		System.out.println();
	}
	
	//Print list of Mondays in a given month of the current year
	private static void allMondays(String month) {
		Month realMonth = Month.valueOf(month);
		LocalDate date = Year
				.now()
				.atMonth(realMonth)
				.atDay(1)
				.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
		Month checkMonth = date.getMonth();
		System.out.println("Mondays in " + month + " of " + date.getYear() + ":");
		while(checkMonth == realMonth) {
			System.out.printf("%s%n", date);
			date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			checkMonth = date.getMonth();
		}
		System.out.println();
	}
	
	//Print whether or not the given date occurs on Friday the 13th
	private static void fridayThirteenth(int year, int month, int day) {
		LocalDate date = LocalDate.of(year, month, day);	
		if(date.getDayOfMonth() == 13 && date.getDayOfWeek() == DayOfWeek.FRIDAY)
			System.out.println(date.toString() + " occurs on Friday the 13th.");
		else
			System.out.println(date.toString() + " does not occur on Friday the 13th.");
	}
		
}
