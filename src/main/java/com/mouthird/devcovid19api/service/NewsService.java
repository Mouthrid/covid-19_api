package com.mouthird.devcovid19api.service;

import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.dao.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void addNews(List<News> newsList) { newsRepository.saveAll(newsList); }

    public void putNews(News news) { newsRepository.save(news); }

    public Page<News> getNews(int limit) {
        PageRequest pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "newsTime"));
        return newsRepository.findAll(pageable);
    }

    public void deleteById(Long Id) {
        newsRepository.deleteById(Id);
    }

}
