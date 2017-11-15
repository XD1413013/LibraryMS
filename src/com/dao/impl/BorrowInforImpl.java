package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dao.BorrowInfor;
import com.utils.DBUtils;

public class BorrowInforImpl implements BorrowInfor {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public boolean borrowInfor(String book_id, String reader_name, String time) {
		String sql = "insert into borrow_infor values(?,?,?,?)";
		try {
			try {
				conn = DBUtils.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book_id);
			pstmt.setString(2, reader_name);
			pstmt.setString(3, time);
			pstmt.setInt(4, 1);
			return (pstmt.executeUpdate() != 0);
		} catch (SQLException e) {
			return false;
		} finally {
			DBUtils.closeAll(rs, stmt, pstmt, conn);
		}
	}

	@Override
	public boolean returnInfor(String book_id,String borrow_time,String return_time, double payment) {
		String sql0 = "select reader_name from borrow_infor where book_id = '" + book_id + "' and return_flag=1";
		String sql = "insert into return_infor values(?,?,?,?,?)";
		try {
			try {
				conn = DBUtils.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql0);
			rs.next();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book_id);
			pstmt.setString(2, rs.getString(1));
			pstmt.setString(3, borrow_time);
			pstmt.setString(4, return_time);
			pstmt.setDouble(5,payment);
			return (pstmt.executeUpdate() != 0);
		} catch (SQLException e) {
			return false;
		} finally {
			DBUtils.closeAll(rs, stmt, pstmt, conn);
		}
	}

	@Override
	public Date searchInfor(String book_id) {

		String sql0 = "select borrow_time from borrow_infor where book_id = '" + book_id + "' AND return_flag = 0";
		try {
			conn = DBUtils.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql0);
			if (rs.next()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = dateFormat.parse(rs.getString(1));
				return date;
			}else {

			}
		} catch (Exception e) {
			return null;
		} finally {
			DBUtils.closeAll(rs, stmt, pstmt, conn);
		}
		return null;
	}


	public double prePay(String reader_id){
		Date now = new Date();
		double payment = 0.0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			conn = DBUtils.getConnection();
			String sql = "SELECT book_id,borrow_time FROM borrow_infor WHERE reader_id = ? AND return_flag = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,reader_id);
			rs = pstmt.executeQuery();
			Date borrow_time;
			while (rs.next()){
				String book_id = rs.getString(1);
				borrow_time = sdf.parse(rs.getString(2));
				payment += now.getTime() / (24 * 60 * 60 * 1000) - borrow_time.getTime() / (24 * 60 * 60 * 1000)-30;
				payment -= getMoney(book_id);
			}
		}catch (Exception e){

		}finally {
			DBUtils.closeAll(rs,stmt,pstmt,conn);
		}
		return payment;
	}

	public void fresh(String reader_id){
		Connection conn2 = null;
		Date now = new Date();
		double payment = 0.0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			conn = DBUtils.getConnection();
			String sql = "SELECT book_id,borrow_time FROM borrow_infor WHERE reader_id = ? AND return_flag = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,reader_id);
			rs = pstmt.executeQuery();
			Date borrow_time;
			while (rs.next()){
				String book_id = rs.getString(1);
				borrow_time = sdf.parse(rs.getString(2));
				payment = now.getTime() / (24 * 60 * 60 * 1000) - borrow_time.getTime() / (24 * 60 * 60 * 1000) - 30;
				payment -= getMoney(book_id);
				System.out.println(payment);
				System.out.println(book_id);
				System.out.println(sdf.format(borrow_time));
	
				conn2 = DBUtils.getConnection();
				
				String sql_1 = "UPDATE borrow_infor SET payment = payment + ? WHERE book_id = ? AND borrow_time = ? LIMIT 1";
				pstmt = conn2.prepareStatement(sql_1);System.out.println("0");
				pstmt.setDouble(1,payment);System.out.println("1");
				pstmt.setString(2,book_id);System.out.println("2");
				pstmt.setString(3,sdf.format(borrow_time));System.out.println("3");
				pstmt.execute();
				System.out.println(book_id);
				System.out.println(sdf.format(borrow_time));
				if(conn2!=null) {
					conn2.close();
				}
			}
		}catch (Exception e){

		}finally {
			DBUtils.closeAll(rs,stmt,pstmt,conn);
		}
	}

	public double getMoney(String book_id){
		double payment = 0.0;
		try{
			conn = DBUtils.getConnection();
			String sql = "SELECT payment FROM borrow_infor WHERE book_id = ? AND return_flag = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,book_id);
			rs = pstmt.executeQuery();
			if (rs.next()){
				payment = rs.getDouble(1);
			}
		}catch (Exception e){

		}finally {
			DBUtils.closeAll(rs,stmt,pstmt,conn);
		}
		return payment;
	}

}
