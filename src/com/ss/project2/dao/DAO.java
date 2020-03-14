package com.ss.project2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.ss.project2.entity.Entity;

public abstract class DAO {
	
	private static Connection connection = null;
	
	public DAO(Connection connection){
		DAO.connection = connection;
	}
	
	abstract List<Entity> extract(ResultSet resultSet) throws SQLException, ClassNotFoundException;
	
	abstract List<Entity> extractFirstLevel(ResultSet resultSet) throws SQLException, ClassNotFoundException;
	
	protected static Connection getConnection() {
		return DAO.connection;
	}
	
	protected void save(String query, Object[] values) {
		try {
			PreparedStatement statement = DAO.connection.prepareStatement(query);
			if(values != null) {
				Integer index = 1;
				for(Object object: values) {
					statement.setObject(index++, object);
				}
			}
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected Integer saveGetKey(String query, Object[] values) {
		try {
			PreparedStatement statement = DAO.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			if(values != null) {
				Integer index = 1;
				for(Object object: values) {
					statement.setObject(index++, object);
				}
			}
			statement.executeUpdate();
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected List<Entity> read(String query, Object[] values) {
		try {
			PreparedStatement statement = DAO.connection.prepareStatement(query);
			if(values != null) {
				Integer index = 1;
				for(Object object: values) {
					statement.setObject(index++, object);
				}
			}
			ResultSet resultSet = statement.executeQuery();
			return this.extract(resultSet);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected List<Entity> readFirstLevel(String query, Object[] values) {
		try {
			PreparedStatement statement = DAO.connection.prepareStatement(query);
			if(values != null) {
				Integer index = 1;
				for(Object object: values) {
					statement.setObject(index++, object);
				}
			}
			ResultSet resultSet = statement.executeQuery();
			return this.extractFirstLevel(resultSet);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
