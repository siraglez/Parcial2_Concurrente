package org.example.parcial2_concurrente.configuracion;

import org.example.parcial2_concurrente.visualizacion.GaltonWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new GaltonWebSocketHandler(), "/galtonBoard").setAllowedOrigins("*");
    }

    @Bean
    public GaltonWebSocketHandler galtonWebSocketHandler() {
        return new GaltonWebSocketHandler();
    }
}
