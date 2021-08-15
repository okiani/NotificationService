package com.example.notificationservice.service;


import com.example.notificationservice.entity.ChannelType;
import com.example.notificationservice.entity.Message;

public interface INotificationService {

    long notifyAll(Message message);
    String notify(ChannelType channelType, Message message);
}
