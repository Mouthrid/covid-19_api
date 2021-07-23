package com.mouthird.devcovid19api.dao;

import com.mouthird.devcovid19api.dao.entity.Channel;
import com.mouthird.devcovid19api.dao.entity.Video;
import com.mouthird.devcovid19api.dao.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class VideoConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(VideoRepository videoRepository) {
//        return args -> {
//            Video video1 = new Video("1L", "test title", "https://test.com",
//                    "https://img.jpg", 10000, "watching", "03:29",
//                    new Channel("1L", "Mouthrid", "https://mouthrid.jpg"));
//            Video video2 = new Video("2L", "test title", "https://test.com",
//                    "https://img.jpg", 10000, "waiting", "03:29",
//                    new Channel("2L", "Mouthrid", "https://mouthrid.jpg"));
//            Video video3 = new Video("3L", "test title", "https://test.com",
//                    "https://img.jpg", 10000, "saved", "03:29",
//                    new Channel("2L", "Mouthrid", "https://mouthrid.jpg"));
//            Video video4 = new Video("4L", "test title", "https://test.com",
//                    "https://img.jpg", 10000, "saved", "03:29",
//                    new Channel("2L", "Mouthrid", "https://mouthrid.jpg"));
//            videoRepository.saveAll(Arrays.asList(video1, video2, video3, video4));
//        };
//    }
}
