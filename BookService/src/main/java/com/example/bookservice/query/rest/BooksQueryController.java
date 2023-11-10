package com.example.bookservice.query.rest;

import com.example.bookservice.query.FindBooksQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/books")
public class BooksQueryController {
    private final QueryGateway queryGateway;

    public BooksQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping()
    public List<BookRestModel> getBooks() {
        FindBooksQuery findBooksQuery = new FindBooksQuery();
        return queryGateway.query(
                findBooksQuery,
                ResponseTypes.multipleInstancesOf(BookRestModel.class)
        ).join();
    }
}
