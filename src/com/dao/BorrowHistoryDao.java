package com.dao;

import com.domain.Book;
import com.domain.BorrowHistory;

import java.util.List;

public interface BorrowHistoryDao {
    public List<BorrowHistory> viewHistory(String reader_name);
    public List<BorrowHistory> viewHistory(String startTime, String endTime);
    public boolean whetherRemind(String reader_name);
    public List<Book> reserveHistory(String reader_id);
}
