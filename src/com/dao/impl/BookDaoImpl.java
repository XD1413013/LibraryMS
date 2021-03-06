package com.dao.impl;

import com.dao.BookDao;
import com.domain.Book;
import com.utils.DBUtils;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookDaoImpl implements BookDao {

	// 模糊搜书
			public List<Book> searchBook(String input) throws Exception {
			List<Book> bookList = null;
			Connection conn = null;
			Statement stmt = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			if (!input.equals("")) {
				String sql = "select * from book where book_name like ? or author like ?  order by isbn ;";
				try {
					conn = DBUtils.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + input + "%");
					pstmt.setString(2, "%" + input + "%");
					rs = pstmt.executeQuery();
					if (rs.next()) {
						rs.previous();
						BeanListHandler<Book> bh = new BeanListHandler<Book>(Book.class);
						bookList = bh.handle(rs);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBUtils.closeAll(rs, stmt, pstmt, conn);
				}
			}

			return bookList;
		}


	// 精确搜书
	public Boolean searchTheBook(String bookName, String author) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		boolean state = false;

		String sql = "select book_name from book where book_name = '" + bookName + "' and author = '" + author + "'";

		try {
			conn = DBUtils.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				state = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, stmt, pstmt, conn);
		}

		return state;
	}

	public boolean addBook(Book book) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean state = false;

		try {
			conn = DBUtils.getConnection();
			String sql0 = "insert into book values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql0);
			pstmt.setString(1,book.getBook_id());
			pstmt.setString(2,book.getBook_name());
			pstmt.setString(3,book.getAuthor());
			pstmt.setString(4,book.getPublishing());
			pstmt.setString(5,book.getPublishing_time());
			pstmt.setString(6,book.getIsbn());
			pstmt.setString(7,book.getLocation());
			pstmt.setString(8,"1");
			pstmt.setString(9,"0");
			pstmt.setString(10,null);
			state = (pstmt.executeUpdate() != 0);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, stmt, pstmt, conn);
		}

		return state;
	}

	// 借书
	public String borrowBook(String book_id, String reader_id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String state = null;
		Date borrowDate = new Date();
		Date should_Date = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(borrowDate);
		calendar.add(Calendar.DATE,30);
		should_Date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String book_name,author,borrow_time,should_return_time;

		state = checkReserveNum(reader_id);

		if (!state.equals("B_no")) {
			if (state.equals("R_no")) {
				try {
					conn = DBUtils.getConnection();
					String sql = "SELECT book_id FROM book WHERE predetermine_id = ? AND isbn = (SELECT isbn FROM book WHERE book_id = ?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, reader_id);
					pstmt.setString(2, book_id);
					rs = pstmt.executeQuery();
					if (rs.next()) {

					} else {
						return "More than number limition.";
					}
				} catch (Exception e) {

				}
			}
			try {
				conn = DBUtils.getConnection();
				String sql = "SELECT borrow_flag,predetermine_id FROM book WHERE book_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, book_id);
				rs = pstmt.executeQuery();
				if (rs.next()){
					String b_flag = rs.getString(1);
					String flag = rs.getString(2);
					if (flag != null && !flag.equals(reader_id)){
						return "This book is already been reserved.";
					}
					else if(b_flag.equals("1")){
						return "This book is already been borrowed.";
					}
				}
			}catch (Exception e){

			}
			try {
				conn = DBUtils.getConnection();
				String sql = "UPDATE book SET borrow_flag = 1 WHERE book_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, book_id);
				pstmt.execute();
				sql = "SELECT book_name,author,isbn FROM book WHERE book_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,book_id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					book_name = rs.getString(1);
					author = rs.getString(2);
					String isbn = rs.getString(3);

					borrow_time = sdf.format(borrowDate);
					should_return_time = sdf.format(should_Date);
					sql = "INSERT INTO borrow_infor(book_id, book_name, author, reader_id, borrow_time, should_return_time,payment,return_flag) VALUES (?,?,?,?,?,?,0.0,0)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, book_id);
					pstmt.setString(2, book_name);
					pstmt.setString(3, author);
					pstmt.setString(4, reader_id);
					pstmt.setString(5, borrow_time);
					pstmt.setString(6, should_return_time);
					pstmt.execute();


					sql = "UPDATE book SET predetermine_id = NULL WHERE isbn = ? AND predetermine_id = ? LIMIT 1";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,isbn);
					pstmt.setString(2,reader_id);
					pstmt.execute();
					return "Success";
				}
				} catch (SQLException e) {

				} catch (Exception e) {

				} finally {
					DBUtils.closeAll(rs, stmt, pstmt, conn);
				}
		} else {
			return "More than borrow number limition.";
		}
		return "Error";
	}

	// 还书
	public boolean returnBook(String book_id,String return_time,double payment) {

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean state = false;

		try {
			conn = DBUtils.getConnection();
			String sql = "UPDATE book SET borrow_flag = 0 WHERE book_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,book_id);
			pstmt.execute();
			sql = "UPDATE borrow_infor SET return_time = ?,payment = payment + ?,return_flag = 1 WHERE book_id = ? AND return_flag = 0 LIMIT 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,return_time);
			pstmt.setDouble(2,payment);
			pstmt.setString(3,book_id);
			pstmt.execute();
			state = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, stmt, pstmt, conn);
		}

		return state;
	}

	public boolean deleteBook(String id) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean state = false;

		try {
			conn = DBUtils.getConnection();
			stmt = conn.createStatement();

			String sql = "select book_name from book where book_id = '" + id + "' and borrow_flag='0'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (!rs.next()){
				return false;
			}

			sql = "delete from book where book_id= '" + id + "'";
			state = (stmt.executeUpdate(sql) != 0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, stmt, pstmt, conn);
		}
		return state;
	}

	public boolean changeLocation(Book book) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean state = false;

		try {
			conn = DBUtils.getConnection();
			stmt = conn.createStatement();

			String sql = "update book set location='" + book.getLocation() + "'where book_id = '" + book.getBook_id() + "'";
			state = (stmt.executeUpdate(sql) != 0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, stmt, pstmt, conn);
		}
		return state;
	}

	public boolean changeCanBorrow(Book book) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean state = false;

		try {
			conn = DBUtils.getConnection();
			stmt = conn.createStatement();

			String sql = "update book set can_borrow='" + book.getCan_borrow() + "'where book_id = '" + book.getBook_id() + "'";
			state = (stmt.executeUpdate(sql) != 0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, stmt, pstmt, conn);
		}
		return state;
	}

	public boolean reserveBook(String book_isbn, String reader_id) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{

			String sql = "SELECT book_id FROM book WHERE isbn = ? AND predetermine_id is null";
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,book_isbn);
			rs = pstmt.executeQuery();

			if (rs.next()){
				String book_id = rs.getString(1);
				sql = "UPDATE book SET predetermine_id = ? WHERE book_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,reader_id);
				pstmt.setString(2,book_id);
				pstmt.execute();
				Monitor monitor = new Monitor(book_id,reader_id);
				monitor.start();
				return true;
			}
			else{
				return false;
			}
		}catch (SQLException e){

		}catch (Exception e){

		}finally {
			DBUtils.closeAll(rs, stmt, pstmt, conn);
		}
		return false;
	}

	public String checkReserveNum(String reader_id) {
		int number = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try{
			String sql = "SELECT book_id FROM borrow_infor WHERE reader_id = ? AND return_flag = 0";
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,reader_id);
			rs = pstmt.executeQuery();
			while (rs.next()){
				number ++;
			}
			if (number == 2){
				return "B_no";
			}
			sql = "SELECT book_id FROM book WHERE predetermine_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,reader_id);
			rs = pstmt.executeQuery();
			while (rs.next()){
				number ++;
			}
			if (number < 2){
				return "R_yes";
			}
			else {
				return "R_no";
			}
		}catch (SQLException e){

		}catch (Exception e){

		}finally {
			DBUtils.closeAll(rs, stmt, pstmt, conn);
		}
		return "no";
	}

	public boolean isReader(String reader_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try{
			conn = DBUtils.getConnection();
			String sql = "SELECT reader_status FROM reader WHERE reader_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,reader_id);
			rs = pstmt.executeQuery();

			if (rs.next()){
				String flag = rs.getString(1);
				if (flag.equals("1")){
					return true;
				}
				else{
					return false;
				}
			}
			else {
				return false;
			}
		}catch (SQLException e){

		}catch (Exception e){

		}finally {
			DBUtils.closeAll(rs,stmt,pstmt,conn);
		}
		return false;
	}
	private class Monitor extends Thread{

		private String book_id;
		private String reader_id;

		public Monitor(String book_id, String reader_id) {
			this.book_id = book_id;
			this.reader_id = reader_id;
		}

		public void run() {
			try{
				Thread.sleep(100000);
				Connection conn = DBUtils.getConnection();
				String sql = "UPDATE book SET predetermine_id = NULL WHERE book_id = ? AND predetermine_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,this.book_id);
				pstmt.setString(2,this.reader_id);
				pstmt.execute();
			}catch (InterruptedException e){

			}catch (SQLException e){

			}catch (Exception e){

			}
		}
	}
	@Override
	public boolean CancelReserve(String book_id) {
				Connection conn = null;
				try {
					conn = DBUtils.getConnection();
					PreparedStatement ps = conn.prepareStatement("update book set predetermine_id = null where book_id = ?");
					ps.setString(1, book_id);
					if(ps.executeUpdate()==1) {
						return true;
					}else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		return false;
	}

}
