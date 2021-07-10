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
		newsRepository.deleteAll();
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
	}

	@Test
	public void putNews() {
		News news = newsRepository.findById(1L)
				.orElseThrow(() -> new IllegalStateException(
				"news with id 1L does not exists"
		));
		news.setTitle("Change Title");
		newsRepository.save(news);
		News modifiy_news = newsRepository.findById(1L)
				.orElseThrow(() -> new IllegalStateException(
						"news with id 1L does not exists"
				));
		assertEquals("Change Title", modifiy_news.getTitle());
	}

	@Test
	public void deleteNews() {
		List<News> newsList = newsRepository.findAll();
		newsRepository.delete(newsList.get(0));
		newsList = newsRepository.findAll();
		assertEquals(4, newsList.size());
	}

}
