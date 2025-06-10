package org.ar.eduutn.frba.dds.mqttpixelgame.service;

import lombok.RequiredArgsConstructor;
import org.ar.eduutn.frba.dds.mqttpixelgame.Events.CambioColorPixelEvent;
import org.ar.eduutn.frba.dds.mqttpixelgame.Events.CanvasActualizadoEvent;
import org.ar.eduutn.frba.dds.mqttpixelgame.Events.CanvasCompletadoEvent;
import org.ar.eduutn.frba.dds.mqttpixelgame.Events.EnviarTodosLosCanvasEvent;
import org.ar.eduutn.frba.dds.mqttpixelgame.Events.ObtenerCanvasEvent;
import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.CambioColorRequest;
import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.PixelCanvasResponseDto;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.PixelCanvas;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.repositories.CanvasRepository;
import org.ar.eduutn.frba.dds.mqttpixelgame.exceptions.CoordenadasFueraDeRangoException;
import org.ar.eduutn.frba.dds.mqttpixelgame.mappers.CambioDtoMapper;
import org.ar.eduutn.frba.dds.mqttpixelgame.mappers.ColorMapper;
import org.ar.eduutn.frba.dds.mqttpixelgame.mappers.PixelCanvasDtoMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CanvasService {
  private final CanvasRepository canvasRepository;

  private final ApplicationEventPublisher publisher;

  @EventListener
  public void handleObtenerCanvas(ObtenerCanvasEvent event) {
    List<PixelCanvasResponseDto> canvasList = canvasRepository.obtenerTodos()
        .stream().map(PixelCanvasDtoMapper::fromPixelCanvas)
        .toList();
    publisher.publishEvent(new EnviarTodosLosCanvasEvent(this, canvasList));
  }

  @EventListener
  public void handleCambioColorEvent(CambioColorPixelEvent event) {
    try {

      llenarPixel(event.getCambioColorRequest());
    } catch (CoordenadasFueraDeRangoException ignored) {
    }
  }

  public void llenarPixel(CambioColorRequest request) throws CoordenadasFueraDeRangoException {
    this.canvasRepository.obtenerPorId(request.id())
        .ifPresent(canvas -> {
          synchronized (canvas) {
            var resultado = canvas.rellenarPixel(request.x(), request.y(), ColorMapper.colorFromString(request.color()));
            if (resultado.huboCambio()) {
              this.canvasRepository.actualizar(canvas.getId(), canvas);
              publisher.publishEvent(new CanvasActualizadoEvent(this, CambioDtoMapper.fromCambioColorRequest(request)));
              verificarSiSeLogroImagen(canvas);
            }
          }
        });
  }

  private void verificarSiSeLogroImagen(PixelCanvas canvas) {
    if (canvas.logroSuTarget()) {
      publisher.publishEvent(new CanvasCompletadoEvent(this, canvas.getId()));
    }
  }
}
