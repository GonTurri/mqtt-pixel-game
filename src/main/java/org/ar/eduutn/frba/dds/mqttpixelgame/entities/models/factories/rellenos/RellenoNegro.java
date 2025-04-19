package org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.factories.rellenos;

import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.Color;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.PixelCanvas;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.factories.RellenoStrategy;

public class RellenoNegro implements RellenoStrategy {
  @Override
  public void aplicar(PixelCanvas canvas) {
    for (int i = 0; i < canvas.getFilas(); i++) {
      for (int j = 0; j < canvas.getColumnas(); j++) {
        canvas.rellenarPixel(i, j, Color.NEGRO);
      }
    }
  }
}
