package com.ss.project2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.ss.project2.entity.Entity;

public class BookCopyDAO extends DAO {
	
	public BookCopyDAO(Connection connection) {
		super(connection);
	}
	
	public List<Entity> getBookCopiesByBranchId(Integer branchId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Entity> extract(ResultSet resultSet) {
		return null;
	}
	
	@Override
	public List<Entity> extractFirstLevel(ResultSet resultSet) {
		return null;
	}

}
