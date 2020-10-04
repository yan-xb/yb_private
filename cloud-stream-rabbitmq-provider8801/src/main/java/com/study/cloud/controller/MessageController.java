package com.study.cloud.controller;

import com.study.cloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {

    @Resource
    private IMessageProvider iMessageProvider;

    @GetMapping(value = "send/message")
    public String sendMessage(){
       return iMessageProvider.send();
    }

}
