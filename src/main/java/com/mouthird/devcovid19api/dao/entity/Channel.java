package com.mouthird.devcovid19api.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table
public class Channel {

    @Id
    @Column(name = "channel_id")
    @NotBlank(message = "id is missing or empty")
    private String id;

    @NotBlank(message = "name is missing or empty")
    private String name;

    @NotBlank(message = "imgUrl is missing or empty")
    private String imgUrl;

    public Channel() {}

    public Channel(String id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public  void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
