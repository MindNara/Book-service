# book-service

## Dependencies
- Lombok
- Spring Web
- Spring for RabbitMQ
- Spring Data MongoDB

## Run Project
1. Run Axon server and RabbitMQ
2. Run Project
   - BookService
   - DiscoveryServer
   - ApiGateway

## Path
- Get Book: (GET) /book-service/getBook
- Create Book: (GET) /book-service/createBook
- Update Book: (PUT) /book-service/updateBook
- Create Book: (DELETE) /book-service/deleteBook

## RabbitMQ
- Get Book
    - Queues: GetBookQueue
    - Exchanges: Direct
    - Routing key: getBook
- Create Book
    - Queues: AddBookQueue
    - Exchanges: Direct
    - Routing key: addBook
- Update Book
    - Queues: UpdateBookQueue
    - Exchanges: Direct
    - Routing key: updateBook
- Delete Book
    - Queues: DeleteBookQueue
    - Exchanges: Direct
    - Routing key: deleteBook
