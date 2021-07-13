package com.mouthird.devcovid19api.service;

import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.dao.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void addNews(List<News> newsList) {
        newsRepository.saveAll(newsList);
    }

    public List<News> getNews() {
        return newsRepository.findAll();
    }

    public void deleteById(Long Id) {
        newsRepository.deleteById(Id);
    }
}
