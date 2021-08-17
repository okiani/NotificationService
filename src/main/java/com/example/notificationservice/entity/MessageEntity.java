package com.example.notificationservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class MessageEntity {

    private long id;
    private String channelType;

    //email
    private String subject;
    private String from;
    private String to;
    private String body;
    private String sentTime;

    // sms
    private String mobile;
    private String text;
    /*private Double balance;
    private Double withdraw;
    private Timestamp currentDate;*/

    public MessageEntity() {
        this.sentTime = LocalDateTime.now().toString();
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", body='" + body + '\'' +
                ", sentTime='" + sentTime + '\'' +
                '}';
    }
}
