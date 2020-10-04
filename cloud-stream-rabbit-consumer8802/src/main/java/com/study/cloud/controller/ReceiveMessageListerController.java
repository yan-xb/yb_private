package com.study.cloud.controller;

import javafx.scene.control.Skin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
/**
 * 将消息消费者注入到组件中
 * */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListerController {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("我是1号消费者："+ message.getPayload() + "端口号： "+serverPort);
    }

}
