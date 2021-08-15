package com.example.notificationservice.service.channel;

import com.example.notificationservice.entity.ChannelType;
import com.example.notificationservice.entity.Message;
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
                .orElseThrow(() -> new RuntimeException("No channel found with type : "+c));
    }

    public void notifyAll(Message msg) {
        for(IChannel c : channelList) {
            c.notify(msg);
        }
    }

    public List<IChannel> getChannels() {
        return channelList;
    }
}
