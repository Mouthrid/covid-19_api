package com.mouthird.devcovid19api.dao.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Class for News
 * @author RayXie
 */

@Entity
@Table
public class News {
    /**
     * News id in database
     */
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
    /**
     * News title
     */
    @NotBlank
    private String title;
    /**
     * News published time
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate news_time;
    /**
     * time of web crawler get this news
     */
    private LocalDate crawl_time;
    /**
     * News website url
     */
    @NotBlank
    private String news_url;
    /**
     * image url for this News
     */
    @NotBlank
    private String img_url;
    /**
     * short description for this News
     */
    @NotBlank
    private String description;

    /**
     * default constructor for News
     */
    public News() {}

    /**
     * Parameterized constructor
     * @param title News title
     * @param news_time News published time, format: 2021-07-11
     * @param news_url News website url
     * @param img_url image url for this News
     * @param description short description for this News
     */
    public News(String title,
                LocalDate news_time,
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

    /**
     * Get News Id
     * @return returns News Id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get News title
     * @return returns News title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set News title
     * @param title News title
     */
    public void setTitle(String title) {
        this.title = title;
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
