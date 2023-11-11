package com.example.bookservice.command;

import com.example.bookservice.command.book.CreateBookCommand;
import com.example.bookservice.command.book.DeleteBookCommand;
import com.example.bookservice.command.book.UpdateBookCommand;
import com.example.bookservice.command.chapter.CreateChapterCommand;
import com.example.bookservice.command.chapter.DeleteChapterCommand;
import com.example.bookservice.command.chapter.UpdateChapterCommand;
import com.example.bookservice.command.book.BookRestModel;
import com.example.bookservice.command.chapter.ChapterRestModel;
import com.example.bookservice.core.data.book.BookEntity;
import com.example.bookservice.core.data.book.BookRepository;
import com.example.bookservice.core.data.chapter.ChapterEntity;
import com.example.bookservice.core.data.chapter.ChapterRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookCommandService {

    @Autowired
    private final CommandGateway commandGateway;

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final ChapterRepository chapterRepository;

    @Autowired
    public BookCommandService(CommandGateway commandGateway, BookRepository bookRepository, ChapterRepository chapterRepository) {
        this.commandGateway = commandGateway;
        this.bookRepository = bookRepository;
        this.chapterRepository = chapterRepository;
    }

    @RabbitListener(queues = "AddBookQueue")
    public void createBook(BookRestModel model) {
        System.out.println("CREATE BOOK");

        CreateBookCommand command = CreateBookCommand.builder()
                .bookId(UUID.randomUUID().toString())
                .title(model.getTitle())
                .description(model.getDescription())
                .category(model.getCategory())
                .type(model.getType())
                .cover(model.getCover())
                .view(model.getView())
                .like(model.getLike())
                .comment(model.getComment())
                .status(model.getStatus())
                .userId(model.getUserId())
                .build();

        try {
            commandGateway.sendAndWait(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "UpdateBookQueue")
    public void updateBook(BookRestModel model) {
        System.out.println("UPDATE BOOK");

        UpdateBookCommand command = UpdateBookCommand.builder()
                .bookId(model.getBookId())
                .title(model.getTitle())
                .description(model.getDescription())
                .category(model.getCategory())
                .type(model.getType())
                .cover(model.getCover())
                .view(model.getView())
                .like(model.getLike())
                .comment(model.getComment())
                .status(model.getStatus())
                .userId(model.getUserId())
                .build();

        try {
            commandGateway.sendAndWait(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "DeleteBookQueue")
    public void deleteBook(String bookId) {
        System.out.println("DELETE BOOK: " + bookId);

        BookEntity bookEntity = bookRepository.findBookByBookId(bookId);

        if (bookEntity != null) {
            DeleteBookCommand deleteBookCommand = DeleteBookCommand.builder()
                    .bookId(bookEntity.getBookId())
                    .title(bookEntity.getTitle())
                    .description(bookEntity.getDescription())
                    .category(bookEntity.getCategory())
                    .type(bookEntity.getType())
                    .cover(bookEntity.getCover())
                    .view(bookEntity.getView())
                    .like(bookEntity.getLike())
                    .comment(bookEntity.getComment())
                    .status(bookEntity.getStatus())
                    .userId(bookEntity.getUserId())
                    .build();

            try {
                commandGateway.sendAndWait(deleteBookCommand);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Book not found with ID: " + bookId);
        }

    }

    @RabbitListener(queues = "AddChapterQueue")
    public void createChapter(ChapterRestModel model) {
        System.out.println("CREATE CHAPTER");

        CreateChapterCommand command = CreateChapterCommand.builder()
                .chapterId(UUID.randomUUID().toString())
                .number(model.getNumber())
                .title(model.getTitle())
                .content(model.getContent())
                .cover(model.getCover())
                .view(model.getView())
                .like(model.getLike())
                .date(model.getDate())
                .bookId(model.getBookId())
                .build();

        try {
            commandGateway.sendAndWait(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "UpdateChapterQueue")
    public void updateChapter(ChapterRestModel model) {
        System.out.println("UPDATE CHAPTER");

        UpdateChapterCommand command = UpdateChapterCommand.builder()
                .chapterId(model.getChapterId())
                .number(model.getNumber())
                .title(model.getTitle())
                .content(model.getContent())
                .cover(model.getCover())
                .view(model.getView())
                .like(model.getLike())
                .date(model.getDate())
                .bookId(model.getBookId())
                .build();

        try {
            commandGateway.sendAndWait(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "DeleteChapterQueue")
    public void deleteChapter(String chapterId) {
        System.out.println("DELETE CHAPTER");

        ChapterEntity chapterEntity = chapterRepository.findChapterByChapterId(chapterId);

        DeleteChapterCommand command = DeleteChapterCommand.builder()
                .chapterId(chapterEntity.getChapterId())
                .number(chapterEntity.getNumber())
                .title(chapterEntity.getTitle())
                .content(chapterEntity.getContent())
                .cover(chapterEntity.getCover())
                .view(chapterEntity.getView())
                .like(chapterEntity.getLike())
                .date(chapterEntity.getDate())
                .bookId(chapterEntity.getBookId())
                .build();

        try {
            commandGateway.sendAndWait(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
