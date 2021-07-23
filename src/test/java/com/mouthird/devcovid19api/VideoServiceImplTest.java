package com.mouthird.devcovid19api;

import com.mouthird.devcovid19api.dao.entity.Channel;
import com.mouthird.devcovid19api.dao.entity.Video;
import com.mouthird.devcovid19api.dao.repositories.VideoRepository;
import com.mouthird.devcovid19api.service.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VideoServiceImplTest {

    @Autowired
    private VideoService videoService;

    @MockBean
    private VideoRepository videoRepository;

    private Video video;

    private Channel channel;

    @BeforeEach
    public void setUp() {
        channel = new Channel("1L", "test channel", "https://channel.jpg");
        video = new Video("1L", "test title", "https://test.com",
                "https://img.jpg", 10000, "watching", "03:29", channel);
    }

    @Test
    public void getSavedVideo() {
        List<Video> videoList = new ArrayList<>();
        for(int i=0; i<5; i++) {
            videoList.add(new Video(i + "L", "test title", "https://test.com",
                    "https://img.jpg", 10000, "saved", "03:29",
                    new Channel("1L", "Mouthrid", "https://mouthrid.jpg")));
        }
        Page<Video> videoPage = new PageImpl<>(videoList);
        PageRequest pageable = PageRequest.of(0,5 , Sort.by(Sort.Direction.DESC, "videoTime"));
        when(videoRepository.findByState("saved", pageable)).thenReturn(videoPage.getContent());
        List<Video> response = videoService.getSavedVideo(5);
        assertEquals(5, response.size());
    }

    @Test
    public void getLiveVideo() {
        List<Video> videoList = new ArrayList<>();
        for(int i=0; i<5; i++) {
            videoList.add(new Video(i + "L", "test title", "https://test.com",
                    "https://img.jpg", 10000, "watching", "03:29",
                    new Channel("1L", "Mouthrid", "https://mouthrid.jpg")));
        }
        when(videoRepository.findByNotState("saved")).thenReturn(videoList);
        List<Video> response = videoService.getLiveVideo();
        assertEquals(5, response.size());

    }

    @Test
    public void deleteVideo() {
        when(videoRepository.existsById("1L")).thenReturn(true);
        videoService.deleteById("1L");
        verify(videoRepository, times(1)).deleteById("1L");
    }

    @Test
    public void addVideo() {
        videoService.addVideo(video);
        verify(videoRepository, times(1)).save(video);
    }

    @Test
    public void putVideo() {
        when(videoRepository.existsById("1LL")).thenReturn(true);
        videoService.putVideo(video);
        verify(videoRepository, times(1)).save(video);

    }
}
