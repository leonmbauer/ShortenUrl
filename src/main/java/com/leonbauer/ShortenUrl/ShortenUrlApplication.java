package com.leonbauer.ShortenUrl;

import com.leonbauer.ShortenUrl.model.Url;
import com.leonbauer.ShortenUrl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class ShortenUrlApplication {

	@Autowired
	UrlRepository urlRepository;

	public static void main(String[] args) {
		SpringApplication.run(ShortenUrlApplication.class, args);
	}
}
