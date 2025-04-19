package org.ar.eduutn.frba.dds.mqttpixelgame.Events;

import lombok.Getter;
import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.PixelCanvasCambioDto;
import org.springframework.context.ApplicationEvent;

@Getter
public class CanvasActualizadoEvent extends ApplicationEvent {
  private final PixelCanvasCambioDto cambioDto;

  public CanvasActualizadoEvent(Object source,PixelCanvasCambioDto cambioDto) {
    super(source);
    this.cambioDto = cambioDto;
  }
}
