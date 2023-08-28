package com.epam.uladzislau.processor.service;

import com.epam.uladzislau.processor.dto.SongDto;
import com.epam.uladzislau.processor.feign.ServiceSongs;
import com.epam.uladzislau.processor.mq.MQConfig;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    ServiceSongs feign;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(SongDto message) {
        feign.postSong(message);
    }

}