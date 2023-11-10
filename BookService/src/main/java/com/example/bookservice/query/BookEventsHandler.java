package com.example.bookservice.query;

import com.example.bookservice.core.data.BookEntity;
import com.example.bookservice.core.data.BookRepository;
import com.example.bookservice.core.events.BookCreateEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookEventsHandler {
    private final BookRepository bookRepository;

    @Autowired
    public BookEventsHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventHandler
    public void on(BookCreateEvent event) {
        System.out.println("Adding book in mongoDB");
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(event, bookEntity);
        bookRepository.insert(bookEntity);
    }
}
