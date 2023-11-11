package com.example.bookservice.core.data.book;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Document("Book")
public class BookEntity implements Serializable {

    @Id
    private String bookId;
    private String title;
    private String description;
    private String category;
    private String type;
    private String cover;
    private Integer view;
    private Integer like;
    private Integer comment;
    private String status;
    private String userId;

    public BookEntity() {
    }

    public BookEntity(String bookId, String title, String description, String category, String type, String cover, Integer view, Integer like, Integer comment, String status, String userId) {
        this.bookId = bookId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.type = type;
        this.cover = cover;
        this.view = view;
        this.like = like;
        this.comment = comment;
        this.status = status;
        this.userId = userId;
    }
}
