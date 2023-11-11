package com.example.bookservice.core.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document("Chapter")
public class ChapterEntity implements Serializable {
    @Id
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
