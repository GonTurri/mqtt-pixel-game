package org.ar.eduutn.frba.dds.mqttpixelgame.controllers;

import lombok.RequiredArgsConstructor;
import org.ar.eduutn.frba.dds.mqttpixelgame.Events.CanvasActualizadoEvent;
import org.ar.eduutn.frba.dds.mqttpixelgame.Events.CanvasCompletadoEvent;
import org.ar.eduutn.frba.dds.mqttpixelgame.Events.EnviarTodosLosCanvasEvent;
import org.ar.eduutn.frba.dds.mqttpixelgame.Events.ObtenerCanvasEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CanvasWebSocketHandler {

  private final ApplicationEventPublisher publisher;


  private final SimpMessagingTemplate messagingTemplate;
  @MessageMapping("/obtenerCanvas")
  public void obtenerCanvas() {
    publisher.publishEvent(new ObtenerCanvasEvent(this));
  }

  @EventListener
  public void handleEnviarTodosLosCanvas(EnviarTodosLosCanvasEvent event) {
    messagingTemplate.convertAndSend("/topic/canvas.todos", event.getCanvasList());
  }

  @EventListener
  public void handleCanvasActualizado(CanvasActualizadoEvent event) {
    messagingTemplate.convertAndSend("/topic/canvas." + event.getCambioDto().id(), event.getCambioDto());
  }

  @EventListener
  public void handleCanvasCompletado(CanvasCompletadoEvent event) {
    messagingTemplate.convertAndSend("/topic/canvas." + event.getCanvasId() + ".ganador", "¡Canvas completado!");
  }


}
