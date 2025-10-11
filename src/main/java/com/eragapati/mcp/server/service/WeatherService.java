package com.eragapati.mcp.server.service;

import com.eragapati.mcp.server.model.Alert;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.stream.Collectors;

@Service
public class WeatherService {

    private static final String BASE_URL = "https://api.weather.gov";

    private final RestClient restClient;

    public WeatherService() {

        this.restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Accept", "application/geo+json")
                .defaultHeader("User-Agent", "WeatherApiClient/1.0 (your@email.com)")
                .build();
    }

    @Tool(description = "Provides active weather alerts for the given for US state. Input two char US state code (example:  CA, NY)")
    public String getAlerts(String state) {
        Alert alert = restClient.get().uri("/alerts/active/area/" + state).retrieve().body(Alert.class);

        return alert.features()
                .stream()
                .map(feature -> String.format("""
                                Event: %s
                                Area Desc: %s
                                Severity: %s
                                Description: %s
                                Instructions: %s
                                """, feature.properties().event(), feature.properties().areaDesc(), feature.properties().severity(),
                        feature.properties().description(), feature.properties().instruction()))
                .collect(Collectors.joining("\n"));
    }
}