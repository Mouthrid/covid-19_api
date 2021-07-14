package com.mouthird.devcovid19api;

import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.dao.repositories.NewsRepository;
import com.mouthird.devcovid19api.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NewsServiceImplTest {

    @Autowired
    private NewsService newsService;

    @MockBean
    private NewsRepository newsRepository;

    @Test
    public void getNews() {
        List<News> newsList = new ArrayList<>();
        for(int i=0; i<5; i++) {
            newsList.add(new News("test title", LocalDate.parse("2021-07-12"), "https://test.com",
                    "https://img.jpg", "This is test object."));
        }
        when(newsRepository.findAll()).thenReturn(newsList);
        List<News> response = newsService.getNews();
        assertEquals(5, response.size());
    }

    @Test
    public void deleteNews() {
        newsService.deleteById(1L);
        verify(newsRepository, times(1)).deleteById(1L);
    }

    @Test
    public void addNews() {
        List<News> newsList = new ArrayList<>();
        for(int i=0; i<5; i++) {
            newsList.add(new News("test title", LocalDate.parse("2021-07-12"), "https://test.com",
                    "https://img.jpg", "This is test object."));
        }
        newsService.addNews(newsList);
        verify(newsRepository, times(1)).saveAll(newsList);
    }

    @Test
    public void putNews() {

    }
}
