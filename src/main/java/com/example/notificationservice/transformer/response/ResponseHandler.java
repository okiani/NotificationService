package com.example.notificationservice.transformer.response;

import com.example.notificationservice.constant.JsonDictionary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, int errorCode ,HttpStatus status, Object responseObj) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put(JsonDictionary.MESSAGE, message);
//        map.put(JsonDictionary.STATUS_CODE, status.value());
        map.put(JsonDictionary.STATUS_CODE, errorCode);
        map.put(JsonDictionary.DATA, responseObj);

        return new ResponseEntity<Object>(map,status);
    }
}
