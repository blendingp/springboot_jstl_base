package com.example.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 *  웹소켓 설정
 */
@Configuration // 설정 클래스 선언
@EnableWebSocket // 웹소켓 설정 활성화
public class WebSocketConfig implements WebSocketConfigurer {
    private final ChatWebSocketHandler webSocketHandler;

    public WebSocketConfig(ChatWebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    /*
        WebSocketHandler 요청 매핑 을 구성
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(webSocketHandler, "/ws/chat") // 호출 path 지정
                .setAllowedOrigins("*"); // 허용 도메인
    }
}
