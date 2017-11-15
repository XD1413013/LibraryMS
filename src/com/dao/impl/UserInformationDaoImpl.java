package com.dao.impl;

import java.sql.*;

import com.dao.UserInformationDao;
import com.domain.Librarian;
import com.domain.Reader;
import com.utils.DBUtils;

public class UserInformationDaoImpl implements UserInformationDao {

	@Override
	public String get(Connection conn, String urn, String psw) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("select reader_status from reader where reader_id=? and reader_pwd=?");
		ps.setString(1, urn);
		ps.setString(2, psw);
		String a = "";
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			a = "1";
			if (rs.getString(1).equals("0")) {
				a = "-1";
			}
		} else {
			a = "0";
		}
		return a;
	}

	public ResultSet getLibrarian(Connection conn, String urn, String psw) throws SQLException {
		PreparedStatement ps = conn
				.prepareStatement("select * from librarian where librarian_id=? and librarian_pwd=?");
		ps.setString(1, urn);
		ps.setString(2, psw);
		return ps.executeQuery();
	}

	@Override
	public int loginUp(Connection conn, String id,String urn, String sex,String pwd) {
		PreparedStatement ps;
		int code=-2;
		try {
			ps = conn.prepareStatement("insert into reader values(?,?,?,?,?,?,?);");
			ps.setString(1, id);
			ps.setString(2, pwd);
			ps.setString(3, urn);
			ps.setString(4, sex);
			ps.setString(5, null);
			ps.setString(6, null);
			ps.setString(7, "0");
			code = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

	public Librarian searchLibrarian(String id) {
		Connection conn = null;
		ResultSet rs = null;
		Librarian librarian = new Librarian();
		try {
			conn = DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from librarian where librarian_id=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				librarian.setLibrarian_id(rs.getString(1));
				librarian.setLibrarian_pwd(rs.getString(2));
				librarian.setLibrarian_name(rs.getString(3));
				librarian.setLibrarian_sex(rs.getString(4));
				librarian.setLibrarian_email(rs.getString(5));
				librarian.setLibrarian_phone(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return librarian;
	}
	
	@Override
	public Reader searchReader(String id) {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		Reader reader = new Reader();
		String pwd = null;
		String name = null;
		String sex = null;
		String email = null;
		String phone = null;
		String status = null;

		try {
			conn = DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from reader where reader_id=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
			    id = rs.getString(1);
				pwd = rs.getString(2);
				name = rs.getString(3);
				sex = rs.getString(4);
				email = rs.getString(5);
				phone = rs.getString(6);
				status = rs.getString(7);

				reader.setReader_id(id);
				reader.setReader_pwd(pwd);
				reader.setReader_name(name);
				reader.setReader_sex(sex);
				reader.setReader_email(email);
				reader.setReader_phone(phone);
				reader.setReader_status(status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reader;
	}

	@Override
	public boolean Modify(String id, String data, int flag, int user) {
		Connection conn = null;
		PreparedStatement ps = null;
		if (user == 1) {
			try {
				conn = DBUtils.getConnection();
				if (flag == 0) {
					ps = conn.prepareStatement("update reader set reader_pwd = ? where reader_id= ? ");
					ps.setString(1, data);
				} else if (flag == 1) {
					ps = conn.prepareStatement("update reader set reader_email = ? where reader_id= ? ");
					ps.setString(1, data);
				} else {
					ps = conn.prepareStatement("update reader set reader_phone = ? where reader_id= ? ");
					ps.setString(1, data);
				}
				ps.setString(2, id);
				if (ps.executeUpdate() == 1) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				conn = DBUtils.getConnection();
				if (flag == 0) {
					ps = conn.prepareStatement("update librarian set librarian_pwd = ? where librarian_id= ? ");
					ps.setString(1, data);
				} else if (flag == 1) {
					ps = conn.prepareStatement("update librarian set librarian_email = ? where librarian_id= ? ");
					ps.setString(1, data);
				} else {
					ps = conn.prepareStatement("update librarian set librarian_phone = ? where librarian_id= ? ");
					ps.setString(1, data);
				}
				ps.setString(2, id);
				if (ps.executeUpdate() == 1) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
    @Override
    public boolean changeStatus(String id, String status) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean state = false;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("update reader set reader_status=? where reader_id = ?");
            pstmt.setString(1, status);
            pstmt.setString(2, id);
            state = (pstmt.executeUpdate() != 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(rs, stmt, pstmt, conn);
        }

        return state;
    }

}
