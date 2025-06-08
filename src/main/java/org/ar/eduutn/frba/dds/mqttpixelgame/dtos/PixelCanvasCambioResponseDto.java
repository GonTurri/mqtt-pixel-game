package org.ar.eduutn.frba.dds.mqttpixelgame.dtos;


import lombok.Builder;

@Builder
public record PixelCanvasCambioResponseDto(
  String id,
  int fila,
  int columna,
  String color
){}
