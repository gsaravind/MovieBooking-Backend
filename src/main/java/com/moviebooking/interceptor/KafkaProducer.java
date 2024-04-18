package com.moviebooking.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void sendMessage(String msg) {
        kafkaTemplate.send("REQ_RES_LOGS", msg);
    }

}
