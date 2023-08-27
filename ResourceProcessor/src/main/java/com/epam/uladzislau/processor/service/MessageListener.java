package com.epam.uladzislau.processor.service;

import com.epam.uladzislau.processor.dto.SongDto;
import com.epam.uladzislau.processor.mq.MQConfig;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(SongDto message) {
        System.out.println(message);
    }

}