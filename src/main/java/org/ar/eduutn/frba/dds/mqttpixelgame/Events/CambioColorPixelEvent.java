package org.ar.eduutn.frba.dds.mqttpixelgame.Events;

import lombok.Getter;
import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.CambioColorRequest;
import org.springframework.context.ApplicationEvent;

@Getter
public class CambioColorPixelEvent extends ApplicationEvent {
  private final String canvasId;
  private final CambioColorRequest cambioColorRequest;

  public CambioColorPixelEvent(Object source, String canvasId, CambioColorRequest cambioColorRequest) {
    super(source);
    this.canvasId = canvasId;
    this.cambioColorRequest = cambioColorRequest;
  }
}
