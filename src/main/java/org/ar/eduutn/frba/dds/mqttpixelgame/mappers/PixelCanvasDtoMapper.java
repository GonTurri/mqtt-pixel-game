package org.ar.eduutn.frba.dds.mqttpixelgame.mappers;

import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.PixelCanvasResponseDto;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.Color;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.PixelCanvas;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.factories.PixelCanvasFactory;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.factories.rellenos.RellenoCanvas;

public class PixelCanvasDtoMapper {
  public static PixelCanvasResponseDto fromPixelCanvas(PixelCanvas canvas) {

    String[][] matrizColores = mapMatrizColorToMatrizString(canvas.getMatriz());

    String[][] targetColores = mapMatrizColorToMatrizString(canvas.getTargetCanvas()
        .map(PixelCanvas::getMatriz).
        orElse(PixelCanvasFactory.of(RellenoCanvas.BLANCO).getMatriz()));

    return PixelCanvasResponseDto.builder()
        .id(canvas.getId())
        .filas(canvas.getFilas())
        .columnas(canvas.getColumnas())
        .matriz(matrizColores)
        .target(targetColores)
        .build();

  }

  private static String[][] mapMatrizColorToMatrizString(Color[][] matriz) {
    int filas = matriz.length, columnas = matriz[0].length;
    String[][] mapeada = new String[filas][columnas];

    for (int i = 0; i < filas; i++) {
      for (int j = 0; j < columnas; j++) {
        mapeada[i][j] = ColorMapper.ColorToRgbString(matriz[i][j]);
      }
    }

    return mapeada;
  }


}
