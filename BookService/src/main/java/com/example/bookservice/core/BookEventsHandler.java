package com.example.bookservice.core;

import com.example.bookservice.core.data.BookEntity;
import com.example.bookservice.core.data.BookRepository;
import com.example.bookservice.core.data.ChapterEntity;
import com.example.bookservice.core.data.ChapterRepository;
import com.example.bookservice.core.events.book.BookCreateEvent;
import com.example.bookservice.core.events.book.BookDeleteEvent;
import com.example.bookservice.core.events.book.BookUpdateEvent;
import com.example.bookservice.core.events.chapter.ChapterCreateEvent;
import com.example.bookservice.core.events.chapter.ChapterDeleteEvent;
import com.example.bookservice.core.events.chapter.ChapterUpdateEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookEventsHandler {
    private final BookRepository bookRepository;
    private final ChapterRepository chapterRepository;

    @Autowired
    public BookEventsHandler(BookRepository bookRepository, ChapterRepository chapterRepository) {
        this.bookRepository = bookRepository;
        this.chapterRepository = chapterRepository;
    }

    @EventHandler
    public void on(BookCreateEvent event) {
        System.out.println("Adding book in mongoDB");

        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(event, bookEntity);
        bookRepository.insert(bookEntity);
    }

    @EventHandler
    public void on(BookUpdateEvent event) {
        System.out.println("Updated book in mongoDB");

        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(event, bookEntity);
        bookRepository.save(bookEntity);
    }

    @EventHandler
    public void on(BookDeleteEvent event) {
        System.out.println("Delete book in mongoDB");

        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(event, bookEntity);
        bookRepository.deleteById(bookEntity.getBookId());
    }

    @EventHandler
    public void on(ChapterCreateEvent event) {
        System.out.println("Adding chapter in mongoDB");

        ChapterEntity chapterEntity = new ChapterEntity();
        BeanUtils.copyProperties(event, chapterEntity);
        chapterRepository.insert(chapterEntity);
    }

    @EventHandler
    public void on(ChapterUpdateEvent event) {
        System.out.println("Updated chapter in mongoDB");

        ChapterEntity chapterEntity = new ChapterEntity();
        BeanUtils.copyProperties(event, chapterEntity);
        chapterRepository.save(chapterEntity);
    }

    @EventHandler
    public void on(ChapterDeleteEvent event) {
        System.out.println("Delete chapter in mongoDB");

        ChapterEntity chapterEntity = new ChapterEntity();
        BeanUtils.copyProperties(event, chapterEntity);
        chapterRepository.deleteById(chapterEntity.getChapterId());
    }
}
