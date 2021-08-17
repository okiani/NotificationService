package com.example.notificationservice.controller;

import com.example.notificationservice.entity.ChannelType;
import com.example.notificationservice.entity.MessageEntity;
import com.example.notificationservice.exception.BadRequestException;
import com.example.notificationservice.service.INotificationService;
import com.example.notificationservice.util.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class NotificationController {

    private static final Logger LOG = LogManager.getLogger(NotificationController.class);

    @Autowired
    INotificationService notificationService;

    @Autowired
    EmailValidator emailValidator;

    public NotificationController(INotificationService notificationService, EmailValidator emailValidator) {
        this.notificationService = notificationService;
        this.emailValidator = emailValidator;
    }

    @PostMapping("/notify/{channelType}")
    public String notify(@PathVariable ChannelType channelType, @RequestBody MessageEntity msg) {

        if (ChannelType.email == channelType) {

            if (!emailValidator.isValid(msg.getFrom())) {
                throw new BadRequestException("Invalid value From Address" + msg.getFrom(), 400);
            }
            if (!emailValidator.isValid(msg.getTo())) {
                throw new BadRequestException("Invalid value From Address" + msg.getFrom(), 400);
            }
        }

        return notificationService.notify(channelType, msg);
    }

    @PostMapping("/notifyAll")
    public String notifyAll(@RequestBody MessageEntity msg) {
        return notificationService.notifyAll(msg);
    }
}
