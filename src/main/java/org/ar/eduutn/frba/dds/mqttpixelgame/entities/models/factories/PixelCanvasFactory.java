package org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.factories;

import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.Color;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.PixelCanvas;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.factories.rellenos.RellenoBlanco;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.factories.rellenos.RellenoCanvas;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.factories.rellenos.RellenoNegro;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.factories.rellenos.RellenoRandom;
import java.util.EnumMap;
import java.util.Map;

public class PixelCanvasFactory {

  private static final int FILAS_DEFAULT = 6;
  private static final int COLUMNAS_DEFAULT = 6;

  private static final Map<RellenoCanvas, RellenoStrategy> rellenoStrategies = new EnumMap<>(RellenoCanvas.class);

  static {
    rellenoStrategies.put(RellenoCanvas.BLANCO, new RellenoBlanco());
    rellenoStrategies.put(RellenoCanvas.NEGRO, new RellenoNegro());
    rellenoStrategies.put(RellenoCanvas.RANDOM, new RellenoRandom());
  }

  public static PixelCanvas of(int filas, int columnas, RellenoCanvas tipoRelleno) {
    PixelCanvas canvas = new PixelCanvas(filas, columnas);
    rellenarCanvas(canvas, tipoRelleno);
    return canvas;
  }

  public static PixelCanvas of(RellenoCanvas tipoRelleno) {
    return of(FILAS_DEFAULT, COLUMNAS_DEFAULT, tipoRelleno);
  }

  public static PixelCanvas of() {
    return of(RellenoCanvas.RANDOM);
  }

  public static PixelCanvas of(Color[][] matriz) {
    return new PixelCanvas(matriz.length, matriz[0].length, matriz);
  }

  private static void rellenarCanvas(PixelCanvas canvas, RellenoCanvas tipoRelleno) {
    rellenoStrategies.getOrDefault(tipoRelleno, new RellenoRandom())
        .aplicar(canvas);
  }
}

