package com.ss.project2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.project2.entity.Author;
import com.ss.project2.entity.Book;
import com.ss.project2.entity.Entity;

public class AuthorDAO extends DAO {
	
	public AuthorDAO(Connection connection) {
		super(connection);
	}
	
	public void add(Author author) {
		this.save("insert into tbl_author (authorName) values (?)", new Object[] { author.getName() });
	}
	
	public Integer addGetPK(Author author) {
		return this.saveGetKey("insert into tbl_author (authorName) values (?)", new Object[] { author.getName() });
	}
	
	public void update(Author author) {
		this.save("update tbl_author set authorName = ? where authorId = ?", new Object[]{ author.getName(), author.getId() });
	}
	
	public void delete(Author author) {
		this.save("delete from tbl_author where authorId = ?", new Object[] { author.getId() });
	}
	
	public void insertBookAuthor(Book book, Author author) {
		this.save("insert into tbl_book_authors (bookId, authorId) values(?, ?)", new Object[] { book.getId(), author.getId() });
	}
	
	public List<Entity> getAuthors() {
		return this.read("select * from tbl_authors", null);
	}
	
	public List<Entity> getAuthorsByName(String name) {
		return this.read("select * from tbl_authors where authorName = ?", new Object[]{ name });
	}
	
	@Override
	public List<Entity> extract(ResultSet resultSet) {
		try {
			List<Entity> authorList = new ArrayList<Entity>();
			BookDAO bookDAO = new BookDAO(AuthorDAO.getConnection());
			while(resultSet.next()) {
				Author author = new Author();
				author.setId(resultSet.getInt("authorId"));
				author.setName(resultSet.getString("authorName"));
				author.setBookList(bookDAO.readFirstLevel("select * from tbl_book where bookId in (select bookId from tbl_book_authors where authorId = ?)", new Object[]{ author.getId() }));
				authorList.add(author);
			}
			return authorList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Entity> extractFirstLevel(ResultSet resultSet) {
		try {
			List<Entity> authorList = new ArrayList<Entity>();
			while(resultSet.next()) {
				Author author = new Author();
				author.setId(resultSet.getInt("authorId"));
				author.setName(resultSet.getString("authorName"));
				authorList.add(author);
			}
			return authorList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
