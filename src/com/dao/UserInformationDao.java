package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.domain.Librarian;
import com.domain.Reader;

public interface UserInformationDao {

	String get(Connection conn, String id, String psw) throws SQLException;

	ResultSet getLibrarian(Connection conn, String id, String psw) throws SQLException;

	int loginUp(Connection conn, String id,String urn, String sex,String pwd);

	Reader searchReader(String id);
	
	Librarian searchLibrarian(String id);

	boolean changeStatus(String id, String status);

	boolean Modify(String id,String data, int flag, int user);
	
}
