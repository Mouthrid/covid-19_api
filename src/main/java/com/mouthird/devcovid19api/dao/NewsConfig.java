package com.mouthird.devcovid19api.dao;

import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.dao.repositories.NewsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

@Configuration
public class NewsConfig {
    /**
    @Bean
    CommandLineRunner commandLineRunner(NewsRepository newsRepository) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return args -> {
            News news1 = new News("1L", "test title1", timestamp, "https://test.com",
                    "https://img.jpg", "This is test object.");
            News news2 = new News("2L", "test title2", timestamp, "https://test.com",
                    "https://img.jpg", "This is test object.");
            News news3 = new News("3L", "test title3", timestamp, "https://test.com",
                    "https://img.jpg", "This is test object.");
            newsRepository.saveAll(Arrays.asList(news1, news2, news3));
        };
    }
    **/

}
