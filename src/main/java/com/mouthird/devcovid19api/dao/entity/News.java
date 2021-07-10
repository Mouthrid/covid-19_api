package com.mouthird.devcovid19api.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class News {
    @Id
    @SequenceGenerator(
            name = "news_sequence",
            sequenceName = "news_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "news_sequence"
    )
    private Long id;
    private String title;
    private String news_time;
    private LocalDate crawl_time;
    private String news_url;
    private String img_url;
    private String description;

    public News() {}

    public News(Long id,
                String title,
                String news_time,
                String news_url,
                String img_url,
                String description) {
        this.id = id;
        this.title = title;
        this.news_time = news_time;
        this.crawl_time = LocalDate.now();
        this.news_url = news_url;
        this.img_url = img_url;
        this.description = description;
    }

    public News(String title,
                String news_time,
                String news_url,
                String img_url,
                String description) {
        this.title = title;
        this.news_time = news_time;
        this.crawl_time = LocalDate.now();
        this.news_url = news_url;
        this.img_url = img_url;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title=" + title +
                ", news_time=" + news_time +
                ", crawl_time=" + crawl_time +
                ", news_url=" + news_url +
                ", img_url=" + img_url +
                ", description=" + description +
                "}";
    }
}
