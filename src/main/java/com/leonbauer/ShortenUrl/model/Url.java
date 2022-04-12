package com.leonbauer.ShortenUrl.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("url")
@Data
public class Url {
    @Id
    private String id;
    private String url;
    private String shorter;
}
