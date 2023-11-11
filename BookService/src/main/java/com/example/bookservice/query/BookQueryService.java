package com.example.bookservice.query;

import com.example.bookservice.query.rest.model.BookRestModel;
import com.example.bookservice.query.rest.model.ChapterRestModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookQueryService {

    @Autowired
    private QueryGateway queryGateway;

    public BookQueryService(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @RabbitListener(queues = "GetBookQueue")
    public List<BookRestModel> getBook() {
        System.out.println("GET ALL BOOKS");

        FindBooksQuery findBooksQuery = new FindBooksQuery();
        return queryGateway.query(
                findBooksQuery,
                ResponseTypes.multipleInstancesOf(BookRestModel.class)
        ).join();
    }

    @RabbitListener(queues = "GetBookIdQueue")
    public BookRestModel getBookByBookId(String bookId) {
        System.out.println("GET BOOK BY BOOK ID: " + bookId);

        FindBooksByBookIdQuery findBooksByBookIdQuery = new FindBooksByBookIdQuery(bookId);
        return queryGateway.query(
                findBooksByBookIdQuery,
                ResponseTypes.instanceOf(BookRestModel.class)
        ).join();
    }

    @RabbitListener(queues = "GetChapterQueue")
    public List<ChapterRestModel> getChapters() {
        System.out.println("GET ALL CHAPTERS");

        FindChapterQuery findChapterQuery = new FindChapterQuery();
        return queryGateway.query(
                findChapterQuery,
                ResponseTypes.multipleInstancesOf(ChapterRestModel.class)
        ).join();
    }

    @RabbitListener(queues = "GetChapterIdQueue")
    public ChapterRestModel getChapterByChapterId(String chapterId) {
        System.out.println("GET CHAPTERS BY CHAPTER ID: " + chapterId);

        FindChapterByChapterIdQuery findChapterByChapterIdQuery = new FindChapterByChapterIdQuery(chapterId);
        return queryGateway.query(
                findChapterByChapterIdQuery,
                ResponseTypes.instanceOf(ChapterRestModel.class)
        ).join();
    }
}
