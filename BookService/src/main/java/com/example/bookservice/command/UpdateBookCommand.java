package com.example.bookservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class UpdateBookCommand {
    @TargetAggregateIdentifier
    private final String bookId;
    private final String title;
    private final String description;
    private final String category;
    private final String type;
    private final String cover;
    private final Integer view;
    private final Integer like;
    private final Integer comment;
    private final String status;
    private final String userId;
}
