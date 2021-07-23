package com.mouthird.devcovid19api.service;

import com.mouthird.devcovid19api.dao.entity.Channel;
import com.mouthird.devcovid19api.dao.repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public void addChannel(Channel channel) {
        boolean exists = channelRepository.existsById(channel.getId());
        if (exists) {
            throw new IllegalStateException(
                    "Channel with id " + channel.getId() + " already exists"
            );
        }
        channelRepository.save(channel);
    }

    public void putChannel(Channel channel) {
        channelRepository.save(channel);
    }

    public List<Channel> getChannel() {
        return channelRepository.findAll();
    }

    public void deleteById(String Id) {
        boolean exists = channelRepository.existsById(Id);
        if (!exists) {
            throw new IllegalStateException(
                    "Channel with id " + Id + " does not exists"
            );
        }
        channelRepository.deleteById(Id);
    }

}
