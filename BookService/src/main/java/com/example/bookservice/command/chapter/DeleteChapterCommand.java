package com.example.bookservice.command.chapter;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.ArrayList;
import java.util.Date;

@Builder
@Data
public class DeleteChapterCommand {
    @TargetAggregateIdentifier
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
