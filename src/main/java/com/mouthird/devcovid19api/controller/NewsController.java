package com.mouthird.devcovid19api.controller;

import com.mouthird.devcovid19api.dao.entity.News;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v0/news")
public class NewsController {

    /**
     * Get all News in the database
     * @return News list
     */
    @GetMapping
    public String getNews(@RequestParam("limit") Integer limit){
        return String.valueOf(limit);
    }
}
