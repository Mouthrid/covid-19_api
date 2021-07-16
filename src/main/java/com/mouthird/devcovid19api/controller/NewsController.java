package com.mouthird.devcovid19api.controller;

import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.helper.ValidList;
import com.mouthird.devcovid19api.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
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
    public void postNews(@RequestBody @Valid ValidList<News> newsList) {
        newsService.addNews(newsList);
    }

    /**
     * Modify News content
     * @param news News object
     */
    @PutMapping
    public void putNews(@RequestBody @Valid News news) {
        if(news.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id is missing");
        if(news.getCrawlTime() == null)
            news.setCrawlTime(LocalDate.now());
        newsService.putNews(news);
    }

    @DeleteMapping
    public void deleteNews(@RequestParam("id") Long id) {
        newsService.deleteById(id);
    }
}
