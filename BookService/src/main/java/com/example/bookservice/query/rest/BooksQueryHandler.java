package com.example.bookservice.query.rest;

import com.example.bookservice.core.data.BookEntity;
import com.example.bookservice.core.data.BookRepository;
import com.example.bookservice.query.FindBooksQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BooksQueryHandler {
    private final BookRepository bookRepository;

    public BooksQueryHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @QueryHandler
    public List<BookRestModel> findBooks(FindBooksQuery query) {
        List<BookRestModel> bookRest = new ArrayList<>();
        List<BookEntity> storedBooks = bookRepository.findAll();
        for (BookEntity bookEntity : storedBooks) {
            BookRestModel bookRestModel = new BookRestModel();
            BeanUtils.copyProperties(bookEntity, bookRestModel);
            bookRest.add(bookRestModel);
        }
        return bookRest;
    }
}
