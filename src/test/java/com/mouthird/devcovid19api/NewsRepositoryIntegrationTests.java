package com.mouthird.devcovid19api;

import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.dao.repositories.NewsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NewsRepositoryIntegrationTests {

	@Autowired
	private NewsRepository newsRepository;

	@BeforeEach
	public void init() {
		for(int i=0; i<5; i++) {
			News news = new News("test title", "7.12", "https://test.com",
					"https://img.jpg", "This is test object.");
			newsRepository.save(news);
		}
	}

	@Test
	public void getAllNews() {
		List<News> newsList = newsRepository.findAll();
		assertEquals(5, newsList.size());

		Long id = 1L;
		for(News x : newsList)
			assertEquals(id++, x.getId());
	}

}
