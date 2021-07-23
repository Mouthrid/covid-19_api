package com.mouthird.devcovid19api.dao.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.nio.channels.Channel;
import java.sql.Timestamp;

/**
 * Class for Video
 * @author RayXie
 */

@Entity
@Table
public class Video {
    /**
     * Video id in database
     */
    @Id
    @NotBlank(message = "id is missing or empty")
    private String id;
    /**
     * Video title
     */
    @NotBlank(message = "title is missing or empty")
    private String title;
    /**
     * Video published date
     */
    @NotNull(message = "newsDate is missing or empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp videoTime;
    /**
     * Video website url
     */
    @NotBlank(message = "newUrl time is missing or empty")
    private String videoUrl;
    /**
     * image url for this Video
     */
    @NotBlank(message = "imgUrl is missing or empty")
    private String imgUrl;
    /**
     * View count of this Video
     */
    @NotNull(message = "viewCount is missing or empty")
    private int viewCount;
    /**
     * View state of this Video
     */
    @NotBlank(message = "viewState is missing or empty")
    private String viewState;
    /**
     * Duration of this Video
     */
    @NotBlank(message = "duration is missing or empty")
    private String duration;
    /**
     * Channel Id of this Video
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "channel_id")
    @NotBlank(message = "ChannelId is missing or empty")
    private Channel channel;

    /**
     * default constructor for Video
     */
    public Video() {}

    /**
     * Parameterized constructor
     * @param id Video Id
     * @param title Video title
     * @param videoUrl Video website url
     * @param imgUrl image url for this Video
     * @param viewCount view count of this Video
     * @param viewState view state of this Video
     * @param duration duration fo this Video
     * @param channel this Video's channel
     */
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

    /**
     * Set Video Id
     */
    public  void setId(String id) {
        this.id = id;
    }

    /**
     * Get Video Id
     * @return returns Video Id
     */
    public String getId() {
        return id;
    }

    /**
     * Get Video title
     * @return returns Video title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set Video title
     * @param title Video title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get Video time
     * @return returns Video time
     */
    public Timestamp getVideoTime() {
        return videoTime;
    }

    /**
     * Set Video time
     * @param videoTime Video time (Auto generate, server time)
     */
    public void setVideoTime(Timestamp videoTime) {
        this.videoTime = videoTime;
    }

    /**
     * Get Video url
     * @return returns video url
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * Set Video url
     * @param videoUrl video url
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * Get Video image url
     * @return returns image url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Set Video image url
     * @param imgUrl image url
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * Get Video view count
     * @return returns view count
     */
    public int getViewCount() {
        return viewCount;
    }

    /**
     * Set view count
     * @param viewCount view count
     */
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * Get view state ('watching', 'waitting', 'saved')
     * @return view state
     */
    public String getViewState() {
        return viewState;
    }

    /**
     * Set view state
     * @param viewState view state ('watching', 'waitting', 'saved')
     */
    public void setViewState(String viewState) {
        this.viewState = viewState;
    }

    /**
     * Get duration '03:35'
     * @return duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Set duration
     * @param duration duration '03:35'
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Get Channel information
     * @return channel object
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * Set Channel object
     * @param channel channel object
     */
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
