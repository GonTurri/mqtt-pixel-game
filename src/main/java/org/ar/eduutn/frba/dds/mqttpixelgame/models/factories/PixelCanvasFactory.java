package org.ar.eduutn.frba.dds.mqttpixelgame.models.factories;

import org.ar.eduutn.frba.dds.mqttpixelgame.models.Color;
import org.ar.eduutn.frba.dds.mqttpixelgame.models.PixelCanvas;
import java.util.Random;

public class PixelCanvasFactory {

  private static final int FILAS_DEFAULT = 6;
  private static final int COLUMNAS_DEFAULT = 6;

  public static PixelCanvas of(int filas, int columnas, RellenoCanvas tipoRelleno) {
    PixelCanvas canvas = new PixelCanvas(filas, columnas);
    rellenarCanvas(canvas, tipoRelleno);
    return canvas;
  }

  public static PixelCanvas of(RellenoCanvas tipoRelleno){
    return of(FILAS_DEFAULT,COLUMNAS_DEFAULT,tipoRelleno);
  }

  public static PixelCanvas of(){
    return of(RellenoCanvas.RANDOM);
  }

  public static PixelCanvas of(Color[][] matriz){
    return new PixelCanvas(matriz.length,matriz[0].length,matriz);
  }

  private static void rellenarCanvas(PixelCanvas canvas, RellenoCanvas tipoRelleno) {
    Color colorFijo = switch (tipoRelleno) {
      case BLANCO -> Color.BLANCO;
      case NEGRO -> Color.NEGRO;
      default -> null;
    };

    if (colorFijo != null) {
      for (int i = 0; i < canvas.getFilas(); i++) {
        for (int j = 0; j < canvas.getColumnas(); j++) {
          canvas.rellenarPixel(i, j, colorFijo);
        }
      }
    } else if (tipoRelleno == RellenoCanvas.RANDOM) {
      Random random = new Random();
      Color[] colores = Color.values();
      for (int i = 0; i < canvas.getFilas(); i++) {
        for (int j = 0; j < canvas.getColumnas(); j++) {
          canvas.rellenarPixel(i, j, colores[random.nextInt(colores.length)]);
        }
      }
    }
  }
}
