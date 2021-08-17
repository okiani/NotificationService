package com.example.notificationservice.service.channel;

import com.example.notificationservice.entity.ChannelType;
import com.example.notificationservice.entity.MessageEntity;
import com.example.notificationservice.exception.NotFoundException;
import com.example.notificationservice.service.IChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChannelFactory {

    private final List<IChannel> channelList;

    @Autowired
    public ChannelFactory(List<IChannel> channelList) {
        this.channelList = channelList;
    }

    public IChannel get(ChannelType c) {
        return channelList
                .stream()
                .filter(service -> service.supports(c))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No channel found with type : " + c, 404));
    }

    public void notifyAll(MessageEntity msg) {
        for(IChannel c : channelList) {
            c.notify(msg);
        }
    }

    public List<IChannel> getChannels() {
        return channelList;
    }
}
