package com.example.bookservice.command.rest;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookCommandController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/createBook")
    public String createBook(@RequestBody BookRestModel model) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        rabbitTemplate.convertAndSend("Direct", "addBook", model);
        return "Create Book";
    }

    @PutMapping("updateBook/{bookId}")
    public String updateBook(@PathVariable("bookId") String bookId) {
        return "HTTP PUT Handled " + bookId;
    }

    @DeleteMapping("deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") String bookId) {
        rabbitTemplate.convertAndSend("Direct", "deleteBook", bookId);
        return "Delete Book ID: " + bookId;
    }

}
