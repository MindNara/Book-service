package com.example.bookservice.query.rest.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class ChapterRestModel {
    private String chapterId;
    private String number;
    private String title;
    private ArrayList<String> content;
    private String cover;
    private String view;
    private String like;
    private Date date;
    private String bookId;
}
