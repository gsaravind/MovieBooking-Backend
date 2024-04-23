package com.moviebooking.interceptor;

import java.io.IOException;

import org.springframework.kafka.core.KafkaTemplate;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.HttpResponse;
import org.zalando.logbook.Precorrelation;
import org.zalando.logbook.Sink;

public class KafkaSink implements Sink {

	private final KafkaTemplate<String, String> kafkaTemplate;
	
	public KafkaSink(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public void write(Precorrelation precorrelation, HttpRequest request) throws IOException {
		
	}

	@Override
	public void write(Correlation correlation, HttpRequest request, HttpResponse response) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("PATH: " + request.getPath() + "\n");
		sb.append("METHOD: " + request.getMethod() + "\n");
		sb.append("REQUEST BODY: " + request.getBodyAsString() + "\n");
		sb.append("RESPONSE BODY: " + response.getBodyAsString() + "\n");
		kafkaTemplate.send("REQ_RES_LOGS", sb.toString());
	}

	@Override
	public void writeBoth(Correlation correlation, HttpRequest request, HttpResponse response) throws IOException {
		
	}

	
}
