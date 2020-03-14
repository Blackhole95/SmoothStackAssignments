package com.ss.project1;

import java.util.Scanner;

//Brendan Raymond
//Catalog: CRUD interface between user and Book/Author/Publisher files
class PublicLibrary {
	
	public static void main(String[] args) {
		System.out.println("Loading data...");
		Library library = new Library();
		try (Scanner in = new Scanner(System.in)) {
			int option = 0;
			String response = null;
			while(option < 1 || option > 4) {
				System.out.println("What would you like to look at?\n" +
								   "1) Books\n" +
								   "2) Authors\n" +
								   "3) Publishers\n" +
								   "4) [Exit System]");
				try {
					response = in.nextLine();
					option = Integer.parseInt(response);
					switch(option) {
						case 1:
							PublicLibrary.independentMenu(ItemType.Book, library);
							break;
						case 2:
							PublicLibrary.dependentMenu(ItemType.Author, library);
							break;
						case 3:
							PublicLibrary.dependentMenu(ItemType.Publisher, library);
							break;
						case 4:
							break;
						default:
							System.out.println("I'm sorry, the input must be an integer between 1 and 4.");
							break;
					}
				} catch(NumberFormatException e) {
					System.out.println("I'm sorry, the input must be a valid integer.");
				}
			}
		}
		System.out.println("Loading data...");
		library.setData();
		System.out.println("Goodbye.");
	}
	
	public static void independentMenu(ItemType itemType, Library library) {
		int option = 0;
		try (Scanner in = new Scanner(System.in)) {
			while(option < 1 || option > 5) {
				System.out.println("What would you like to do?\n" +
								   "1) Add " + itemType.toString() + "\n" +
								   "2) Find " + itemType.toString() + "\n" +
								   "3) Update " + itemType.toString() + "\n" +
								   "4) Delete " + itemType.toString() + "\n" +
						   		   "5) Go Back");
				String response = in.nextLine();
				try {
					option = Integer.parseInt(response);
					switch(option) {
						case 1:
							library.add(itemType);
							option = 0;
							break;
						case 2:
							library.find(itemType);
							option = 0;
							break;
						case 3:
							library.update(itemType);
							option = 0;
							break;
						case 4:
							library.delete(itemType);
							option = 0;
							break;
						case 5:
							System.out.println("Returning to Main Menu.");
							break;
						default:
							System.out.println("I'm sorry, the input must be an integer between 1 and 5.");
							break;
					}
				} catch(NumberFormatException e) {
					System.out.println("I'm sorry, the input must be a valid integer.");
				}
			}
		}
	}
	
	public static void dependentMenu(ItemType itemType, Library library) {
		int option = 0;
		try (Scanner in = new Scanner(System.in)) {
			while(option < 1 || option > 4) {
				System.out.println("What would you like to do?\n" +
								   "1) Find " + itemType.toString() + "\n" +
								   "2) Update " + itemType.toString() + "\n" +
								   "3) Delete " + itemType.toString() + "\n" +
								   "4) Go Back");
				String response = in.nextLine();
				try {
					option = Integer.parseInt(response);
					switch(option) {
						case 1:
							library.find(itemType);
							option = 0;
							break;
						case 2:
							library.update(itemType);
							option = 0;
							break;
						case 3:
							library.delete(itemType);
							option = 0;
							break;
						case 4:
							System.out.println("Returning to Main Menu.");
							break;
						default:
							System.out.println("I'm sorry, the input must be an integer between 1 and 4.");
							break;
					}
				} catch(NumberFormatException e) {
					System.out.println("I'm sorry, the input must be a valid integer.");
				}
			}
		}
	}
		
}
