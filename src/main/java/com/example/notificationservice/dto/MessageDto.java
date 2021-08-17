package com.example.notificationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@Data
public class MessageDto {
    @JsonProperty("body")
    private String body;

    @JsonProperty("from")
    private String from;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("to")
    private String to;

    @JsonProperty("channel_type")
    private String channelType;


    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("text")
    private String text;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("withdraw")
    private Double withdraw;

    @JsonProperty("current_date")
    private Timestamp currentDate;

}
