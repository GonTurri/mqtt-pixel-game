package org.ar.eduutn.frba.dds.mqttpixelgame.Events;

import org.springframework.context.ApplicationEvent;

public class ObtenerCanvasEvent extends ApplicationEvent {
  public ObtenerCanvasEvent(Object source) {
    super(source);
  }
}
