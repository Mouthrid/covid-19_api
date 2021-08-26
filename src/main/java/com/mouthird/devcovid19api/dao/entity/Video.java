package com.mouthird.devcovid19api.dao.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table
public class Video {

    @Id
    @NotBlank(message = "id is missing or empty")
    private String id;

    @NotBlank(message = "title is missing or empty")
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp videoTime;

    @NotBlank(message = "newUrl time is missing or empty")
    private String videoUrl;

    @NotBlank(message = "imgUrl is missing or empty")
    private String imgUrl;

    @NotNull(message = "viewCount is missing or empty")
    private int viewCount;

    @NotBlank(message = "viewState is missing or empty")
    private String viewState;

    @NotBlank(message = "duration is missing or empty")
    private String duration;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "channel_id")
    private Channel channel;

    public Video() {}

    public Video(String id,
                 String title,
                 String videoUrl,
                 String imgUrl,
                 int viewCount,
                 String viewState,
                 String duration,
                 Channel channel) {
        this.id = id;
        this.title = title;
        this.videoUrl = videoUrl;
        this.imgUrl = imgUrl;
        this.viewCount = viewCount;
        this.viewState = viewState;
        this.duration = duration;
        this.channel = channel;
    }

    public Video(String id,
                 String title,
                 Timestamp videoTime,
                 String videoUrl,
                 String imgUrl,
                 int viewCount,
                 String viewState,
                 String duration,
                 Channel channel) {
        this.id = id;
        this.title = title;
        this.videoTime = videoTime;
        this.videoUrl = videoUrl;
        this.imgUrl = imgUrl;
        this.viewCount = viewCount;
        this.viewState = viewState;
        this.duration = duration;
        this.channel = channel;
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

    public Timestamp getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Timestamp videoTime) {
        this.videoTime = videoTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getViewCount() {
        return viewCount;
    }


    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }


    public String getViewState() {
        return viewState;
    }


    public void setViewState(String viewState) {
        this.viewState = viewState;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title=" + title +
                ", videoTime=" + videoTime +
                ", videoUrl=" + videoUrl +
                ", imgUrl=" + imgUrl +
                ", viewCount=" + viewCount +
                ", viewState=" + viewState +
                ", duration=" + duration +
                ", channel=" + channel +
                "}";
    }
}
