package com.ss.project2;

import java.util.List;
import java.util.Scanner;

import com.ss.project2.entity.Book;
import com.ss.project2.entity.BookLoan;
import com.ss.project2.entity.Borrower;
import com.ss.project2.entity.Entity;
import com.ss.project2.entity.LibraryBranch;
import com.ss.project2.service.BorrowerService;

public class LMS {
	
	public static final Scanner in = new Scanner(System.in);
	
	private static BorrowerService borrowerService = new BorrowerService();
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Brendan Raymond Library Management System, brought to you by SmoothStack.");
		while(true) {
			System.out.println("Which category of a user are you?\n"
							 + "1) Borrower\n"
							 + "2) Librarian\n"
							 + "3) Administrator\n"
							 + "4) None (Exit System)");
			String input = LMS.in.nextLine();
			switch(input) {
				case "1":
					LMS.borrowerMenu();
					break;
				case "2":
					LMS.librarianMenu();
					break;
				case "3":
					LMS.administratorMenu();
					break;
				case "4":
					System.out.println("Thank you for using the Brendan Raymond Library Management System, brought to you by SmoothStack. Goodbye.");
					LMS.in.close();
					return;
				default:
					System.out.println("I'm sorry, the input must be a valid integer between 1 and 4.");
					break;
			}
		}
	}
	
	private static void borrowerMenu() {
		System.out.println("Please input your card number:");
		String input = LMS.in.nextLine();
		try {
			Integer cardNo = Integer.parseInt(input);
			List<Entity> borrowerList = LMS.borrowerService.getBorrowers(cardNo);
			if(borrowerList.isEmpty() || ((Borrower)borrowerList.get(0)).getCardNo() != cardNo) {
				System.out.println("I'm sorry, that card number does not exist.");
				return;
			}
			while(true) {
				System.out.println("What would you like to do?\n"
								 + "1) Check out book\n"
								 + "2) Return book\n"
								 + "3) Nothing (Go back)");
				input = LMS.in.nextLine();
				switch(input) {
					case "1":
						LMS.checkOutLibraryMenu();
						break;
					case "2":
						LMS.returnLibraryMenu();
						break;
					case "3":
						return;
					default:
						System.out.println("I'm sorry, the input must be a valid integer between 1 and 3.");
						break;
				}
			}
		} catch(NumberFormatException e) {
			System.out.println("I'm sorry, the card number must be a valid integer.");
		}
	}
	
	private static void checkOutLibraryMenu() {
		while(true) {
			List<Entity> libraryBranchList = LMS.borrowerService.getLibraryBranches();
			Integer i = 1;
			System.out.println("Which library would you like to check out from?");
			for(Entity libraryBranch : libraryBranchList) {
				System.out.println(i + ") " + ((LibraryBranch)libraryBranch).getName());
				i++;
			}
			System.out.println(i + ") None (Go back)");
			String input = LMS.in.nextLine();
			try {
				Integer option = Integer.parseInt(input);
				if(option == (i - 1)) {
					return;
				} else if(option >= i) {
					System.out.println("I'm sorry, the input be a valid integer between 1 and " + i + ".");
				} else {
					LibraryBranch libraryBranch = (LibraryBranch)libraryBranchList.get(option);
					LMS.checkOutBookMenu(libraryBranch);
				}
			} catch(NumberFormatException e) {
				System.out.println("I'm sorry, the input must be a valid integer.");
			}
		}
	}
	
	private static void checkOutBookMenu(LibraryBranch libraryBranch) {
		while(true) {
			List<Entity> bookList = borrowerService.getBooks(libraryBranch.getId());
			Integer i = 1;
			System.out.println("Please select a book:");
			for(Entity book : bookList) {
				System.out.println(i + ") " + ((Book)book).getTitle());
				i++;
			}
			System.out.println(i + ") None (Go back)");
			String input = LMS.in.nextLine();
			try {
				Integer option = Integer.parseInt(input);
				if(option == (i - 1)) {
					return;
				} else if(option >= i) {
					System.out.println("I'm sorry, the input be a valid integer between 1 and " + i + ".");
				} else {
					Book book = (Book)bookList.get(option);
					BookLoan bookLoan = new BookLoan();
					bookLoan.setBook(book);
				}
			} catch(NumberFormatException e) {
				System.out.println("I'm sorry, the input must be a valid integer.");
			}
		}
	}
	
	private static void returnLibraryMenu() {
		
	}
	
	private static void librarianMenu() {
		
	}
	
	private static void administratorMenu() {
		
	}
	
}
