package com.dao.impl;

import com.dao.BorrowHistoryDao;
import com.domain.Book;
import com.domain.BorrowHistory;
import com.utils.DBUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.sql.*;
import java.util.List;

public class BorrowHistoryDaoImpl implements BorrowHistoryDao {

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public List<BorrowHistory> viewHistory(String reader_id) {
        List<BorrowHistory> borrowHistories = new ArrayList<>();
        BorrowHistory borrowHistory = null;
        String should_r_time = null;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sql = "SELECT * FROM borrow_infor WHERE reader_id = ?";
        try{
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,reader_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                date = format.parse(rs.getString(5));
                cal.setTime(date);
                cal.add(Calendar.DATE,30);
                date = cal.getTime();
                should_r_time = format.format(date);
                borrowHistory = new BorrowHistory(rs.getString(2),rs.getString(3),rs.getString(5),should_r_time,rs.getString(7),rs.getDouble(8),rs.getString(9));
                borrowHistories.add(borrowHistory);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(rs, stmt, pstmt, conn);
        }
        return borrowHistories;
    }

    @Override
    public List<BorrowHistory> viewHistory(String startTime, String endTime) {
        List<BorrowHistory> borrowHistories = new ArrayList<>();
        BorrowHistory borrowHistory = null;
        String sql = "SELECT * FROM borrow_info WHERE return_time < ? AND return_time >= ?";
        try{
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, endTime);
            pstmt.setString(2, startTime);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                borrowHistory = new BorrowHistory(rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDouble(8),rs.getString(9));
                borrowHistories.add(borrowHistory);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(rs, stmt, pstmt, conn);
        }

        return borrowHistories;
    }

    @Override
    public boolean whetherRemind(String reader_id){

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String sql = "SELECT borrow_time FROM borrow_infor WHERE reader_id = ? AND return_flag = 0";
        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,reader_id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Date borrow_time = dateFormat.parse(rs.getString(1));
                long days = now.getTime()/(24 * 60 * 60 * 1000) - borrow_time.getTime()/(24 * 60 * 60 * 1000);
                if (days > 30){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(rs, stmt, pstmt, conn);
        }
        return false;
    }
    
	@Override
	public List<Book> reserveHistory(String reader_id) {
		List<Book> books = new ArrayList<>();
		Book book = new Book();
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM book WHERE predetermine_id = ?");
			ps.setString(1, reader_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				book.setBook_id(rs.getString(1));
				book.setBook_name(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setPublishing(rs.getString(4));
				book.setPublishing_time(rs.getString(5));
				book.setIsbn(rs.getString(6));
				book.setLocation(rs.getString(7));
				books.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
}
