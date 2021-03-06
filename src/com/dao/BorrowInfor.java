package com.dao;

import java.util.Date;

public interface BorrowInfor {
	public boolean borrowInfor(String book_id, String reader_name, String time);

	public Date searchInfor(String book_id);

	public boolean returnInfor(String book_id, String borrow_time,String return_time, double payment);
}
