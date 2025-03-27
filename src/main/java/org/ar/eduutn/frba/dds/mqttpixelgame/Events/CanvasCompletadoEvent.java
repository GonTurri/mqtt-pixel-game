package org.ar.eduutn.frba.dds.mqttpixelgame.Events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CanvasCompletadoEvent extends ApplicationEvent {

  private final String canvasId;

  public CanvasCompletadoEvent(Object source, String canvasId) {
    super(source);
    this.canvasId = canvasId;
  }
}
