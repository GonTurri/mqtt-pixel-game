package org.ar.eduutn.frba.dds.mqttpixelgame.mappers;

import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.CambioColorRequest;
import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.PixelCanvasCambioDto;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.Color;

public class CambioDtoMapper {
  public static PixelCanvasCambioDto fromCambioColorRequest(String id, CambioColorRequest request){
    Color c = ColorMapper.colorFromString(request.color());
    return PixelCanvasCambioDto.builder()
        .id(id)
        .fila(request.x())
        .columna(request.y())
        .color(ColorMapper.ColorToRgbString(c))
        .build();
  }
}
