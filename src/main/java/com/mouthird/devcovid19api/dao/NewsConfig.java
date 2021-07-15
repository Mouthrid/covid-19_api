package com.mouthird.devcovid19api.dao;

import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.dao.repositories.NewsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class NewsConfig {

    @Bean
    CommandLineRunner commandLineRunner(NewsRepository newsRepository) {
        return args -> {
            News news1 = new News("test title1", LocalDate.parse("2021-10-12"), "https://test.com",
                    "https://img.jpg", "This is test object.");
            News news2 = new News("test title2", LocalDate.parse("2021-07-12"), "https://test.com",
                    "https://img.jpg", "This is test object.");
            News news3 = new News("test title3", LocalDate.parse("2021-09-12"), "https://test.com",
                    "https://img.jpg", "This is test object.");
            newsRepository.saveAll(Arrays.asList(news1, news2, news3));
        };
    }


}
