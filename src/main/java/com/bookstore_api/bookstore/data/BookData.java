package com.bookstore_api.bookstore.data;

import com.bookstore_api.bookstore.entity.Book;

import java.util.List;

public class BookData {

    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
