package com.mouthird.devcovid19api.dao.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Class for Channel
 * @author RayXie
 */

@Entity
@Table
public class Channel {
    /**
     * Channel id in database
     */
    @Id
    @NotBlank(message = "id is missing or empty")
    private String id;
    /**
     * Channel name
     */
    @NotBlank(message = "name is missing or empty")
    private String name;
    /**
     * image url for this Channel
     */
    @NotBlank(message = "imgUrl is missing or empty")
    private String imgUrl;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "video")
    private Video video;

    /**
     * default constructor for Channel
     */
    public Channel() {}

    /**
     * Parameterized constructor
     * @param id Channel Id
     * @param name Channel name
     * @param imgUrl image url for this Channel
     */
    public Channel(String id,
                   String name,
                   String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    /**
     * Set Channel Id
     */
    public  void setId(String id) {
        this.id = id;
    }

    /**
     * Get Channel Id
     * @return returns Channel Id
     */
    public String getId() {
        return id;
    }

    /**
     * Get Channel name
     * @return returns Channel name
     */
    public String getName() {
        return name;
    }

    /**
     * Set Channel name
     * @param name Channel name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get Channel image url
     * @return returns image url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Set Channel image url
     * @param imgUrl image url
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * Get Video object
     * @return Video object
     */
    public Video getVideo() {
        return video;
    }

    /**
     * Set Video object
     * @param video Video object
     */
    public void setVideo(Video video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name=" + name +
                ", imgUrl=" + imgUrl +
                "}";
    }
}
