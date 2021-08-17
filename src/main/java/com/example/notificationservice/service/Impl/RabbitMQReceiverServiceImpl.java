package com.example.notificationservice.service.Impl;

import com.example.notificationservice.dto.MessageDto;
import com.example.notificationservice.entity.ChannelType;
import com.example.notificationservice.entity.MessageEntity;
import com.example.notificationservice.exception.NotFoundException;
import com.example.notificationservice.service.INotificationService;
import com.example.notificationservice.service.IRabbitMQReceiverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@Slf4j
public class RabbitMQReceiverServiceImpl implements IRabbitMQReceiverService {

    @Autowired
    INotificationService notificationService;
    private ModelMapper modelMapper;
    private ObjectMapper objectMapper;

    public RabbitMQReceiverServiceImpl(INotificationService notificationService, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.notificationService = notificationService;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "${notification.rabbitmq.queue}")
    public void receivedMessage(Object msg) {

        try {
            MessageDto messageDto = objectMapper.readValue(modelMapper.map(msg, Message.class).getBody(), MessageDto.class);

            MessageEntity messageEntity = modelMapper.map(messageDto, MessageEntity.class);
//            ChannelType channelType = modelMapper.map(messageEntity.getChannelType(), ChannelType.class);

//            notificationService.notify(channelType, messageEntity);
            notificationService.notifyAll(messageEntity);

        } catch (Exception exception) {
            log.info("Does Not Received Message From RabbitMQ: " + msg);
            throw new NotFoundException("error message: " + exception.getMessage(), 404);
        }
    }

}
