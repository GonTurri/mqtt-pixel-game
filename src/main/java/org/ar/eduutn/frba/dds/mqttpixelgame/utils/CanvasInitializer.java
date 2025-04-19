package org.ar.eduutn.frba.dds.mqttpixelgame.utils;

import lombok.RequiredArgsConstructor;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.Color;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.PixelCanvas;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.factories.PixelCanvasFactory;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.factories.rellenos.RellenoCanvas;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.repositories.CanvasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CanvasInitializer implements CommandLineRunner {

  private final Color[][] matriz_oveja =

      {
          {
              Color.BLANCO, Color.BLANCO, Color.BLANCO, Color.BLANCO, Color.BLANCO, Color.BLANCO
          },
          {
              Color.BEIGE, Color.BEIGE, Color.BEIGE, Color.BEIGE, Color.BEIGE, Color.BEIGE
          },
          {
              Color.NEGRO, Color.BLANCO, Color.BEIGE, Color.BEIGE, Color.BLANCO, Color.NEGRO
          },
          {
              Color.BEIGE, Color.BEIGE, Color.BEIGE, Color.BEIGE, Color.BEIGE, Color.BEIGE
          },
          {
              Color.BLANCO, Color.BEIGE, Color.ROSA, Color.ROSA, Color.BEIGE, Color.BLANCO
          },
          {
              Color.BLANCO, Color.BEIGE, Color.ROSA, Color.ROSA, Color.BEIGE, Color.BLANCO
          }
      };

  private final Color[][] matriz_pato =

      {
          {
              Color.BLANCO, Color.BLANCO, Color.BLANCO, Color.BLANCO, Color.BLANCO, Color.BLANCO
          },
          {
              Color.BLANCO, Color.NEGRO, Color.BLANCO, Color.BLANCO, Color.NEGRO, Color.BLANCO
          },
          {
              Color.BLANCO, Color.AMARILLO, Color.AMARILLO, Color.AMARILLO, Color.AMARILLO, Color.BLANCO
          },
          {
              Color.BLANCO, Color.MARRON, Color.MARRON, Color.MARRON, Color.MARRON, Color.BLANCO
          },
          {
              Color.BLANCO, Color.BLANCO, Color.ROJO, Color.ROJO, Color.BLANCO, Color.BLANCO
          },
          {
              Color.BLANCO, Color.BLANCO, Color.ROJO, Color.ROJO, Color.BLANCO, Color.BLANCO
          }
      };

  private final CanvasRepository canvasRepository;

  @Override
  public void run(String... args) throws Exception {
    inicializarCanvas();
  }

  private void inicializarCanvas() {
    PixelCanvas canvasOveja = PixelCanvasFactory.of(matriz_oveja);
    PixelCanvas canvasPato = PixelCanvasFactory.of(matriz_pato);


    List<PixelCanvas> canvasList = new ArrayList<>(Arrays.asList(canvasOveja,canvasPato));


    for (int i = 1; i <= 2; i++) {
      PixelCanvas canvas = PixelCanvasFactory.of(RellenoCanvas.RANDOM);
      canvas.setTargetCanvas(canvasList.get(i-1));
      System.out.println("Id del canvas equipo " + i + ": " + canvas.getId());
      canvasList.add(canvas);

    }

    canvasList.forEach(canvasRepository::guardar);


  }
}
