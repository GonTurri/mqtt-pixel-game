package org.ar.eduutn.frba.dds.mqttpixelgame.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class PixelCanvas {
  private final int filas;
  private final int columnas;
  private final Color[][] matriz;

  public PixelCanvas(int filas, int columnas) {

    if(columnas <= 0 || filas<=0) throw new IllegalArgumentException("Filas o columnas inválidas");

    this.filas = filas;
    this.columnas = columnas;
    this.matriz = new Color[this.filas][this.columnas];
  }

  public void rellenarPixel(int x, int y, Color color){
    if (x >= 0 && x < filas && y >= 0 && y < columnas) {
      matriz[x][y] = color;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PixelCanvas canvas = (PixelCanvas) o;
    if (filas != canvas.filas || columnas != canvas.columnas) return false;
    for (int i = 0; i < filas; i++) {
      for (int j = 0; j < columnas; j++) {
        if (matriz[i][j] != canvas.matriz[i][j]) {
          return false;
        }
      }
    }
    return true;
  }
  @Override
  public int hashCode() {
    int result = Objects.hash(filas, columnas);
    result = 31 * result + Arrays.deepHashCode(matriz);
    return result;
  }
}
