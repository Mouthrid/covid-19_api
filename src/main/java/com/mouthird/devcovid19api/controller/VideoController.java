package com.mouthird.devcovid19api.controller;

import com.mouthird.devcovid19api.dao.entity.Video;
import com.mouthird.devcovid19api.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="api/v0/video")
public class VideoController {

    private final VideoService videoService;

    @Value("${APP_KEY}")
    private String APP_KEY;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @CrossOrigin
    @GetMapping(params = {"viewState", "limit"})
    public List<Video> getVideo(@RequestParam("viewState") String viewState,
                               @RequestParam("limit") Integer limit) {
        return videoService.getVideo(viewState, limit);
    }

    @PostMapping
    public void postVideo(@RequestHeader("appKey") String appKey,
                          @RequestBody @Valid Video video) {
        if(!APP_KEY.equals(appKey))
            throw new SecurityException(appKey + " is incorrect");
        videoService.addVideo(video);
    }

    @PutMapping
    public void putVideo(@RequestHeader("appKey") String appKey,
                         @RequestBody @Valid Video video) {
        if(!APP_KEY.equals(appKey))
            throw new SecurityException(appKey + " is incorrect");
        videoService.putVideo(video);
    }

    @DeleteMapping
    public void deleteVideo(@RequestHeader("appKey") String appKey,
                            @RequestParam("id") String id) {
        if(!APP_KEY.equals(appKey))
            throw new SecurityException(appKey + " is incorrect");
        videoService.deleteById(id);
    }
}
