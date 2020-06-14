package me.endnether.webforoj.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private static RabbitTemplate rabbitTemplate;

    @Autowired
    void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public static void sendCores(String msg) {
        rabbitTemplate.convertAndSend("Task", msg);
    }

}
