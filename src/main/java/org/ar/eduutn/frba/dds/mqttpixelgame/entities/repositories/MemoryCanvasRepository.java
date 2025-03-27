package org.ar.eduutn.frba.dds.mqttpixelgame.entities.repositories;

import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.PixelCanvas;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryCanvasRepository implements CanvasRepository{

  private final Map<String, PixelCanvas> canvasStorage = new ConcurrentHashMap<>();

  @Override
  public List<PixelCanvas> obtenerTodos() {
    return new ArrayList<>(this.canvasStorage.values()
        .stream().filter(canvas -> canvas.getTargetCanvas().isPresent()).toList());
  }

  @Override
  public Optional<PixelCanvas> obtenerPorId(String id) {
    return Optional.ofNullable(this.canvasStorage.get(id));
  }

  @Override
  public void guardar(PixelCanvas canvas) {
    this.canvasStorage.put(canvas.getId(),canvas);
  }

  @Override
  public void eliminar(String id) {
    this.canvasStorage.remove(id);
  }

  @Override
  public void actualizar(String id, PixelCanvas canvas) {
    this.canvasStorage.put(id,canvas);
  }
}
