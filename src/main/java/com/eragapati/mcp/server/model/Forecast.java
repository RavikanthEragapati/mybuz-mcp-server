package com.eragapati.mcp.server.model;

import java.util.List;
import java.util.Map;


public record Forecast(Props properties) {
    public record Props(List<Period> periods) {
    }

    public record Period(Integer number, String name,
                         String startTime, String endTime,
                         Boolean isDayTime, Integer temperature,
                         String temperatureUnit,
                         String temperatureTrend,
                         Map probabilityOfPrecipitation,
                         String windSpeed, String windDirection,
                         String icon, String shortForecast,
                         String detailedForecast) {
    }
}