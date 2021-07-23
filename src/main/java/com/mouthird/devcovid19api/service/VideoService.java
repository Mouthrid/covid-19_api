package com.mouthird.devcovid19api.service;

import com.mouthird.devcovid19api.dao.entity.Video;
import com.mouthird.devcovid19api.dao.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void addVideo(Video video) {
        boolean exists = videoRepository.existsById(video.getId());
        if (exists) {
            throw new IllegalStateException(
                    "Video with id " + video.getId() + " already exists"
            );
        }
        if(video.getVideoTime() == null) {
            Date date = new Date();
            video.setVideoTime(new Timestamp(date.getTime()));
        }
        videoRepository.save(video);
    }

    public void putVideo(Video video) {
        Date date = new Date();
        video.setVideoTime(new Timestamp(date.getTime()));
        videoRepository.save(video);
    }

    public List<Video> getSavedVideo(int limit) {
        PageRequest pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "videoTime"));
        return videoRepository.findByState("saved", pageable);
    }

    public List<Video> getLiveVideo() {
        return videoRepository.findByNotState("saved");
    }

    public void deleteById(String Id) {
        boolean exists = videoRepository.existsById(Id);
        if (!exists) {
            throw new IllegalStateException(
                    "Video with id " + Id + " does not exists"
            );
        }
        videoRepository.deleteById(Id);
    }

}
