package org.ar.eduutn.frba.dds.mqttpixelgame.Events;

import lombok.Getter;
import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.PixelCanvasResponseDto;
import org.springframework.context.ApplicationEvent;

@Getter
public class CanvasActualizadoEvent extends ApplicationEvent {
  private final PixelCanvasResponseDto canvas;

  public CanvasActualizadoEvent(Object source,PixelCanvasResponseDto canvas) {
    super(source);
    this.canvas = canvas;
  }
}
