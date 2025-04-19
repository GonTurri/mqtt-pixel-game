package org.ar.eduutn.frba.dds.mqttpixelgame.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

@Slf4j
public class WebSocketSessionCapturingHandlerDecorator extends WebSocketHandlerDecorator {
  public WebSocketSessionCapturingHandlerDecorator(WebSocketHandler delegate) {
    super(delegate);
  }

  public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    if (session.isOpen()) {
      super.handleMessage(session, message);
    } else {
      log.info("Dropped inbound WebSocket message due to closed session");
    }

  }
}
