package com.leonbauer.ShortenUrl.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class RequestUrl {
    private String url;
}
