package org.ar.eduutn.frba.dds.mqttpixelgame.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MqttConfig {

  @Value("${broker.url}")
  private String broker_url;

  @Value("${broker.client.id}")
  private String client_id;

  @Bean
  public MqttClient mqttClient() throws MqttException {
    System.out.println("Connecting to broker: " + broker_url);
    MqttClient client = new MqttClient(broker_url,client_id,new MemoryPersistence());
    MqttConnectOptions options = new MqttConnectOptions();
    options.setAutomaticReconnect(true);
    options.setKeepAliveInterval(30);
    options.setCleanSession(true);
    client.connect(options);
    System.out.println("Connection success");
    return client;
  }

}
