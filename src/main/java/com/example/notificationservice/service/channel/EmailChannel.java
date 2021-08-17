package com.example.notificationservice.service.channel;

import com.example.notificationservice.entity.ChannelType;
import com.example.notificationservice.entity.MessageEntity;
import com.example.notificationservice.exception.NotFoundException;
import com.example.notificationservice.service.IChannel;
import com.example.notificationservice.util.EmailValidator;
import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;

@Component
public class EmailChannel implements IChannel {

    @Autowired
    EmailValidator emailValidator;

    @Autowired
    it.ozimov.springboot.mail.service.EmailService emailService;

    @Value("${spring.mail.username}")
    String fromEmail;

    @Override
    public void notify(MessageEntity msg) {

        if (!emailValidator.isValid(msg.getFrom())) {
            throw new NotFoundException("Invalid email format in - " + msg.getFrom(), 404);
        }

        if (!emailValidator.isValid(msg.getTo())) {
            throw new NotFoundException("Invalid email format in - " + msg.getTo(), 404);
        }

        try {
            Email email = DefaultEmail.builder()
                    .from(new InternetAddress(fromEmail, "NotificationService"))
                    .to(Lists.newArrayList(new InternetAddress(
                            msg.getTo(), "")))
                    .subject(msg.getSubject())
                    .body(msg.getBody())
                    .encoding("UTF-8").build();

            emailService.send(email);

        } catch (Exception e) {
            throw new NotFoundException("Failed to send message using email channel, exception : " + e.getMessage(), 500);
        }
    }

    @Override
    public boolean supports(ChannelType channelType) {
        return ChannelType.email == channelType;
    }
}
