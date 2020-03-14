package com.ss.project2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.project2.entity.Borrower;
import com.ss.project2.entity.Entity;

public class BorrowerDao extends DAO {

	public BorrowerDao(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	public List<Entity> getBorrowers() {
		return this.read("select * from tbl_borrower", null);
	}
	
	public List<Entity> getBorrowersByCardNo(Integer cardNo) {
		return this.read("select * from tbl_borrower where cardNo = ?", new Object[]{ cardNo });
	}
	
	@Override
	public List<Entity> extract(ResultSet resultSet) {
		try {
			List<Entity> borrowerList = new ArrayList<Entity>();
			BookLoanDAO bookLoanDAO = new BookLoanDAO(BorrowerDao.getConnection());
			while(resultSet.next()) {
				Borrower borrower = new Borrower();
				borrower.setCardNo(resultSet.getInt("cardNo"));
				borrower.setName(resultSet.getString("name"));
				borrower.setAddress(resultSet.getString("address"));
				borrower.setPhone(resultSet.getString("phone"));
				borrower.setBookLoanList(bookLoanDAO.readFirstLevel("select * from tbl_book_loans where cardNo = ?", new Object[]{ borrower.getCardNo() }));
				borrowerList.add(borrower);
			}
			return borrowerList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Entity> extractFirstLevel(ResultSet resultSet) {
		try {
			List<Entity> borrowerList = new ArrayList<Entity>();
			while(resultSet.next()) {
				Borrower borrower = new Borrower();
				borrower.setCardNo(resultSet.getInt("cardNo"));
				borrower.setName(resultSet.getString("name"));
				borrower.setAddress(resultSet.getString("address"));
				borrower.setPhone(resultSet.getString("phone"));
				borrowerList.add(borrower);
			}
			return borrowerList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
