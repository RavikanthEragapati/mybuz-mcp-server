package com.eragapati.mcp.server.configuration.tool.function;

import com.eragapati.mcp.server.model.ArithmeticInput;
import com.eragapati.mcp.server.model.ConversionInput;
import com.eragapati.mcp.server.model.TextInput;
import com.eragapati.mcp.server.model.TimeZoneInput;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class FunctionToolCallbackConfig {

    @Bean
    public ToolCallback textToUpperCase() {
        return FunctionToolCallback.builder("textToUpperCase", (TextInput input) -> input.input().toUpperCase())
                .inputType(TextInput.class)
                .description("Converts all of the characters in given String to upper case")
                .build();
    }


    // Calculator Tool (Arithmetic Addition)
    @Bean
    public ToolCallback calculator() {
        return FunctionToolCallback.builder("calculator", (ArithmeticInput input) -> {
                    double result = input.num1() + input.num2();
                    return String.format("The sum of %.2f and %.2f is %.2f.", input.num1(), input.num2(), result);
                })
                .inputType(ArithmeticInput.class)
                .description("Performs simple arithmetic operations, currently only supporting addition of two numbers (num1 + num2).")
                .build();
    }


    // Unit Conversion Tool (Celsius to Fahrenheit)
    @Bean
    public ToolCallback temperatureConverter() {
        return FunctionToolCallback.builder("temperatureConverter", (ConversionInput input) -> {
                    double value = input.value();
                    String from = input.fromUnit();
                    String to = input.toUnit();
                    double result;

                    if ("C".equalsIgnoreCase(from) && "F".equalsIgnoreCase(to)) {
                        result = (value * 9 / 5.0) + 32;
                        return String.format("%.2f°%s is equivalent to %.2f°%s.", value, from, result, to);
                    } else if ("F".equalsIgnoreCase(from) && "C".equalsIgnoreCase(to)) {
                        result = (value - 32) * 5 / 9.0;
                        return String.format("%.2f°%s is equivalent to %.2f°%s.", value, from, result, to);
                    } else if (from.equals(to)) {
                        return String.format("The units are the same: %.2f°%s.", value, from);
                    } else {
                        return "Error: Unsupported conversion requested. I only support Celsius (C) <-> Fahrenheit (F) conversion.";
                    }
                })
                .inputType(ConversionInput.class)
                .description("Converts temperature between Celsius (C) and Fahrenheit (F). Provide the numeric value, the unit to convert from (C or F), and the unit to convert to (C or F).")
                .build();
    }


    // Time Zone Tool (Date/Time Utility)
    @Bean
    public ToolCallback timeZoneConverter() {
        return FunctionToolCallback.builder("timeZoneConverter", (TimeZoneInput input) -> {
                    try {
                        ZoneId zoneId = ZoneId.of(input.timezoneId());
                        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a, MMMM d, yyyy (z)");
                        return "The current time in " + input.timezoneId() + " is: " + zonedDateTime.format(formatter);
                    } catch (Exception e) {
                        return "Error: Invalid timezone ID provided. Please use a standard IANA timezone format, like 'America/Los_Angeles'.";
                    }
                })
                .inputType(TimeZoneInput.class)
                .description("Retrieves the current date and time for a given IANA timezone ID (e.g., 'America/New_York', 'Europe/Paris').")
                .build();
    }
}
