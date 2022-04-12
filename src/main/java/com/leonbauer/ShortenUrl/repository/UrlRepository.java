package com.leonbauer.ShortenUrl.repository;

import com.leonbauer.ShortenUrl.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UrlRepository extends MongoRepository<Url, String> {
    @Query("{name:'?0'}")
    Url findItemByName(String name);

    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<Url> findAll(String category);

    @Query("{shorter: '?0'}")
    Url findItemByShortcut(String url);

    public long count();
}
