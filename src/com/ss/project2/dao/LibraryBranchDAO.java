package com.ss.project2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ss.project2.entity.Entity;

public class LibraryBranchDAO extends DAO{

	public LibraryBranchDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	List<Entity> extract(ResultSet resultSet) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	List<Entity> extractFirstLevel(ResultSet resultSet) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Entity> getLibraryBranches() {
		// TODO Auto-generated method stub
		return null;
	}

}
