package com.moviebooking.interceptor;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.DefaultHttpLogFormatter;
import org.zalando.logbook.core.DefaultSink;
import org.zalando.logbook.core.StreamHttpLogWriter;

@Configuration
public class RequestLoggingFilterConfig {

    /*
     * @Bean
     * public CommonsRequestLoggingFilter commonRequestLoggingFilter() {
     * CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
     * filter.setIncludeQueryString(true);
     * filter.setIncludePayload(true);
     * filter.setAfterMessagePrefix("REQUEST DATA : ");
     * return filter;
     * }
     */

	@Autowired
	private CustomSink customSink;
	
    @Bean
    public Logbook logbook() {
		/*Logbook logbook = Logbook.builder()
		        .sink(new DefaultSink(
		                new DefaultHttpLogFormatter(),
		                new StreamHttpLogWriter(System.err)))
		        .build();*/
    	Logbook logbook = Logbook.builder()
    			.sink(customSink)
    			.build();
        return logbook;
    }

}
