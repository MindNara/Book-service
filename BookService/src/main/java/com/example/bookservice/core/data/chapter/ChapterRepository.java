package com.example.bookservice.core.data.chapter;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends MongoRepository<ChapterEntity, String> {
    @Query(value = "{ 'chapterId' : ?0 }")
    public ChapterEntity findChapterByChapterId(String chapterId);
}
