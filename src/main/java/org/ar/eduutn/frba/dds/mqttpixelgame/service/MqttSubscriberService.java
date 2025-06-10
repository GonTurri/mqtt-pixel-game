package org.ar.eduutn.frba.dds.mqttpixelgame.service;

import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.ar.eduutn.frba.dds.mqttpixelgame.Events.CambioColorPixelEvent;
import org.ar.eduutn.frba.dds.mqttpixelgame.Events.CanvasCompletadoEvent;
import org.ar.eduutn.frba.dds.mqttpixelgame.dtos.CambioColorRequest;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class MqttSubscriberService {

  private final MqttClient mqttClient;

  private final ApplicationEventPublisher publisher;

  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();
  private final Gson gson = new Gson();

  private final ExecutorService pool = Executors.newFixedThreadPool(15);

  @Value("${broker.topic}")
  private String topicTemplate;

  @PostConstruct
  private void subscribeToCanvasUpdates() {
    try {
      mqttClient.subscribe(topicTemplate, this::handleMessage);
      System.out.println("Subscribed to topic: " + topicTemplate);
    } catch (MqttException e) {
      e.printStackTrace();
    }
  }

  @EventListener
  public void handleCanvasCompletadoEvent(CanvasCompletadoEvent event) throws MqttException {
    desuscribirseDeTopics();
  }

  private void desuscribirseDeTopics() throws MqttException {
    mqttClient.unsubscribe(topicTemplate);
  }


  private void handleMessage(String topic, MqttMessage message) {
    pool.execute(() -> procesarMensaje(topic, message));
  }

  private void procesarMensaje(String topic, MqttMessage message) {
    try {
      String payload = new String(message.getPayload());
      CambioColorRequest cambioColorRequest = gson.fromJson(payload, CambioColorRequest.class);

      validarRequest(cambioColorRequest);
      // Publicar evento para manejar el cambio en un servicio desacoplado
      publisher.publishEvent(new CambioColorPixelEvent(this, cambioColorRequest));
    } catch (RuntimeException ignored) {
    }
  }

  private void validarRequest(CambioColorRequest request){
    Set<ConstraintViolation<CambioColorRequest>> violations = validator.validate(request);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  @PreDestroy
  public void shutdown() {
    pool.shutdown();
    System.out.println("Thread pool shutdown.");
  }
}
