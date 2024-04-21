package org.example.quanlisukien.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class ConfigKafkaTopic {
  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> props = new HashMap<>();
    props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
    props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG,"3000");
    return new KafkaAdmin(props);
  }

  @Bean
  public NewTopic topic() {
    return TopicBuilder.name("new-topic")
        .partitions(1)
        .replicas(1)
        .build();
  }
}
