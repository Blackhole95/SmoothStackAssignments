package com.ss.project1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//Brendan Raymond
//Catalog: CRUD interface between user and Book/Author/Publisher files
public class Catalog {
	
	//Main Method
	public static void main(String[] args) {
		boolean nextMenu;
		int option;
		List<CatalogEntity> bookList;
		List<CatalogEntity> authorList;
		List<CatalogEntity> publisherList;
		String entity;
		String response;
		//Load data from text files if they exist
		System.out.println("Loading data...");
		new File("data").mkdir();
		bookList = Catalog.getBooks();
		authorList = Catalog.getAuthors();
		publisherList = Catalog.getPublishers();
		//Exit if there was a problem loading files
		if(bookList == null || authorList == null || publisherList == null)
			return;
		//Main Menu
		try (Scanner in = new Scanner(System.in)) {
			nextMenu = false;
			option = 0;
			entity = null;
			while(option < 1 || option > 4) {
				System.out.println("What would you like to look at?\n" +
								   "1) Books\n" +
								   "2) Authors\n" +
								   "3) Publishers\n" +
								   "4) [Exit System]");
				try {
					//Check if input is an integer
					response = in.nextLine();
					option = Integer.parseInt(response);
					//Make decision
					switch(option) {
						case 1:
							nextMenu = true;
							entity = "Book";
							break;
						case 2:
							nextMenu = true;
							entity = "Author";
							break;
						case 3:
							nextMenu = true;
							entity = "Publisher";
							break;
						case 4:
							System.out.println("Goodbye.");
							break;
						default:
							System.out.println("I'm sorry, the input must be an integer between 1 and 4.");
							break;
					}
					//Next Menu
					if(nextMenu) {
						if(option == 1) {
							//Book Menu
							option = 0;
							while(option < 1 || option > 5) {
								System.out.println("What would you like to do?\n" +
												   "1) Add Book\n" +
												   "2) Find Book\n" +
												   "3) Update Book\n" +
												   "4) Delete Book\n" +
												   "5) Go Back");
								response = in.nextLine();
								try {
									option = Integer.parseInt(response);
									switch(option) {
										case 1:
											Catalog.create(bookList, authorList, publisherList, in);
											option = 0;
											break;
										case 2:
											Catalog.read(entity, bookList, in);
											option = 0;
											break;
										case 3:
											Catalog.update(entity, bookList, in);
											option = 0;
											break;
										case 4:
											Catalog.delete(entity, bookList, authorList, publisherList, in);
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
						} else if(option == 2) {
							//Author Menu
							option = 0;
							while(option < 1 || option > 4) {
								System.out.println("What would you like to do?\n" +
												   "1) Find Author\n" +
												   "2) Update Author\n" +
												   "3) Delete Author\n" +
												   "4) Go Back");
								response = in.nextLine();
								try {
									option = Integer.parseInt(response);
									switch(option) {
										case 1:
											Catalog.read(entity, authorList, in);
											option = 0;
											break;
										case 2:
											Catalog.update(entity, authorList, in);
											option = 0;
											break;
										case 3:
											Catalog.delete(entity, bookList, authorList, publisherList, in);
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
						} else {
							//Publisher Menu
							option = 0;
							while(option < 1 || option > 4) {
								System.out.println("What would you like to do?\n" +
												   "1) Find Publisher\n" +
												   "2) Update Publisher\n" +
												   "3) Delete Publisher\n" +
												   "4) Go Back");
								response = in.next();
								try {
									option = Integer.parseInt(response);
									switch(option) {
										case 1:
											Catalog.read(entity, publisherList, in);
											option = 0;
											break;
										case 2:
											Catalog.update(entity, publisherList, in);
											option = 0;
											break;
										case 3:
											Catalog.delete(entity, bookList, authorList, publisherList, in);
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
						option = 0;
					}
					nextMenu = false;
				} catch(NumberFormatException e) {
					System.out.println("I'm sorry, the input must be a valid integer.");
				}
			}
		}
		//Save data and exit
		Catalog.setBooks(bookList);
		Catalog.setAuthors(authorList);
		Catalog.setPublishers(publisherList);
	}
	
	//Load books from text file if it exists, else return empty list
	private static List<CatalogEntity> getBooks() {
		ArrayList<CatalogEntity> bookList;
		ArrayList<String> entries;
		File file;
		String line;
		String[] data;
		file = new File("data/book.txt");
		try {
			if(file.createNewFile()) {
				//No file to read to read from
				return new ArrayList<CatalogEntity>();
			} else {
				//Each item recorded as line of text in text file
				entries = new ArrayList<String>();
				try(BufferedReader bufferedReader = new BufferedReader(new FileReader("data/book.txt"))) {
					while((line = bufferedReader.readLine()) != null) {
						entries.add(line);
					}
				}
				if(entries.isEmpty()) {
					//No lines to read from
					return new ArrayList<CatalogEntity>();
				} else {
					//Create and add items from data
					bookList = new ArrayList<CatalogEntity>();
					for(String entry : entries) {
						data = entry.split(",");
						bookList.add(new Book(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
					}
					return bookList;
				}
			}
		} catch (IOException e) {
			//Shouldn't get here
			System.out.println("Something went wrong.");;
		}
		return null;
	}
	
	//Load authors from text file if it exists, else return empty list
	//TODO: Combine with getBooks
	private static List<CatalogEntity> getAuthors() {
		ArrayList<CatalogEntity> authorList;
		ArrayList<String> entries;
		File file;
		String line;
		String[] data;
		file = new File("data/author.txt");
		try {
			if(file.createNewFile()) {
				return new ArrayList<CatalogEntity>();
			} else {
				entries = new ArrayList<String>();
				try(BufferedReader bufferedReader = new BufferedReader(new FileReader("data/author.txt"))) {
					while((line = bufferedReader.readLine()) != null) {
						entries.add(line);
					}
				}
				if(entries.isEmpty()) {
					return new ArrayList<CatalogEntity>();
				} else {
					authorList = new ArrayList<CatalogEntity>();
					for(String entry : entries) {
						data = entry.split(",");
						authorList.add(new Author(Integer.parseInt(data[0]), data[1]));
					}
					return authorList;
				}
			}
		} catch (IOException e) {
			System.out.println("Something went wrong.");;
		}
		return null;
	}
	
	//Load publishers from text file if it exists, else return empty list
	//TODO: Combine with getBooks
	private static List<CatalogEntity> getPublishers() {
		ArrayList<CatalogEntity> publisherList;
		ArrayList<String> entries;
		File file;
		String line;
		String[] data;
		file = new File("data/publisher.txt");
		try {
			if(file.createNewFile()) {
				return new ArrayList<CatalogEntity>();
			} else {
				entries = new ArrayList<String>();
				try(BufferedReader bufferedReader = new BufferedReader(new FileReader("data/publisher.txt"))) {
					while((line = bufferedReader.readLine()) != null) {
						entries.add(line);
					}
				}
				if(entries.isEmpty()) {
					return new ArrayList<CatalogEntity>();
				} else {
					publisherList = new ArrayList<CatalogEntity>();
					for(String entry : entries) {
						data = entry.split(",");
						publisherList.add(new Publisher(Integer.parseInt(data[0]), data[1], data[2]));
					}
					return publisherList;
				}
			}
		} catch (IOException e) {
			System.out.println("Something went wrong.");;
		}
		return null;
	}
	
	//Store book list in text file memory
	private static void setBooks(List<CatalogEntity> bookList) {
		Book book;
		File file;
		file = new File("data/book.txt");
		try {
			//Replace previous file
			file.delete();
			file.createNewFile();
			//Store each item as line of data, fields separated by commas
			try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/book.txt"))) {
				for(CatalogEntity item : bookList) {
					book = (Book)item;
					bufferedWriter.write(book.getID() + "," + book.getName() + "," + book.getAuthorID() + "," + book.getPublisherID() + "\n");;
				}
			}
		} catch (IOException e) {
			System.out.println("Something went wrong.");;
		}
	}
	
	//Store author list in text file memory
	//TODO: Combine with setBooks
	private static void setAuthors(List<CatalogEntity> authorList) {
		Author author;
		File file;
		file = new File("data/author.txt");
		try {
			file.delete();
			file.createNewFile();
			try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/author.txt"))) {
				for(CatalogEntity item : authorList) {
					author = (Author)item;
					bufferedWriter.write(author.getID() + "," + author.getName() + "\n");;
				}
			}
		} catch (IOException e) {
			System.out.println("Something went wrong.");;
		}
	}
	
	//Store publisher list in text file memory
	//TODO: Combine with setBooks
	private static void setPublishers(List<CatalogEntity> publisherList) {
		Publisher publisher;
		File file;
		file = new File("data/publisher.txt");
		try {
			file.delete();
			file.createNewFile();
			try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/publisher.txt"))) {
				for(CatalogEntity item : publisherList) {
					publisher = (Publisher)item;
					bufferedWriter.write(publisher.getID() + "," + publisher.getName() + "," + publisher.getAddress() + "\n");;
				}
			}
		} catch (IOException e) {
			System.out.println("Something went wrong.");;
		}
	}
	
	//Create book if is not already in book list, else do nothing
	//Create author and publisher as needed
	//Add all new items to respective lists
	private static void create(List<CatalogEntity> bookList, List<CatalogEntity> authorList, List<CatalogEntity> publisherList, Scanner in) {
		int bookID;
		int authorID;
		int publisherID;
		List<CatalogEntity> bookFilter;
		List<CatalogEntity> authorFilter;
		List<CatalogEntity> publisherFilter;
		String book;
		String author;
		String publisher;
		String address;
		//Query for book information
		System.out.println("Enter Book Name:");
		book = in.nextLine();
		System.out.println("Enter Author Name:");
		author = in.nextLine();
		System.out.println("Enter Publisher Name:");
		publisher = in.nextLine();
		//TODO: Query address only after existence of publisher has been checked
		System.out.println("Enter Publisher Address:");
		address = in.nextLine();
		//Create Author
		authorFilter = authorList.stream().filter(item -> item.getName().equalsIgnoreCase(author)).collect(Collectors.toList());
		if(authorFilter.isEmpty()) {
			if(authorList.isEmpty())
				authorID = 1;
			else
				authorID = authorList.get(authorList.size() - 1).getID() + 1;
			authorList.add(new Author(authorID, author));
		} else {
			authorID = authorFilter.get(0).getID();
		}
		//Create Publisher
		publisherFilter = publisherList.stream().filter(item -> item.getName().equalsIgnoreCase(publisher)).collect(Collectors.toList());
		publisherFilter = publisherFilter.stream().filter(item -> ((Publisher)item).getAddress().equalsIgnoreCase(address)).collect(Collectors.toList());
		if(publisherFilter.isEmpty()) {
			if(publisherList.isEmpty())
				publisherID = 1;
			else
				publisherID = publisherList.get(publisherList.size() - 1).getID() + 1;
			publisherList.add(new Publisher(publisherID, publisher, address));
		} else {
			publisherID = publisherFilter.get(0).getID();
		}
		//Create Book
		bookFilter = bookList.stream().filter(item -> item.getName().equalsIgnoreCase(book)).collect(Collectors.toList());
		bookFilter = bookFilter.stream().filter(item -> ((Book)item).getAuthorID() == authorID).collect(Collectors.toList());
		bookFilter = bookFilter.stream().filter(item -> ((Book)item).getPublisherID() == publisherID).collect(Collectors.toList());
		if(bookFilter.isEmpty()) {
			if(bookList.isEmpty())
				bookID = 1;
			else
				bookID = bookList.get(bookList.size() - 1).getID() + 1;
			bookList.add(new Book(bookID, book, authorID, publisherID));
			System.out.println("New Book Added.");
		} else {
			System.out.println("I'm sorry, this book already exists in the Catalog.");
		}
	}
	
	//Create list of items matching input name or id, print information
	private static void read(String entity, List<CatalogEntity> list, Scanner in) {
		int id;
		List<CatalogEntity> filter;
		String name;
		System.out.println("Enter " + entity + "Name or ID:");
		name = in.nextLine();
		try {
			id = Integer.parseInt(name);
			filter = list.stream().filter(item -> item.getID() == id).collect(Collectors.toList());
		} catch(NumberFormatException e) {
			filter = list.stream().filter(item -> item.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
		}
		if(filter.isEmpty()) {
			System.out.println("I'm sorry, that book does not exist in the system.");
		} else {
			for(CatalogEntity item : filter) {
				System.out.println(item.toString());
			}
		}
	}
	
	//Find item matching input id, update information if it exists
	private static void update(String entity, List<CatalogEntity> list, Scanner in) {
		int option;
		CatalogEntity item;
		List<CatalogEntity> filter;
		String name;
		String newName;
		String response;
		option = 0;
		while(option == 0) {
			try {
				System.out.println("Enter " + entity + "ID:");
				name = in.nextLine();
				int id = Integer.parseInt(name);
				filter = list.stream().filter(e -> e.getID() == id).collect(Collectors.toList());
				if(filter.isEmpty()) {
					System.out.println("I'm sorry, no book with that ID exists in the system.");
				} else {
					item = filter.get(0);
					System.out.println(item.toString());
					option = 0;
					switch(entity) {
						case "Book":
							while(option < 1 || option > 2) {
								System.out.println("What would you like to edit?\n" +
										   "1) Name\n" +
										   "2) Nothing");
								response = in.nextLine();
								try {
									option = Integer.parseInt(response);
									switch(option) {
										case 1:
											System.out.println("Enter New Name:");
											newName = in.nextLine();
											item.setName(newName);
											break;
										case 2:
											return;
										default:
											System.out.println("I'm sorry, the input must be an integer between 1 and 2.");
											break;
									}
								} catch(NumberFormatException e) {
									System.out.println("I'm sorry, the input must be a valid integer.");
								}
							}
							break;
						case "Author":
							while(option < 1 || option > 2) {
								System.out.println("What would you like to edit?\n" +
										   "1) Name\n" +
										   "2) Nothing");
								response = in.nextLine();
								try {
									option = Integer.parseInt(response);
									switch(option) {
										case 1:
											System.out.println("Enter New Name:");
											newName = in.nextLine();
											item.setName(newName);
											break;
										case 2:
											return;
										default:
											System.out.println("I'm sorry, the input must be an integer between 1 and 2.");
											break;
									}
								} catch(NumberFormatException e) {
									System.out.println("I'm sorry, the input must be a valid integer.");
								}
							}
							break;
						case "Publisher":
							while(option < 1 || option > 2) {
								System.out.println("What would you like to edit?\n" +
										   "1) Name\n" +
										   "2) Nothing");
								response = in.nextLine();
								try {
									option = Integer.parseInt(response);
									switch(option) {
										case 1:
											System.out.println("Enter New Name:");
											newName = in.nextLine();
											item.setName(newName);
											break;
										case 2:
											return;
										default:
											System.out.println("I'm sorry, the input must be an integer between 1 and 2.");
											break;
									}
								} catch(NumberFormatException e) {
									System.out.println("I'm sorry, the input must be a valid integer.");
								}
							}
							break;
						default:
							break;
					}
				}
			} catch(NumberFormatException e) {
				System.out.println("I'm sorry, the input ID must be a valid integer.");
			}
		}
	}
	
	//Delete item from catalog if it exists, else do nothing
	//Delete author and publisher if no more books exist for either
	//Delete all associated books when deleting author or publisher
	private static void delete(String entity, List<CatalogEntity> bookList, List<CatalogEntity> authorList, List<CatalogEntity> publisherList, Scanner in) {
		CatalogEntity deletable;
		List<CatalogEntity> filter;
		String name;
		System.out.println("Enter " + entity + "ID:");
		name = in.nextLine();
		try {
			switch(entity) {
				case "Book":
					int bookID = Integer.parseInt(name);
					filter = bookList.stream().filter(item -> item.getID() == bookID).collect(Collectors.toList());
					if(filter.isEmpty()) {
						System.out.println("I'm sorry, that book does not exist in the system.");
					} else {
						deletable = filter.get(0);
						bookList.remove(deletable);
						int authorID = ((Book)deletable).getAuthorID();
						int publisherID = ((Book)deletable).getPublisherID();
						filter = bookList.stream().filter(item -> ((Book)item).getAuthorID() == authorID).collect(Collectors.toList());
						if(filter.isEmpty()) {
							filter = authorList.stream().filter(item -> item.getID() == authorID).collect(Collectors.toList());
							authorList.remove(filter.get(0));
						}
						filter = bookList.stream().filter(item -> ((Book)item).getPublisherID() == publisherID).collect(Collectors.toList());
						if(filter.isEmpty()) {
							filter = publisherList.stream().filter(item -> item.getID() == publisherID).collect(Collectors.toList());
							publisherList.remove(filter.get(0));
						}
					}
					break;
				case "Author":
					int authorID = Integer.parseInt(name);
					filter = authorList.stream().filter(item -> item.getID() == authorID).collect(Collectors.toList());
					if(filter.isEmpty()) {
						System.out.println("I'm sorry, that author does not exist in the system.");
					} else {
						deletable = filter.get(0);
						authorList.remove(deletable);
						filter = bookList.stream().filter(item -> ((Book)item).getAuthorID() == authorID).collect(Collectors.toList());
						if(!filter.isEmpty()) {
							for(CatalogEntity item : filter) {
								bookList.remove(item);
							}
						}
					}
					break;
				case "Publisher":
					int publisherID = Integer.parseInt(name);
					filter = publisherList.stream().filter(item -> item.getID() == publisherID).collect(Collectors.toList());
					if(filter.isEmpty()) {
						System.out.println("I'm sorry, that publisher does not exist in the system.");
					} else {
						deletable = filter.get(0);
						publisherList.remove(deletable);
						filter = bookList.stream().filter(item -> ((Book)item).getPublisherID() == publisherID).collect(Collectors.toList());
						if(!filter.isEmpty()) {
							for(CatalogEntity item : filter) {
								bookList.remove(item);
							}
						}
					}
					break;
			}
		} catch(NumberFormatException e) {
			System.out.println("I'm sorry, the input ID must be a valid integer.");
		}
	}
	
}
