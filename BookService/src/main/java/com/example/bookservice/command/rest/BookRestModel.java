package com.example.bookservice.command.rest;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookRestModel implements Serializable {
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
