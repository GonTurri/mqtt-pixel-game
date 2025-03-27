package org.ar.eduutn.frba.dds.mqttpixelgame.entities.repositories;

import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.PixelCanvas;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository

public interface CanvasRepository {

    List<PixelCanvas> obtenerTodos();
   Optional<PixelCanvas> obtenerPorId(String id);

    void guardar(PixelCanvas canvas);

    void eliminar(String id);

    void actualizar(String id, PixelCanvas canvas);

}
