package com.example.bookservice.command.book;

import com.example.bookservice.command.book.CreateBookCommand;
import com.example.bookservice.command.book.DeleteBookCommand;
import com.example.bookservice.command.book.UpdateBookCommand;
import com.example.bookservice.core.events.book.BookCreateEvent;
import com.example.bookservice.core.events.book.BookDeleteEvent;
import com.example.bookservice.core.events.book.BookUpdateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Aggregate
public class BookAggregate {
    @AggregateIdentifier
    private String bookId;
    private String title;
    private String description;
    private String category;
    private String type;
    private String cover;
    private Integer view;
    private Integer like;
    private Integer comment;
    private String status;
    private String userId;

    public BookAggregate() {
    }

    @CommandHandler
    public BookAggregate(CreateBookCommand createBookCommand) {
        System.out.println("Book Aggregate: Create Book");

        BookCreateEvent bookCreateEvent = new BookCreateEvent();
        BeanUtils.copyProperties(createBookCommand, bookCreateEvent);
        AggregateLifecycle.apply(bookCreateEvent);
    }

    @EventSourcingHandler
    public void on(BookCreateEvent bookCreateEvent) {
        System.out.println("ON AGGREGATE: CREATE BOOK");

        this.bookId = UUID.randomUUID().toString();
        this.title = bookCreateEvent.getTitle();
        this.description = bookCreateEvent.getDescription();
        this.category = bookCreateEvent.getCategory();
        this.type = bookCreateEvent.getType();
        this.cover = bookCreateEvent.getCover();
        this.view = bookCreateEvent.getView();
        this.like = bookCreateEvent.getLike();
        this.comment = bookCreateEvent.getComment();
        this.status = bookCreateEvent.getStatus();
        this.userId = bookCreateEvent.getUserId();
    }

    @CommandHandler
    public BookAggregate(UpdateBookCommand updateBookCommand) {
        System.out.println("Book Aggregate: Update Book");

        BookUpdateEvent bookUpdateEvent = new BookUpdateEvent();
        BeanUtils.copyProperties(updateBookCommand, bookUpdateEvent);
        AggregateLifecycle.apply(bookUpdateEvent);
    }

    @EventSourcingHandler
    public void on(BookUpdateEvent bookUpdateEvent) {
        System.out.println("ON AGGREGATE: UPDATE BOOK");

        this.bookId = UUID.randomUUID().toString();
        this.title = bookUpdateEvent.getTitle();
        this.description = bookUpdateEvent.getDescription();
        this.category = bookUpdateEvent.getCategory();
        this.type = bookUpdateEvent.getType();
        this.cover = bookUpdateEvent.getCover();
        this.view = bookUpdateEvent.getView();
        this.like = bookUpdateEvent.getLike();
        this.comment = bookUpdateEvent.getComment();
        this.status = bookUpdateEvent.getStatus();
        this.userId = bookUpdateEvent.getUserId();
    }

    @CommandHandler
    public BookAggregate(DeleteBookCommand deleteBookCommand) {
        System.out.println("Book Aggregate: Delete Book");

        BookDeleteEvent bookDeleteEvent = new BookDeleteEvent();
        BeanUtils.copyProperties(deleteBookCommand, bookDeleteEvent);
        AggregateLifecycle.apply(bookDeleteEvent);
    }

    @EventSourcingHandler
    public void on(BookDeleteEvent bookDeleteEvent) {
        System.out.println("ON AGGREGATE: DELETE BOOK");

        this.bookId = UUID.randomUUID().toString();
        this.title = bookDeleteEvent.getTitle();
        this.description = bookDeleteEvent.getDescription();
        this.category = bookDeleteEvent.getCategory();
        this.type = bookDeleteEvent.getType();
        this.cover = bookDeleteEvent.getCover();
        this.view = bookDeleteEvent.getView();
        this.like = bookDeleteEvent.getLike();
        this.comment = bookDeleteEvent.getComment();
        this.status = bookDeleteEvent.getStatus();
        this.userId = bookDeleteEvent.getUserId();
    }
}
