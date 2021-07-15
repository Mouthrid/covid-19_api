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
    private LocalDate newsTime;
    /**
     * time of web crawler get this news
     */
    private LocalDate crawlTime;
    /**
     * News website url
     */
    @NotBlank
    private String newsUrl;
    /**
     * image url for this News
     */
    @NotBlank
    private String imgUrl;
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
     * @param newsTime News published time, format: 2021-07-11
     * @param newsUrl News website url
     * @param imgUrl image url for this News
     * @param description short description for this News
     */
    public News(String title,
                LocalDate newsTime,
                String newsUrl,
                String imgUrl,
                String description) {
        this.title = title;
        this.newsTime = newsTime;
        this.crawlTime = LocalDate.now();
        this.newsUrl = newsUrl;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    /**
     * Parameterized constructor
     * @param id News Id
     * @param title News title
     * @param newsTime News published time, format: 2021-07-11
     * @param newsUrl News website url
     * @param imgUrl image url for this News
     * @param description short description for this News
     */
    public News(Long id,
                String title,
                LocalDate newsTime,
                String newsUrl,
                String imgUrl,
                String description) {
        this.id = id;
        this.title = title;
        this.newsTime = newsTime;
        this.crawlTime = LocalDate.now();
        this.newsUrl = newsUrl;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    /**
     * Set News Id
     */
    public  void setId(Long id) {
        this.id = id;
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

    /**
     * Get News time
     * @return returns News time
     */
    public LocalDate getNewsTime() {
        return newsTime;
    }

    /**
     * Set News time
     * @param newsTime News time
     */
    public void setNewsTime(LocalDate newsTime) {
        this.newsTime = newsTime;
    }

    /**
     * Get Crawl time
     * @return crawl time
     */
    public LocalDate getCrawlTime() {
        return crawlTime;
    }

    /**
     * Set crawl time (Auto generate)
     * @param crawlTime Crawl time
     */
    public void setCrawlTime(LocalDate crawlTime) {
        this.crawlTime = crawlTime;
    }

    /**
     * Get News url
     * @return returns news url
     */
    public String getNewsUrl() {
        return newsUrl;
    }

    /**
     * Set News url
     * @param newsUrl news url
     */
    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    /**
     * Get News image url
     * @return returns image url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Set News image url
     * @param imgUrl image url
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * Get News description
     * @return returns News description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set News description
     * @param description News description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title=" + title +
                ", newsTime=" + newsTime +
                ", crawlTime=" + crawlTime +
                ", newsUrl=" + newsUrl +
                ", imgUrl=" + imgUrl +
                ", description=" + description +
                "}";
    }
}
