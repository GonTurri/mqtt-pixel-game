package org.ar.eduutn.frba.dds.mqttpixelgame.Events;

import lombok.Getter;
import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.PixelCanvasResponseDto;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.PixelCanvas;
import org.springframework.context.ApplicationEvent;
import java.util.List;
@Getter
public class EnviarTodosLosCanvasEvent extends ApplicationEvent {
  private final List<PixelCanvasResponseDto> canvasList;

  public EnviarTodosLosCanvasEvent(Object source, List<PixelCanvasResponseDto> canvasList) {
    super(source);
    this.canvasList = canvasList;
  }
}
