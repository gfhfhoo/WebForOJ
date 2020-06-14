package me.endnether.webforoj.queue;

import me.endnether.webforoj.Util;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;


@Component
public class Consumer {

    @RabbitListener(queuesToDeclare = @Queue("Trace"))
    public void getTrace(String msg) {
        Map<String, Object> map = Util.getMap(msg);

    }
}
