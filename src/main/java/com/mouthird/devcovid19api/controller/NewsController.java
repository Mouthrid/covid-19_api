package com.mouthird.devcovid19api.controller;

import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="api/v0/news")
public class NewsController {

    private final NewsService newsService;

    @Value("${APP_KEY}")
    private String APP_KEY;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @CrossOrigin
    @RequestMapping(params = "limit")
    public List<News> getNews(@RequestParam("limit") Integer limit) { return newsService.getNews(limit); }

    @RequestMapping(params = "id")
    public Map<String, Boolean> existsNews(@RequestParam("id") String id) {
        return Collections.singletonMap("state", newsService.existsNews(id));
    }

    @PostMapping
    public void postNews(@RequestHeader("appKey") String appKey,
                         @RequestBody @Valid News news) {
        if(!APP_KEY.equals(appKey))
            throw new SecurityException(appKey + " is incorrect");
        newsService.addNews(news);
    }

    @PutMapping
    public void putNews(@RequestHeader("appKey") String appKey,
                        @RequestBody @Valid News news) {
        if(!APP_KEY.equals(appKey))
            throw new SecurityException(appKey + " is incorrect");
        newsService.putNews(news);
    }

    @DeleteMapping
    public void deleteNews(@RequestHeader("appKey") String appKey,
                           @RequestParam("del_id") String id) {
        if(!APP_KEY.equals(appKey))
            throw new SecurityException(appKey + " is incorrect");
        newsService.deleteById(id);
    }
}
