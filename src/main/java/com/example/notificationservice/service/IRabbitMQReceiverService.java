package com.example.notificationservice.service;


import java.io.IOException;

public interface IRabbitMQReceiverService {

    void receivedMessage(Object message) throws IOException;
}
