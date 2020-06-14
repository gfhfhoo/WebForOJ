package me.endnether.webforoj.websocket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class CoreResultSocket {
    @Autowired
    public static SimpMessagingTemplate simpMessagingTemplate;

}
