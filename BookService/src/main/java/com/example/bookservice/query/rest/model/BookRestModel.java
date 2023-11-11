package com.example.bookservice.query.rest.model;

import lombok.Data;

@Data
public class BookRestModel {
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
}
