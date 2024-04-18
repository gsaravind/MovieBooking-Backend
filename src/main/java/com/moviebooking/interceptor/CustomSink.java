package com.moviebooking.interceptor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.HttpResponse;
import org.zalando.logbook.Precorrelation;
import org.zalando.logbook.Sink;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomSink implements Sink {

	@Autowired
	private KafkaProducer kafkaProducer;
	
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
		kafkaProducer.sendMessage(sb.toString());		
	}

	@Override
	public void writeBoth(Correlation correlation, HttpRequest request, HttpResponse response) throws IOException {
		
	}
	
}
