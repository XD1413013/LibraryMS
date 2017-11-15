package com.service.impl;

import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.service.ReserveService;

public class ReserveServiceImpl implements ReserveService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public boolean reserve(String book_isbn, String reader_id) {

        return bookDao.reserveBook(book_isbn,reader_id);
    }
}
