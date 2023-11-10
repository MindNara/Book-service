package com.example.bookservice.command.rest;

import com.example.bookservice.command.CreateBookCommand;
import com.example.bookservice.core.data.BookEntity;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.UUID;

@RestController
public class BookCommandController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/createBook")
    public String createBook(@RequestBody CreateBookRestModel model) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        rabbitTemplate.convertAndSend("Direct", "addBook", model);
        return "Create Book";
    }

    @PutMapping("updateBook/{id}")
    public String updateBook(@PathVariable("id") String bookId) {
        return "HTTP PUT Handled " + bookId;
    }

    @DeleteMapping("deleteBook/{id}")
    public String deleteBook(@PathVariable("id") String bookId) {
        rabbitTemplate.convertAndSend("Direct", "deleteBook", bookId);
        return "HTTP DELETE Handled " + bookId;
    }

}
