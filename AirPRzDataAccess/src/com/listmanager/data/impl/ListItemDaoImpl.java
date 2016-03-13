package com.listmanager.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.listmanager.data.ListItemDao;
import com.listmanager.data.util.DbConnector;
import com.listmanager.data.util.JdbcCloses;
import com.listmanager.model.ListItem;

public class ListItemDaoImpl implements ListItemDao{
	
	
	@Override
	public ListItem getListItemById(Long listItemId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		ListItem listItem = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT LI.USER_ID, LI.VALUE "
					+ "FROM TEST.LIST_ITEM LI "
					+ "WHERE LI.ID = ?");
			stmt.setLong(1, listItemId);
			
			rs = stmt.executeQuery();
			if (rs.next()) {
				listItem = new ListItem();
				listItem.setId(listItemId);
				listItem.setUserId(rs.getLong("USER_ID"));
				listItem.setValue(rs.getString("VALUE"));
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(rs);
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return listItem;
	}
	@Override
	public List<ListItem> getListItemsByUserId(Long userId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		List<ListItem> listItems = new ArrayList<ListItem>();
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT LI.ID, LI.VALUE "
					+ "FROM TEST.LIST_ITEM LI "
					+ "WHERE LI.USER_ID = ?");
			stmt.setLong(1, userId);
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				ListItem listItem = new ListItem();
				listItem.setId(rs.getLong("ID"));
				listItem.setUserId(userId);
				listItem.setValue(rs.getString("VALUE"));
				listItems.add(listItem);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(rs);
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return listItems;
	}
	@Override
	public ListItem saveListItem(ListItem listItem) {
		if(listItem.getId() == null) {
			insertListItem(listItem);
		} else {
			updateListItem(listItem);
		}
		
		return listItem;
	}
	

	private void insertListItem(ListItem listItem) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO TEST.LIST_ITEM (USER_ID, VALUE) VALUES (?, ?)");
			stmt.setLong(1, listItem.getUserId());
			stmt.setString(2, listItem.getValue());
			stmt.executeUpdate();
			stmt = connection.prepareStatement("SELECT SYSIBM.IDENTITY_VAL_LOCAL() AS last_cod FROM TEST.LIST_ITEM");
			rs = stmt.executeQuery();
			
			
			if (rs.next()) {
				listItem.setId(rs.getLong("last_cod"));
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(rs);
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
	}
	
	private void updateListItem(ListItem listItem) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"UPDATE TEST.LIST_ITEM SET VALUE = ? WHERE ID = ?");
			stmt.setString(1, listItem.getValue());
			stmt.setLong(2, listItem.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
	}
	
	@Override
	public void deleteListItem(Long listItemId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"DELETE FROM TEST.LIST_ITEM WHERE ID = ?");
			stmt.setLong(1, listItemId);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
	}

}
