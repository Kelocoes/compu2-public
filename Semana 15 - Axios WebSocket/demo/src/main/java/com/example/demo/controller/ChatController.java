package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.demo.model.ChatMessage;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/sendMessage") // Cambiado para ser más específico // User should use /app/chat/sendMessage to send a message
    public void send(ChatMessage message) {
        System.out.println("Message received: " + message);
        System.out.println("Sender: " + message.getSender());
        System.out.println("Receiver: " + message.getReceiver());
        messagingTemplate.convertAndSend(
                "/subscribeTo/" + message.getReceiver(),
                message
        );
    }
}
