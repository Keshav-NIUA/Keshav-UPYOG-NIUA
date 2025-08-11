package org.upyog.gis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.TimeZone;

/**
 * Main application class for GIS Service
 * Provides spatial data search capabilities across different modules
 */
@SpringBootApplication
@ComponentScan(basePackages = { "org.upyog.gis" })
public class GISApplication {

    @Value("${app.timezone}")
    private String timeZone;
    
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setTimeZone(TimeZone.getTimeZone(timeZone));
    }
    
    @Bean
    public org.springframework.web.client.RestTemplate restTemplate() {
        return new org.springframework.web.client.RestTemplate();
    }
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(GISApplication.class, args);
        System.out.println("=== GIS SERVICE STARTED SUCCESSFULLY ===");
    }
} 