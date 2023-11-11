package com.example.bookservice.command;

import com.example.bookservice.command.rest.CreateBookRestModel;
import com.example.bookservice.core.data.BookEntity;
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
    public BookCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RabbitListener(queues = "AddBookQueue")
    public void createBook(CreateBookRestModel model) {
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

        System.out.println(command);

        try {
            commandGateway.sendAndWait(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "DeleteBookQueue")
    public void deleteBook(String bookId) {
        System.out.println("DELETE BOOK: " + bookId);
    }
}
