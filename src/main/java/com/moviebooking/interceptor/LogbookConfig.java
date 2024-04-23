package com.moviebooking.interceptor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.Sink;

@Configuration
public class LogbookConfig {

	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
    @Bean
    public Logbook logbook() throws IOException {
        Sink sink = new KafkaSink(kafkaTemplate);
        return Logbook.builder()
                .sink(sink)
                .build();
    }
		
}
