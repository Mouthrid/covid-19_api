package com.mouthird.devcovid19api.controller;

import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="api/v0/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * Get all News in the database
     * @param limit the number of news list
     * @return News list
     */
    @GetMapping
    public List<News> getNews(@RequestParam("limit") Integer limit) {
        return newsService.getNews(limit);
    }

    /**
     * Add News list to the database
     * @param newsList News list
     */
    @PostMapping
    public void addNews(@RequestBody @Valid List<News> newsList) {
        newsService.addNews(newsList);
    }
}
