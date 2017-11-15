package com.service;

import java.util.List;

import com.domain.Book;

public interface ViewReserve {
	public List<Book> view(String reader_id);
}
