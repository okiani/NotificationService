package com.example.notificationservice.service;

import com.example.notificationservice.entity.ChannelType;
import com.example.notificationservice.entity.MessageEntity;

public interface IChannel {

    default void notify(MessageEntity msg) {
        throw new RuntimeException("Notify method is not implemented yet.");
    }

    default boolean supports(ChannelType type) {
        throw new RuntimeException("Notify method is not implemented yet.");
    }
}
