package com.example.notificationservice.service;


import com.example.notificationservice.entity.ChannelType;
import com.example.notificationservice.entity.MessageEntity;

public interface INotificationService {

    String notifyAll(MessageEntity message);

    String notify(ChannelType channelType, MessageEntity message);
}
