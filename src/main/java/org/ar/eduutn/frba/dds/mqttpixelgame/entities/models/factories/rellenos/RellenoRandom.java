package org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.factories.rellenos;

import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.Color;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.PixelCanvas;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.factories.RellenoStrategy;
import java.util.Random;

public class RellenoRandom implements RellenoStrategy {

  private final Random random = new Random();
  @Override
  public void aplicar(PixelCanvas canvas) {
    Color[] colores = Color.values();
    for (int i = 0; i < canvas.getFilas(); i++) {
      for (int j = 0; j < canvas.getColumnas(); j++) {
        canvas.rellenarPixel(i, j, colores[random.nextInt(colores.length)]);
      }
    }
  }
}
