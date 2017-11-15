package com.service.impl;

import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.service.CancelReserveService;

public class CancelReserveImpl implements CancelReserveService {
	BookDao bookdao = new BookDaoImpl();
	@Override
	public boolean cancel(String book_id) {
		
		return bookdao.CancelReserve(book_id);
	}

}
