package com.leonbauer.ShortenUrl.controller;

import com.leonbauer.ShortenUrl.model.RequestUrl;
import com.leonbauer.ShortenUrl.model.Url;
import com.leonbauer.ShortenUrl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/shortenUrl")
public class UrlController {

    @Autowired
    UrlRepository urlRepository;

    @PostMapping("/shorten")
    public String setUrl(@RequestBody String requestUrl) throws Exception {
        Url url = new Url();
        url.setUrl(requestUrl);
        url.setShorter(doubleHash(requestUrl));
        urlRepository.save(url);
        return(url.getShorter());
    }

    @RequestMapping("/code/{code}")
    public void giveUrl(@PathVariable String code) throws Exception {
        Url urlObj = urlRepository.findItemByShortcut(code);
        Runtime rt = Runtime.getRuntime();
        String url = urlObj.getUrl();
        rt.exec("open " + url);
    }

//    @GetMapping("/test/{code}")
//    public Url test(@PathVariable String code) {
//        return(urlRepository.findItemByShortcut(code));
//    }

    public String doubleHash(String url) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(url.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        byte[] hash2 = digest.digest(sb.toString().getBytes(StandardCharsets.UTF_8));
        StringBuilder sb2 = new StringBuilder();
        for (byte b : hash) {
            sb2.append(String.format("%02x", b));
        }
        return(sb2.toString().substring(0,5));
    }

    @GetMapping("all")
    public String getUrl() {
        List<Url> urlList = new ArrayList<>();
        return(urlRepository.findAll().toString());
    }

    @RequestMapping("removeAll")
    public String removeAll() {
        urlRepository.deleteAll();
        return("All records deleted");
    }
}
