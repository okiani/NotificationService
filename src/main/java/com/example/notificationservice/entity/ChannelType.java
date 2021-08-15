package com.example.notificationservice.entity;

public enum ChannelType {

    slack, email, sms;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
