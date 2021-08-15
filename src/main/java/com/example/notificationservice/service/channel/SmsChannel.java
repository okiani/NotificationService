package com.example.notificationservice.service.channel;

import com.example.notificationservice.entity.ChannelType;
import com.example.notificationservice.entity.Message;
import com.example.notificationservice.service.IChannel;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class SmsChannel implements IChannel {

    @Value("${twilio.account_sid}")
    private String accountSID;

    @Value("${twilio.auth_token}")
    private String accountAuthToken;

    @Value("${twilio.from_number}")
    private String twilloSenderNumber;

    public void notify(Message msg) {
        try {
            Twilio.init(accountSID, accountAuthToken);

            String smsText = msg.getText();
            String mobileNumber = msg.getMobile();

            PhoneNumber recieverPhoneNumber = new PhoneNumber(mobileNumber);
            PhoneNumber senderTwilloPhoneNumber = new PhoneNumber(twilloSenderNumber);

            MessageCreator creator = com.twilio.rest.api.v2010.account.Message.creator(recieverPhoneNumber, senderTwilloPhoneNumber, smsText);
            creator.create();

        } catch (Exception e) {
            throw new RuntimeException("Failed to send message using sms channel, exception : " + e.getMessage(), e);
        }

    }

    @Override
    public boolean supports(ChannelType channelType) {
        return ChannelType.sms == channelType;
    }
}
