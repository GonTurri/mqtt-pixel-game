package org.ar.eduutn.frba.dds.mqttpixelgame.dtos;

import lombok.Builder;

@Builder
public record PixelCanvasResponseDto(

    String id,
    int filas,
    int columnas,
    String[][] matriz,
    String[][] target
) {
}
