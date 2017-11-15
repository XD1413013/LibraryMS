package com.service.impl;

import java.util.List;

import com.dao.BorrowHistoryDao;
import com.dao.impl.BorrowHistoryDaoImpl;
import com.domain.Book;
import com.service.ViewReserve;

public class ViewReserveImpl implements ViewReserve {
	
	private BorrowHistoryDao borrowHistory = new BorrowHistoryDaoImpl();
	@Override
	public List<Book> view(String reader_id) {
		return borrowHistory.reserveHistory(reader_id);
	
	}

}
