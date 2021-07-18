package com.mouthird.devcovid19api.service;

import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.dao.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void addNews(News news) {
        boolean exists = newsRepository.existsById(news.getId());
        if (exists) {
            throw new IllegalStateException(
                    "News with id " + news.getId() + " already exists"
            );
        }
        if(news.getCrawlTime() == null) {
            Date date = new Date();
            news.setCrawlTime(new Timestamp(date.getTime()));
        }
        newsRepository.save(news);
    }

    public void putNews(News news) {
        boolean exists = newsRepository.existsById(news.getId());
        if (!exists) {
            throw new IllegalStateException(
                    "News with id " + news.getId() + " does not exists"
            );
        }
        if(news.getCrawlTime() == null) {
            Date date = new Date();
            news.setCrawlTime(new Timestamp(date.getTime()));
        }
        System.out.println(news);
        newsRepository.save(news);
    }

    public List<News> getNews(int limit) {
        PageRequest pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "newsTime"));
        return newsRepository.findAll(pageable).getContent();
    }

    public void deleteById(String Id) {
        boolean exists = newsRepository.existsById(Id);
        if (!exists) {
            throw new IllegalStateException(
                    "News with id " + Id + " does not exists"
            );
        }
        newsRepository.deleteById(Id);
    }

    public boolean existsNews(String Id) {
        return newsRepository.existsById(Id);
    }

}
