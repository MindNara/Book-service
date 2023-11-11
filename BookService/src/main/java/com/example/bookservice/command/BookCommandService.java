package com.example.bookservice.command;

import com.example.bookservice.command.rest.BookRestModel;
import com.example.bookservice.core.data.BookEntity;
import com.example.bookservice.core.data.BookRepository;
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
    public BookCommandService(CommandGateway commandGateway, BookRepository bookRepository) {
        this.commandGateway = commandGateway;
        this.bookRepository = bookRepository;
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
        System.out.println(model);

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
        System.out.println(bookEntity);

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
}
