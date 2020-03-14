package com.ss.project2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.project2.entity.Author;
import com.ss.project2.entity.Book;
import com.ss.project2.entity.Entity;
import com.ss.project2.entity.Genre;
import com.ss.project2.entity.Publisher;

public class BookDAO extends DAO {
	
	public BookDAO(Connection connection) {
		super(connection);
	}
	
	public void add(Book book) {
		this.save("insert into tbl_book (title) values (?)", new Object[] { book.getTitle() });
	}
	
	public Integer addGetPK(Book book) {
		return this.saveGetKey("insert into tbl_book (title) values (?)", new Object[] { book.getTitle() });
	}
	
	public void update(Book book) {
		this.save("update tbl_book set title = ?, pubId = ? where bookId = ?", new Object[]{ book.getTitle(), book.getPublisher().getId(), book.getId() });
	}
	
	public void delete(Book book) {
		this.save("delete from tbl_book where bookId = ?", new Object[] { book.getId() });
	}
	
	public void insertBookAuthor(Book book, Author author) {
		this.save("insert into tbl_book_authors (bookId, authorId) values(?, ?)", new Object[] { book.getId(), author.getId() });
	}
	
	public void insertBookGenre(Genre genre, Book book) {
		this.save("insert into tbl_book_genres (genreId, bookId) values(?, ?)", new Object[] { genre.getId(), book.getId() });
	}
	
	public List<Entity> getBooks() {
		return this.read("select * from tbl_book", null);
	}
	
	public List<Entity> getBooksByTitle(String title) {
		return this.read("select * from tbl_book where title = ?", new Object[]{ title });
	}
	
	@Override
	public List<Entity> extract(ResultSet resultSet) {
		try {
			List<Entity> bookList = new ArrayList<Entity>();
			PublisherDAO publisherDAO = new PublisherDAO(BookDAO.getConnection());
			AuthorDAO authorDAO = new AuthorDAO(BookDAO.getConnection());
			BookCopyDAO bookCopyDAO = new BookCopyDAO(BookDAO.getConnection());
			BookLoanDAO bookLoanDAO = new BookLoanDAO(BookDAO.getConnection());
			GenreDAO genreDAO = new GenreDAO(BookDAO.getConnection());
			while(resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
				List<Entity> publisherList = publisherDAO.readFirstLevel("select * from tbl_publisher where publisherId = ?", new Object[]{ resultSet.getInt("pubId") });
				if(!publisherList.isEmpty())
					book.setPublisher((Publisher)publisherList.get(0));
				book.setAuthorList(authorDAO.readFirstLevel("select * from tbl_author where authorId in (select authorId from tbl_book_authors where bookId = ?)", new Object[]{ book.getId() }));
				book.setBookCopyList(bookCopyDAO.readFirstLevel("select * from tbl_book_copies where bookId = ?", new Object[]{ book.getId() }));
				book.setBookLoanList(bookLoanDAO.readFirstLevel("select * from tbl_book_loans where bookId = ?", new Object[]{ book.getId() }));
				book.setGenreList(genreDAO.readFirstLevel("select * from tbl_genre where genreId in (select genreId from tbl_book_genres where bookId = ?)", new Object[]{ book.getId() }));
				bookList.add(book);
			}
			return bookList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Entity> extractFirstLevel(ResultSet resultSet) {
		try {
			List<Entity> bookList = new ArrayList<Entity>();
			while(resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
				bookList.add(book);
			}
			return bookList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
