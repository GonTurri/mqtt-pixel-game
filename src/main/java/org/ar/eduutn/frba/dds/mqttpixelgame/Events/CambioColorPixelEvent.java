package org.ar.eduutn.frba.dds.mqttpixelgame.Events;

import lombok.Getter;
import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.CambioColorRequest;
import org.springframework.context.ApplicationEvent;

@Getter
public class CambioColorPixelEvent extends ApplicationEvent {
  private final CambioColorRequest cambioColorRequest;

  public CambioColorPixelEvent(Object source, CambioColorRequest cambioColorRequest) {
    super(source);
    this.cambioColorRequest = cambioColorRequest;
  }
}
