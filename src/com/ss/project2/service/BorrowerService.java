package com.ss.project2.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.project2.dao.AuthorDAO;
import com.ss.project2.dao.BookCopyDAO;
import com.ss.project2.dao.BorrowerDao;
import com.ss.project2.dao.LibraryBranchDAO;
import com.ss.project2.entity.Author;
import com.ss.project2.entity.Book;
import com.ss.project2.entity.Entity;

public class BorrowerService {
	
	private ConnectionUtility connectionUtility;
	
	public BorrowerService() {
		this.connectionUtility = new ConnectionUtility();
	}
	
	public List<Entity> getBooks(Integer branchId) {
		try (Connection connection = this.connectionUtility.getConnection()) {
			BookCopyDAO bookCopyDAO = new BookCopyDAO(connection);
			return bookCopyDAO.getBookCopiesByBranchId(branchId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Entity> getBorrowers(Integer cardNo) {
		try (Connection connection = this.connectionUtility.getConnection()) {
			BorrowerDao borrowerDAO = new BorrowerDao(connection);
			return borrowerDAO.getBorrowersByCardNo(cardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Entity> getLibraryBranches() {
		try (Connection connection = this.connectionUtility.getConnection()) {
			LibraryBranchDAO libraryBranchDAO = new LibraryBranchDAO(connection);
			return libraryBranchDAO.getLibraryBranches();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void saveAuthor(Author author) throws SQLException {
		Connection connection = null;
		try {
			connection = this.connectionUtility.getConnection();
			AuthorDAO authorDAO = new AuthorDAO(connection);
			Integer id = authorDAO.addGetPK(author);// save new author.
			author.setId(id);
			for(Entity book: author.getBookList()){
				authorDAO.insertBookAuthor((Book)book, author);
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
}
