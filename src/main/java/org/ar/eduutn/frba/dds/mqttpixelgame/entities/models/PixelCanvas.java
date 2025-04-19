package org.ar.eduutn.frba.dds.mqttpixelgame.entities.models;

import lombok.Getter;
import lombok.Setter;
import org.ar.eduutn.frba.dds.mqttpixelgame.exceptions.CoordenadasFueraDeRangoException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class PixelCanvas {
  private final String id = UUID.randomUUID().toString();
  private final int filas;
  private final int columnas;
  private final Color[][] matriz;
  private PixelCanvas targetCanvas = null;

  public PixelCanvas(int filas, int columnas) {

    if (columnas <= 0 || filas <= 0) throw new IllegalArgumentException("Filas o columnas inválidas");

    this.filas = filas;
    this.columnas = columnas;
    this.matriz = new Color[this.filas][this.columnas];
  }

  public PixelCanvas(int filas, int columnas, Color[][] matriz) {
    this.filas = filas;
    this.columnas = columnas;
    this.matriz = matriz;
  }


  public Optional<PixelCanvas> getTargetCanvas() {
    return Optional.ofNullable(this.targetCanvas);
  }

  public void setTargetCanvas(PixelCanvas canvas) {
    if (canvas.getFilas() != this.filas || canvas.getColumnas() != this.columnas) {
      throw new IllegalArgumentException("Las dimensiones de los canvas no coinciden");
    }
    this.targetCanvas = canvas;
  }

  public synchronized ResultadoCambio rellenarPixel(int x, int y, Color color) throws CoordenadasFueraDeRangoException {
    if (!coordenadasDentroDeRango(x, y)) {
      throw new CoordenadasFueraDeRangoException();
    }
    if (matriz[x][y] != null &&  matriz[x][y].equals(color)) return new ResultadoCambio(false);
    matriz[x][y] = color;
    return new ResultadoCambio(true);
  }

  public synchronized boolean logroSuTarget() {
    return this.equals(this.targetCanvas);
  }

  public boolean coordenadasDentroDeRango(int x, int y) {
    return x >= 0 && x < filas && y >= 0 && y < columnas;
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
