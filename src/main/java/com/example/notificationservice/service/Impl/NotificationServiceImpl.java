package com.example.notificationservice.service.Impl;

import com.example.notificationservice.entity.ChannelType;
import com.example.notificationservice.entity.MessageEntity;
import com.example.notificationservice.service.channel.ChannelFactory;
import com.example.notificationservice.service.IChannel;
import com.example.notificationservice.service.INotificationService;
import com.example.notificationservice.util.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class NotificationServiceImpl implements INotificationService {

    private static final Logger LOG = LogManager.getLogger(NotificationServiceImpl.class);

    @Autowired
    ChannelFactory factory;

    @Autowired
    EmailValidator emailValidator;

    @Autowired
    private ModelMapper modelMapper;

    public NotificationServiceImpl(ChannelFactory factory, EmailValidator emailValidator, ModelMapper modelMapper) {
        this.factory = factory;
        this.emailValidator = emailValidator;
        this.modelMapper = modelMapper;
    }

    private AtomicInteger notificationId = new AtomicInteger(1);

    public String notifyAll(MessageEntity msg) {
        for (IChannel c : factory.getChannels()) {
            msg.setId(notificationId.getAndIncrement());
            c.notify(msg);
        }
        return factory.getChannels() + " sent successfully";
    }

    public String notify(ChannelType channelType, MessageEntity msg) {
        msg.setId(notificationId.getAndIncrement());
        factory.get(channelType).notify(msg);

        ChannelType channelTypeEnum = modelMapper.map(msg.getChannelType(), ChannelType.class);

        if (channelTypeEnum.equals(ChannelType.email)) {
            return channelType + " sent successfully to: " + msg.getTo();

        } else if (channelTypeEnum.equals(ChannelType.sms)) {
            return channelType + " sent successfully to: " + msg.getMobile();
        }

        return null;
    }
}
