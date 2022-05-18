package com.example.board.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
    스프링에서 제공하는 TextWebSocketHandler 구현체를 상속하여 접속한 사용자들에게 메시지 전송
 */
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {
    // 접속한 사용자의 세션값을 담음
    private static final Map<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<>();

    // 사용자가(브라우저) 웹소켓 서버에 붙게되면 호출
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        CLIENTS.put(session.getId(), session);
    }

    // 접속이 끊어진 사용자가 발생하면 호출
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        CLIENTS.remove(session.getId());
    }

    // CLIENTS 객체에 담긴 세션값들을 가져와서 반복문을 통해 메시지를 발송
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        CLIENTS.forEach((key, value) -> {
            try {
                value.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
