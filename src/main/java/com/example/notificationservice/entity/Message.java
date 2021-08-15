package com.example.notificationservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class Message {

    private long id;
    private String subject;
    private String from;
    private String to;
    private String body;
    private String sentTime;

    public Message() {
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
