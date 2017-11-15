package com.dao;

import java.util.List;

import com.domain.Book;

public interface BookDao {
	public List<Book> searchBook(String input) throws Exception;

	public Boolean searchTheBook(String bookName,String author);

	public boolean addBook(Book book);

	public String borrowBook(String book_id,String reader_name);

	public boolean returnBook(String book_id,String return_time,double payment);

	public boolean deleteBook(String id);

	public boolean changeLocation(Book book);

	public boolean reserveBook(String book_isbn, String reader_id);

	public String checkReserveNum(String reader_id);

	public boolean isReader(String reader_id);

	public boolean changeCanBorrow(Book book);
	
	public boolean CancelReserve(String book_id);
}
