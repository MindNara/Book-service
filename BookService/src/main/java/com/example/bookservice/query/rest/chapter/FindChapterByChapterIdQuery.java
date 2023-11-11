package com.example.bookservice.query.rest.chapter;

public class FindChapterByChapterIdQuery {

    private final String chapterId;

    public FindChapterByChapterIdQuery(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterId() {
        return chapterId;
    }
}
