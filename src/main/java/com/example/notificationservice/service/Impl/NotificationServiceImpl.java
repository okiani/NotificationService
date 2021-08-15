package com.example.notificationservice.service.Impl;

import com.example.notificationservice.entity.ChannelType;
import com.example.notificationservice.entity.Message;
import com.example.notificationservice.service.channel.ChannelFactory;
import com.example.notificationservice.service.IChannel;
import com.example.notificationservice.service.INotificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class NotificationServiceImpl implements INotificationService {

    private static final Logger LOG = LogManager.getLogger(NotificationServiceImpl.class);

    @Autowired
    ChannelFactory factory;

    public NotificationServiceImpl(ChannelFactory factory) {
        this.factory = factory;
    }

    private AtomicInteger notificationId = new AtomicInteger(1);

    /**
     * Notifies channel identified by given channelType with the given message.
     *
     * @param msg The message includes from, to, subject, body
     */
    public long notifyAll(Message msg) {
        for (IChannel c : factory.getChannels()) {
            msg.setId(notificationId.getAndIncrement());
            c.notify(msg);
            LOG.debug("ID = " + notificationId + ", Message sent = " + msg);
        }
        return notificationId.longValue();
    }

    /**
     * Notifies all configured channels(like slack and email) with the given message.
     *
     * @param channelType Type of chanel to notify - slack and email
     * @param msg         The message includes from, to, subject, body
     */
    public String notify(ChannelType channelType, Message msg) {
        msg.setId(notificationId.getAndIncrement());
        factory.get(channelType).notify(msg);
        LOG.debug("ID = " + notificationId + ", Message sent = " + msg);
        return channelType + " sent successfully to: " + msg.getFrom();
    }
}
