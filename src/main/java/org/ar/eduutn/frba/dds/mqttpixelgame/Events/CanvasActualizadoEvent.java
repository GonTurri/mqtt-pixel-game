package org.ar.eduutn.frba.dds.mqttpixelgame.Events;

import lombok.Getter;
import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.PixelCanvasCambioResponseDto;
import org.springframework.context.ApplicationEvent;

@Getter
public class CanvasActualizadoEvent extends ApplicationEvent {
  private final PixelCanvasCambioResponseDto cambioDto;

  public CanvasActualizadoEvent(Object source, PixelCanvasCambioResponseDto cambioDto) {
    super(source);
    this.cambioDto = cambioDto;
  }
}
