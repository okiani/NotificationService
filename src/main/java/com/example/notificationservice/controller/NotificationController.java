package com.example.notificationservice.controller;

import com.example.notificationservice.entity.ChannelType;
import com.example.notificationservice.entity.Message;
import com.example.notificationservice.exception.BadRequest;
import com.example.notificationservice.service.INotificationService;
import com.example.notificationservice.util.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
//@Api(value="Notification APIs")
public class NotificationController {

    private static final Logger LOG = LogManager.getLogger(NotificationController.class);

    @Autowired
    INotificationService notificationService;

    @Autowired
    EmailValidator emailValidator;

    public NotificationController(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/notify/{channelType}")
    public String notify(@PathVariable ChannelType channelType, @RequestBody Message msg) {

        if (ChannelType.email == channelType) {
            if (!emailValidator.isValid(msg.getFrom())) {
                throw new BadRequest("From Address", msg.getFrom());
            }
            if (!emailValidator.isValid(msg.getTo())) {
                throw new BadRequest("To Address", msg.getFrom());
            }
        }

        return notificationService.notify(channelType, msg);
    }

    @PostMapping("/notifyAll")
    public long notifyAll(@RequestBody Message msg) {
        return notificationService.notifyAll(msg);
    }
}
