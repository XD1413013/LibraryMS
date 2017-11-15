package com.service.impl;

import com.domain.Book;
import com.service.GetBookInfoService;
import com.utils.DouBan;

public class GetBookInfoServiceImpl implements GetBookInfoService {
    @Override
    public Book getBookFromDouBan(String isbn) {
        String json = DouBan.getJson(isbn);
        if ("null".equals(json)) {
            return null;
        }
        Book book = new Book();
        book.setIsbn(isbn);
        book.setAuthor(DouBan.doFilter(json, "author"));
        book.setBook_name(DouBan.doFilter(json, "title"));
        book.setPublishing(DouBan.doFilter(json, "publisher"));
        book.setPublishing_time(DouBan.doFilter(json, "pubdate"));

        return book;
    }
}
