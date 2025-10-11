package com.eragapati.mcp.server.configuration.tool.method;

import com.eragapati.mcp.server.service.AccountRateInfoService;
import com.eragapati.mcp.server.service.WeatherService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolAnnotationToolCallbackConfig {
    @Bean
    public ToolCallbackProvider weatherTools(WeatherService weatherService, AccountRateInfoService accountRateInfoService) {
        return MethodToolCallbackProvider.builder().toolObjects(weatherService, accountRateInfoService).build();
    }
}
