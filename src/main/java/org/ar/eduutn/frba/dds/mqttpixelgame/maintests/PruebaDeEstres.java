package org.ar.eduutn.frba.dds.mqttpixelgame.maintests;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.Color;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Component
@Getter
public class PruebaDeEstres implements Runnable {

  private final MqttClient mqttClient;
  private final Random rnd = new Random();
  private final List<String> topics = new ArrayList<>();

  @Override
  public void run() {
    try {
      if (!mqttClient.isConnected() || topics.isEmpty()) {
        return;
      }
      MqttMessage msg = createMessage();
      msg.setQos(0);
      msg.setRetained(true);
      String topic = topics.get(rnd.nextInt(topics.size())); // Evita IndexOutOfBoundsException
      System.out.println("Hola voy a publicar a " + topic + " mensaje " + msg);
      Thread.sleep(1000);
      mqttClient.publish("dds/game/canvas/" + topic + "/colorear", msg);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private MqttMessage createMessage() {
    JsonObject json = new JsonObject();
    json.addProperty("x", rnd.nextInt(6));
    json.addProperty("y", rnd.nextInt(6));
    json.addProperty("color", Color.values()[rnd.nextInt(Color.values().length)].name());
    byte[] payload = json.toString().getBytes(StandardCharsets.UTF_8);
    return new MqttMessage(payload);
  }
}