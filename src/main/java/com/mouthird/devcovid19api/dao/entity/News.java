package com.mouthird.devcovid19api.dao.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

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
    @NotBlank(message = "id is missing or empty")
    private String id;
    /**
     * News title
     */
    @NotBlank(message = "title is missing or empty")
    private String title;
    /**
     * News published date
     */
    @NotNull(message = "newsDate is missing or empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp newsTime;
    /**
     * timestamp of web crawler send the news in api
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp crawlTime;
    /**
     * News website url
     */
    @NotBlank(message = "newUrl time is missing or empty")
    private String newsUrl;
    /**
     * image url for this News
     */
    @NotBlank(message = "imgUrl is missing or empty")
    private String imgUrl;
    /**
     * short description for this News
     */
    @NotBlank(message = "description is missing or empty")
    private String description;

    /**
     * default constructor for News
     */
    public News() {}

    /**
     * Parameterized constructor
     * @param id News Id
     * @param title News title
     * @param newsTime News published date, format: 2021-07-18T13:11:26Z
     * @param newsUrl News website url
     * @param imgUrl image url for this News
     * @param description short description for this News
     */
    public News(String id,
                String title,
                Timestamp newsTime,
                String newsUrl,
                String imgUrl,
                String description) {
        this.id = id;
        this.title = title;
        this.newsTime = newsTime;
        this.newsUrl = newsUrl;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    /**
     * Set News Id
     */
    public  void setId(String id) {
        this.id = id;
    }

    /**
     * Get News Id
     * @return returns News Id
     */
    public String getId() {
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
    public Timestamp getNewsTime() {
        return newsTime;
    }

    /**
     * Set News time
     * @param newsTime News time
     */
    public void setNewsTime(Timestamp newsTime) {
        this.newsTime = newsTime;
    }

    /**
     * Get Crawl time
     * @return crawl time
     */
    public Timestamp getCrawlTime() {
        return crawlTime;
    }

    /**
     * Set crawl time (Auto generate, server time)
     * @param crawlTime Crawl time
     */
    public void setCrawlTime(Timestamp crawlTime) {
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
