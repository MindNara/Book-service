package com.example.bookservice.command.chapter;

import com.example.bookservice.command.chapter.CreateChapterCommand;
import com.example.bookservice.command.chapter.DeleteChapterCommand;
import com.example.bookservice.command.chapter.UpdateChapterCommand;
import com.example.bookservice.core.events.chapter.ChapterCreateEvent;
import com.example.bookservice.core.events.chapter.ChapterDeleteEvent;
import com.example.bookservice.core.events.chapter.ChapterUpdateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Aggregate
public class ChapterAggregate {
    @AggregateIdentifier
    private String chapterId;
    private String number;
    private String title;
    private ArrayList<String> content;
    private String cover;
    private String view;
    private String like;
    private Date date;
    private String bookId;

    public ChapterAggregate() {
    }

    @CommandHandler
    public ChapterAggregate(CreateChapterCommand createChapterCommand) {
        System.out.println("Book Aggregate: Create Book");

        ChapterCreateEvent chapterCreateEvent = new ChapterCreateEvent();
        BeanUtils.copyProperties(createChapterCommand, chapterCreateEvent);
        AggregateLifecycle.apply(chapterCreateEvent);
    }

    @EventSourcingHandler
    public void on(ChapterCreateEvent chapterCreateEvent) {
        System.out.println("ON AGGREGATE: CREATE BOOK");

        this.chapterId = UUID.randomUUID().toString();
        this.number = chapterCreateEvent.getNumber();
        this.title = chapterCreateEvent.getTitle();
        this.content = chapterCreateEvent.getContent();
        this.cover = chapterCreateEvent.getCover();
        this.view = chapterCreateEvent.getView();
        this.like = chapterCreateEvent.getLike();
        this.date = chapterCreateEvent.getDate();
        this.bookId = chapterCreateEvent.getBookId();
    }

    @CommandHandler
    public ChapterAggregate(UpdateChapterCommand updateChapterCommand) {
        System.out.println("Book Aggregate: Update Book");

        ChapterUpdateEvent chapterUpdateEvent = new ChapterUpdateEvent();
        BeanUtils.copyProperties(updateChapterCommand, chapterUpdateEvent);
        AggregateLifecycle.apply(chapterUpdateEvent);
    }

    @EventSourcingHandler
    public void on(ChapterUpdateEvent chapterUpdateEvent) {
        System.out.println("ON AGGREGATE: UPDATE BOOK");

        this.chapterId = UUID.randomUUID().toString();
        this.number = chapterUpdateEvent.getNumber();
        this.title = chapterUpdateEvent.getTitle();
        this.content = chapterUpdateEvent.getContent();
        this.cover = chapterUpdateEvent.getCover();
        this.view = chapterUpdateEvent.getView();
        this.like = chapterUpdateEvent.getLike();
        this.date = chapterUpdateEvent.getDate();
        this.bookId = chapterUpdateEvent.getBookId();
    }

    @CommandHandler
    public ChapterAggregate(DeleteChapterCommand deleteChapterCommand) {
        System.out.println("Book Aggregate: Delete Book");

        ChapterDeleteEvent chapterDeleteEvent = new ChapterDeleteEvent();
        BeanUtils.copyProperties(deleteChapterCommand, chapterDeleteEvent);
        AggregateLifecycle.apply(chapterDeleteEvent);
    }

    @EventSourcingHandler
    public void on(ChapterDeleteEvent chapterDeleteEvent) {
        System.out.println("ON AGGREGATE: DELETE BOOK");

        this.chapterId = UUID.randomUUID().toString();
        this.number = chapterDeleteEvent.getNumber();
        this.title = chapterDeleteEvent.getTitle();
        this.content = chapterDeleteEvent.getContent();
        this.cover = chapterDeleteEvent.getCover();
        this.view = chapterDeleteEvent.getView();
        this.like = chapterDeleteEvent.getLike();
        this.date = chapterDeleteEvent.getDate();
        this.bookId = chapterDeleteEvent.getBookId();
    }
}
