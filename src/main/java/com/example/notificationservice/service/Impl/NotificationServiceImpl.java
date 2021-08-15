package com.example.notificationservice.service.Impl;

import com.example.notificationservice.entity.ChannelType;
import com.example.notificationservice.entity.Message;
import com.example.notificationservice.exception.BadRequest;
import com.example.notificationservice.service.channel.ChannelFactory;
import com.example.notificationservice.service.IChannel;
import com.example.notificationservice.service.INotificationService;
import com.example.notificationservice.util.EmailValidator;
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

    @Autowired
    EmailValidator emailValidator;

    public NotificationServiceImpl(ChannelFactory factory, EmailValidator emailValidator) {
        this.factory = factory;
        this.emailValidator = emailValidator;
    }

    private AtomicInteger notificationId = new AtomicInteger(1);

    public long notifyAll(Message msg) {
        for (IChannel c : factory.getChannels()) {
            msg.setId(notificationId.getAndIncrement());
            c.notify(msg);
            LOG.debug("ID = " + notificationId + ", Message sent = " + msg);
        }
        return notificationId.longValue();
    }

    public String notify(ChannelType channelType, Message msg) {

        msg.setId(notificationId.getAndIncrement());
        factory.get(channelType).notify(msg);

        LOG.debug("ID = " + notificationId + ", Message sent = " + msg);

        if (channelType == ChannelType.email) {

            /*if (!emailValidator.isValid(msg.getFrom())) {
                throw new BadRequest("From Address", msg.getFrom());
            }
            if (!emailValidator.isValid(msg.getTo())) {
                throw new BadRequest("To Address", msg.getFrom());
            }*/

            return channelType + " sent successfully to: " + msg.getFrom();

        } else if (channelType == ChannelType.sms) {
            return channelType + " sent successfully to: " + msg.getMobile();
        }

        return null;
    }
}
