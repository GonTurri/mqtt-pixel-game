package org.ar.eduutn.frba.dds.mqttpixelgame.mappers;

import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.CambioColorRequest;
import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.PixelCanvasCambioResponseDto;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.Color;

public class CambioDtoMapper {
  public static PixelCanvasCambioResponseDto fromCambioColorRequest(CambioColorRequest request){
    Color c = ColorMapper.colorFromString(request.color());
    return PixelCanvasCambioResponseDto.builder()
        .id(request.id())
        .fila(request.x())
        .columna(request.y())
        .color(ColorMapper.ColorToRgbString(c))
        .build();
  }
}
