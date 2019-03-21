package com.zongcc.boot.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zongcc.boot.entity.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @author chunchengzong
 * @date 2018-08-03 16:12
 **/
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send() {
        KafkaMessage message = new KafkaMessage();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        System.out.println("+++++++++++++++++++++  message = " + gson.toJson(message));
        kafkaTemplate.send("test", gson.toJson(message));
    }
}