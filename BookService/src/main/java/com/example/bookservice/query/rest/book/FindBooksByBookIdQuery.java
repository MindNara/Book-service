package com.example.bookservice.query.rest.book;

public class FindBooksByBookIdQuery {
    private final String bookId;

    public FindBooksByBookIdQuery(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}
