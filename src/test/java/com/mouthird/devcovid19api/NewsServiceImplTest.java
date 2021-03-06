package com.mouthird.devcovid19api;

import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.dao.repositories.NewsRepository;
import com.mouthird.devcovid19api.service.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NewsServiceImplTest {

    @Autowired
    private NewsService newsService;

    @MockBean
    private NewsRepository newsRepository;

    private News news;

    private Date date;

    private Timestamp timestamp;

    @BeforeEach
    public void setUp() {
        date = new Date();
        timestamp = new Timestamp(date.getTime());
        news = new News("1LL", "test title", timestamp, "https://test.com",
                "https://img.jpg", "This is test object.");
    }

    @Test
    public void getNews() {
        List<News> newsList = new ArrayList<>();
        for(int i=0; i<5; i++) {
            newsList.add(new News(String.valueOf(i), "test title", timestamp, "https://test.com",
                    "https://img.jpg", "This is test object."));
        }
        Page<News> newsPage = new PageImpl<>(newsList);
        PageRequest pageable = PageRequest.of(0,5 , Sort.by(Sort.Direction.DESC, "newsTime"));
        when(newsRepository.findAll(pageable)).thenReturn(newsPage);
        List<News> response = newsService.getNews(5);
        assertEquals(5, response.size());
    }

    @Test
    public void deleteNews() {
        when(newsRepository.existsById("1L")).thenReturn(true);
        newsService.deleteById("1L");
        verify(newsRepository, times(1)).deleteById("1L");
    }

    @Test
    public void addNews() {
        newsService.addNews(news);
        verify(newsRepository, times(1)).save(news);
    }

    @Test
    public void putNews() {
        when(newsRepository.existsById("1LL")).thenReturn(true);
        newsService.putNews(news);
        verify(newsRepository, times(1)).save(news);

    }

    @Test
    public void existsNews() {
        when(newsRepository.existsById("1L")).thenReturn(true);
        boolean response = newsService.existsNews("1L");
        assertEquals(true, response);
    }
}
