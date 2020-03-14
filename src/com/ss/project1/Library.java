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
//Data representation of Library Management System
class Library {
	
	private List<Item> bookList;
	private List<Item> authorList;
	private List<Item> publisherList;
	
	public Library() {
		this.getData();
	}
	
	public void getData() {
		new File("data").mkdir();
		this.bookList = this.getList("data/book.txt");
		this.authorList = this.getList("data/author.txt");
		this.publisherList = this.getList("data/publisher.txt");
	}
	
	private List<Item> getList(String path) {
		File file = new File(path);
		try {
			if(file.createNewFile()) {
				System.out.println("Library could not be loaded. Initializing empty library.");
				return new ArrayList<Item>();
			} else {
				ArrayList<String> entries = new ArrayList<String>();
				try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
					String line = bufferedReader.readLine();
					while(line != null) {
						entries.add(line);
						line = bufferedReader.readLine();
					}
				}
				if(entries.isEmpty()) {
					System.out.println("Library could not be loaded. Initializing empty library.");
					return new ArrayList<Item>();
				} else {
					List<Item> list = new ArrayList<Item>();
					for(String entry : entries) {
						String[] data = entry.split(",");
						switch(path) {
							case "data/book.txt":
								list.add(new Book(data));
								break;
							case "data/author.txt":
								list.add(new Author(data));
								break;
							case "data/publisher.txt":
								list.add(new Publisher(data));
								break;
							default:
								break;
						}
						list.add(new Book(data));
					}
					return list;
				}
			}
		} catch (IOException e) {
			System.out.println("Library could not be loaded. Initializing empty library.");
		}
		return new ArrayList<Item>();
	}
	
	public void setData() {
		this.setList("data/book.txt");
		this.setList("data/author.txt");
		this.setList("data/publisher.txt");
	}
	
	public void setList(String path) {
		File file = new File(path);
		try {
			file.delete();
			file.createNewFile();
			try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
				switch(path) {
					case "data/book.txt":
						for(Item item : this.bookList) {
							Book book = (Book)item;
							bufferedWriter.write(book.getID() + "," + book.getName() + "," + book.getAuthorID() + "," + book.getPublisherID() + "\n");
						}
						break;
					case "data/author.txt":
						for(Item item : this.authorList) {
							Author author = (Author)item;
							bufferedWriter.write(author.getID() + "," + author.getName() + "\n");
						}
						break;
					case "data/publisher.txt":
						for(Item item : this.publisherList) {
							Publisher publisher = (Publisher)item;
							bufferedWriter.write(publisher.getID() + "," + publisher.getName() + "," + publisher.getAddress() + "\n");
						}
						break;
					default:
						break;
				}
			}
		} catch (IOException e) {
			System.out.println("Library could not be saved.");;
		}
	}
	
	public void add(ItemType itemType) {
		switch(itemType) {
		case Book:
			this.addBook();
			break;
		default:
			break;
		}
	}
	
	private void addBook() {
		try (Scanner in = new Scanner(System.in)) {
			int bookID;
			int authorID;
			int publisherID;
			System.out.println("Enter Book Name:");
			String book = in.nextLine();
			System.out.println("Enter Author Name:");
			String author = in.nextLine();
			System.out.println("Enter Publisher Name:");
			String publisher = in.nextLine();
			List<Item> filter = this.authorList
					.stream()
					.filter(item -> item.getName().equalsIgnoreCase(author))
					.collect(Collectors.toList());
			if(filter.isEmpty()) {
				if(this.authorList.isEmpty()) {
					authorID = 1;
				} else {
					authorID = this.authorList.get(this.authorList.size() - 1).getID() + 1;
				}
				this.authorList.add(new Author(authorID, author));
			} else {
				authorID = filter.get(0).getID();
			}
			filter = this.publisherList
					.stream()
					.filter(item -> item.getName().equalsIgnoreCase(publisher))
					.collect(Collectors.toList());
			if(filter.isEmpty()) {
				if(this.publisherList.isEmpty()) {
					publisherID = 1;
				} else {
					publisherID = this.publisherList.get(this.publisherList.size() - 1).getID() + 1;
				}
				this.publisherList.add(new Publisher(publisherID, publisher, ""));
			} else {
				publisherID = filter.get(0).getID();
			}
			filter = this.bookList
					.stream()
					.filter(item -> item.getName().equalsIgnoreCase(book))
					.filter(item -> ((Book)item).getAuthorID() == authorID)
					.filter(item -> ((Book)item).getPublisherID() == publisherID)
					.collect(Collectors.toList());
			if(filter.isEmpty()) {
				if(this.bookList.isEmpty()) {
					bookID = 1;
				} else {
					bookID = bookList.get(bookList.size() - 1).getID() + 1;
				}
				bookList.add(new Book(bookID, book, authorID, publisherID));
				System.out.println("New Book Added.");
			} else {
				System.out.println("I'm sorry, this book already exists in the Catalog.");
			}
		}
	}
	
	private List<Item> switchList(ItemType itemType) {
		List<Item> list;
		switch(itemType) {
			case Book:
				list = this.bookList;
				break;
			case Author:
				list = this.authorList;
				break;
			case Publisher:
				list = this.publisherList;
				break;
			default:
				list = null;
		}
		return list;
	}
	
	public void find(ItemType itemType) {
		int id;
		List<Item> list = this.switchList(itemType);;
		try (Scanner in = new Scanner(System.in)) { 
			System.out.println("Enter " + itemType.toString() + "Name or ID:");
			String name = in.nextLine();
			List<Item> filter;
			try {
				id = Integer.parseInt(name);
				filter = list
						.stream()
						.filter(item -> item.getID() == id)
						.collect(Collectors.toList());
			} catch(NumberFormatException e) {
				filter = list
						.stream()
						.filter(item -> item.getName().equalsIgnoreCase(name))
						.collect(Collectors.toList());
			}
			if(filter.isEmpty()) {
				System.out.println("I'm sorry, that book does not exist in the system.");
			} else {
				for(Item item : filter) {
					System.out.println(item.toString());
				}
			}
		}
	}
	
	public void update(ItemType itemType) {
		List<Item> list = this.switchList(itemType);
		try (Scanner in = new Scanner(System.in)) { 
			int option = 0;
			while(option == 0) {
				try {
					System.out.println("Enter " + itemType.toString() + "ID:");
					String response = in.nextLine();
					int id = Integer.parseInt(response);
					List<Item> filter = list
							.stream()
							.filter(item -> item.getID() == id)
							.collect(Collectors.toList());
					if(filter.isEmpty()) {
						System.out.println("I'm sorry, no book with that ID exists in the system.");
					} else {
						Item item = filter.get(0);
						System.out.println(item.toString());
						option = 0;
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
										response = in.nextLine();
										item.setName(response);
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
					}
				} catch(NumberFormatException e) {
					System.out.println("I'm sorry, the input ID must be a valid integer.");
				}
			}
		}
	}
	
	public void delete(ItemType itemType) {
		int bookId;
		int authorId;
		int publisherId;
		Item item;
		List<Item> filter;
		try (Scanner in = new Scanner(System.in)) { 
			System.out.println("Enter " + itemType.toString() + "ID:");
			String response = in.nextLine();
			try {
				switch(itemType) {
					case Book:
						bookId = Integer.parseInt(response);
						filter = this.bookList
								.stream()
								.filter(i -> i.getID() == bookId)
								.collect(Collectors.toList());
						if(filter.isEmpty()) {
							System.out.println("I'm sorry, that book does not exist in the system.");
						} else {
							item = filter.get(0);
							this.bookList.remove(item);
							authorId = ((Book)item).getAuthorID();
							publisherId = ((Book)item).getPublisherID();
							filter = this.bookList.
									stream().
									filter(i -> ((Book)i).getAuthorID() == authorId)
									.collect(Collectors.toList());
							if(filter.isEmpty()) {
								filter = this.authorList
										.stream()
										.filter(i -> i.getID() == authorId)
										.collect(Collectors.toList());
								this.authorList.remove(filter.get(0));
							}
							filter = this.bookList
									.stream()
									.filter(i -> ((Book)i).getPublisherID() == publisherId)
									.collect(Collectors.toList());
							if(filter.isEmpty()) {
								filter = this.publisherList
										.stream()
										.filter(i -> i.getID() == publisherId)
										.collect(Collectors.toList());
								this.publisherList.remove(filter.get(0));
							}
						}
						break;
					case Author:
						authorId = Integer.parseInt(response);
						filter = this.authorList
								.stream()
								.filter(i -> i.getID() == authorId)
								.collect(Collectors.toList());
						if(filter.isEmpty()) {
							System.out.println("I'm sorry, that author does not exist in the system.");
						} else {
							item = filter.get(0);
							this.authorList.remove(item);
							filter = this.bookList
									.stream()
									.filter(i -> ((Book)item).getAuthorID() == authorId)
									.collect(Collectors.toList());
							if(!filter.isEmpty()) {
								for(Item i : filter) {
									this.bookList.remove(i);
								}
							}
						}
						break;
					case Publisher:
						publisherId = Integer.parseInt(response);
						filter = this.publisherList
								.stream()
								.filter(i -> i.getID() == publisherId)
								.collect(Collectors.toList());
						if(filter.isEmpty()) {
							System.out.println("I'm sorry, that publisher does not exist in the system.");
						} else {
							item = filter.get(0);
							this.publisherList.remove(item);
							filter = this.bookList.
									stream()
									.filter(i -> ((Book)item).getPublisherID() == publisherId)
									.collect(Collectors.toList());
							if(!filter.isEmpty()) {
								for(Item i : filter) {
									this.bookList.remove(i);
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
	
}
