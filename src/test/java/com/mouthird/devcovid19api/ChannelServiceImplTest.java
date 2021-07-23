package com.mouthird.devcovid19api;

import com.mouthird.devcovid19api.dao.entity.Channel;
import com.mouthird.devcovid19api.dao.repositories.ChannelRepository;
import com.mouthird.devcovid19api.service.ChannelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ChannelServiceImplTest {

    @Autowired
    private ChannelService channelService;

    @MockBean
    private ChannelRepository channelRepository;

    private Channel channel;

    @BeforeEach
    public void setUp() {
        channel = new Channel("1L", "test title", "https://img.jpg");
    }

    @Test
    public void getChannel() {
        List<Channel> channelList = new ArrayList<>();
        for(int i=0; i<5; i++) {
            channelList.add(new Channel(String.valueOf(i), "test title", "https://img.jpg"));
        }
        when(channelRepository.findAll()).thenReturn(channelList);
        List<Channel> response = channelService.getChannel();
        assertEquals(5, response.size());
    }

    @Test
    public void deleteChannel() {
        when(channelRepository.existsById("1L")).thenReturn(true);
        channelService.deleteById("1L");
        verify(channelRepository, times(1)).deleteById("1L");
    }

    @Test
    public void addChannel() {
        channelService.addChannel(channel);
        verify(channelRepository, times(1)).save(channel);
    }

    @Test
    public void putChannel() {
        when(channelRepository.existsById("1LL")).thenReturn(true);
        channelService.putChannel(channel);
        verify(channelRepository, times(1)).save(channel);

    }
}
