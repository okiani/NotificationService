package com.example.notificationservice.entity;

public enum ChannelType {

    slack, email;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
