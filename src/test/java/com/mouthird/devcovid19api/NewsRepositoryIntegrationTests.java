package com.mouthird.devcovid19api;

import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.dao.entity.QNews;
import com.mouthird.devcovid19api.dao.repositories.NewsRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class NewsRepositoryIntegrationTests {

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private JPAQueryFactory queryFactory;

	private QNews qNews = QNews.news;

	@BeforeEach
	public void setUp() {
		newsRepository.deleteAll();
		for(int i=0; i<5; i++) {
			News news = new News(String.valueOf(i), "test title", LocalDate.parse("2021-07-12"), "https://test.com",
					"https://img.jpg", "This is test object.");
			newsRepository.save(news);
		}
	}

	@Test
	public void getAllNews() {
		List<News> newsList = newsRepository.findAll();
		assertEquals(5, newsList.size());
	}

	@Transactional
	@Test
	public void putNewsDSL() {
		News news = queryFactory.selectFrom(qNews).fetchFirst();
		Long status = queryFactory.update(qNews).where(qNews.id.eq(news.getId()))
				.set(qNews.title, "Change Title").execute();
		assertEquals(1, status);
	}

	@Test
	public void putNewsJPA() {
		News news = queryFactory.selectFrom(qNews).fetchFirst();
		news.setTitle("Change Title");
		newsRepository.save(news);
		News modified = queryFactory.selectFrom(qNews).where(qNews.id.eq(news.getId())).fetchFirst();
		assertEquals(news.getTitle(), modified.getTitle());
	}

	@Transactional
	@Test
	public void deleteNews() {
		News news = queryFactory.selectFrom(qNews).fetchFirst();
		Long status = queryFactory.delete(qNews).where(qNews.id.eq(news.getId())).execute();
		assertEquals(1, status);
	}

}
