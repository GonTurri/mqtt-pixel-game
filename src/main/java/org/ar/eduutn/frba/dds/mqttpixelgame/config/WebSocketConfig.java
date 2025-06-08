package org.ar.eduutn.frba.dds.mqttpixelgame.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config
        .setApplicationDestinationPrefixes("/app")
        .enableStompBrokerRelay("/topic")
        .setRelayHost("localhost")
        .setRelayPort(61613)
        .setClientLogin("guest")
        .setClientPasscode("guest");

  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws").setAllowedOrigins("*");
  }
  @Override
  public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
    registry.setSendBufferSizeLimit(2 * 1024 * 1024); // 2MB
    registry.setSendTimeLimit(30000); // 30 segundos
  }


}
