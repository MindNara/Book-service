package com.example.bookservice.command.rest;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Data
public class ChapterRestModel implements Serializable {
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
