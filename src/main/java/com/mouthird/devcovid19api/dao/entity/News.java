package com.mouthird.devcovid19api.dao.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table
public class News {

    @Id
    @NotBlank(message = "id is missing or empty")
    private String id;

    @NotBlank(message = "title is missing or empty")
    private String title;

    @NotNull(message = "newsDate is missing or empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp newsTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp crawlTime;

    @NotBlank(message = "newUrl time is missing or empty")
    private String newsUrl;

    @NotBlank(message = "imgUrl is missing or empty")
    private String imgUrl;

    @NotBlank(message = "description is missing or empty")
    private String description;

    public News() {}

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

    public  void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Timestamp newsTime) {
        this.newsTime = newsTime;
    }

    public Timestamp getCrawlTime() {
        return crawlTime;
    }

    public void setCrawlTime(Timestamp crawlTime) {
        this.crawlTime = crawlTime;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

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
