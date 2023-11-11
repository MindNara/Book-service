package com.example.bookservice.query.rest;

import com.example.bookservice.core.data.BookEntity;
import com.example.bookservice.core.data.BookRepository;
import com.example.bookservice.query.FindBooksByBookIdQuery;
import com.example.bookservice.query.FindBooksQuery;
import com.example.bookservice.query.rest.model.BookRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.*;

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

    @QueryHandler
    public BookRestModel findBooksByBookId(FindBooksByBookIdQuery query) {
        BookEntity bookEntity = bookRepository.findBookByBookId(query.getBookId());

        if (bookEntity != null) {
            BookRestModel bookRestModel = new BookRestModel();
            BeanUtils.copyProperties(bookEntity, bookRestModel);
            return bookRestModel;
        } else {
            return null;
        }

    }
}
