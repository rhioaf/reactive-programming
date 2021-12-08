package com.xtremax.rxjava.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiConfig {

  // BY DOING THIS, WE DON'T NEED TO EXPLICITLY MAP EVERY SINGLE JSON PROPERTY
  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
  }

  @Bean
  public ObjectWriter objectWriter(ObjectMapper mapper) {
    return mapper.writerWithDefaultPrettyPrinter();
  }

  @Bean
  public WebClient webClient() {
    return WebClient.builder().clientConnector(new ReactorClientHttpConnector()).build();
  }
}
