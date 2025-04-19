package org.ar.eduutn.frba.dds.mqttpixelgame.utils;

import lombok.RequiredArgsConstructor;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.PixelCanvas;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.repositories.CanvasRepository;
import org.ar.eduutn.frba.dds.mqttpixelgame.maintests.PruebaDeEstres;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class PruebaDeEstresRunner implements CommandLineRunner {

  private final PruebaDeEstres pruebaDeEstres;
  private final ExecutorService executorService = Executors.newFixedThreadPool(150);

  private final CanvasRepository canvasRepository;


  @Override
  public void run(String ... args) {
    if (args.length == 0 || !args[0].equals("--stress-test")) {
      return; // Solo corre si se pasa el argumento --stress-test
    }

//    Scanner scanner = new Scanner(System.in);
//    System.out.print("Ingrese primer topic: ");
//    String topic1 = scanner.nextLine();
//    System.out.print("Ingrese segundo topic: ");
//    String topic2 = scanner.nextLine();

    pruebaDeEstres.getTopics().addAll(this.canvasRepository.obtenerTodos().stream().map(PixelCanvas::getId).toList());

    System.out.println("Iniciando prueba de estrés...");
    for (int i = 0; i < 1000; i++) {
      executorService.submit(pruebaDeEstres); // Corre en paralelo sin bloquear
    }

    executorService.shutdown(); // Cierra el ExecutorService después de completar las tareas
  }
}