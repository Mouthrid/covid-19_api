package com.mouthird.devcovid19api;

import com.mouthird.devcovid19api.dao.entity.Channel;
import com.mouthird.devcovid19api.dao.entity.QChannel;
import com.mouthird.devcovid19api.dao.entity.Video;
import com.mouthird.devcovid19api.dao.entity.QVideo;
import com.mouthird.devcovid19api.dao.repositories.VideoRepository;
import com.mouthird.devcovid19api.dao.repositories.ChannelRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VideoRepositoryIntegrationTests {

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private JPAQueryFactory queryFactory;

	private QVideo qVideo = QVideo.video;

	private QChannel qChannel = QChannel.channel;

	@BeforeEach
	public void setUp() {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());

		videoRepository.deleteAll();

		for(int i=0; i<2; i++) {
			Video video = new Video(i + "L", "test title", "https://test.com",
					"https://img.jpg", 10000, "watching", "03:29",
					new Channel("1L", "Mouthrid", "https://mouthrid.jpg"));
			videoRepository.save(video);
		}
		for(int i=2; i<5; i++) {
			Video video = new Video(i + "L", "test title", "https://test.com",
					"https://img.jpg", 10000, "watching", "03:29",
					new Channel("2L", "Mouthrid", "https://mouthrid.jpg"));
			videoRepository.save(video);
		}
	}

	@Test
	public void getAllVideo() {
		List<Video> videoList = videoRepository.findAll();
		assertEquals(5, videoList.size());
		List<Channel> channelList = channelRepository.findAll();
		assertEquals(2, channelList.size());
	}

	@Test
	public void putVideo() {
		Video video = queryFactory.selectFrom(qVideo).fetchFirst();
		video.setTitle("Change Title");
		videoRepository.save(video);
		Video modified = queryFactory.selectFrom(qVideo).where(qVideo.id.eq(video.getId())).fetchFirst();
		assertEquals(video.getTitle(), modified.getTitle());
	}

	@Test
	public void deleteVideo() {
		Long status = videoRepository.deleteById("1L");
		assertEquals(1, status);
	}

}
