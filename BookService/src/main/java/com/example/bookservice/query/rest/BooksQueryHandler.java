package com.example.bookservice.query.rest;

import com.example.bookservice.core.data.book.BookEntity;
import com.example.bookservice.core.data.book.BookRepository;
import com.example.bookservice.core.data.chapter.ChapterEntity;
import com.example.bookservice.core.data.chapter.ChapterRepository;
import com.example.bookservice.query.rest.book.FindBooksByBookIdQuery;
import com.example.bookservice.query.rest.book.FindBooksQuery;
import com.example.bookservice.query.rest.chapter.FindChapterByChapterIdQuery;
import com.example.bookservice.query.rest.chapter.FindChapterQuery;
import com.example.bookservice.query.rest.book.BookRestModel;
import com.example.bookservice.query.rest.chapter.ChapterRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BooksQueryHandler {
    private final BookRepository bookRepository;
    private final ChapterRepository chapterRepository;

    public BooksQueryHandler(BookRepository bookRepository, ChapterRepository chapterRepository) {
        this.bookRepository = bookRepository;
        this.chapterRepository = chapterRepository;
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

    @QueryHandler
    public List<ChapterRestModel> findChapters(FindChapterQuery query) {
        List<ChapterRestModel> chapterRest = new ArrayList<>();
        List<ChapterEntity> storedChapters = chapterRepository.findAll();

        for (ChapterEntity chapterEntity : storedChapters) {
            ChapterRestModel chapterRestModel = new ChapterRestModel();
            BeanUtils.copyProperties(chapterEntity, chapterRestModel);
            chapterRest.add(chapterRestModel);
        }
        return chapterRest;
    }

    @QueryHandler
    public ChapterRestModel findChaptersByChapterId(FindChapterByChapterIdQuery query) {
        ChapterEntity chapterEntity = chapterRepository.findChapterByChapterId(query.getChapterId());

        if (chapterEntity != null) {
            ChapterRestModel chapterRestModel = new ChapterRestModel();
            BeanUtils.copyProperties(chapterEntity, chapterRestModel);
            return chapterRestModel;
        } else {
            return null;
        }

    }
}
