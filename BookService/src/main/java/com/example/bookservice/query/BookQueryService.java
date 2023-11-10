package com.example.bookservice.query;

import com.example.bookservice.query.rest.BookRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
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
        System.out.println("GET BOOKS");

        FindBooksQuery findBooksQuery = new FindBooksQuery();
        return queryGateway.query(
                findBooksQuery,
                ResponseTypes.multipleInstancesOf(BookRestModel.class)
        ).join();
    }
}
