package com.eragapati.mcp.server.model;

import java.util.List;


public record Alert(List<Feature> features) {
    public record Feature(Properties properties) {
    }

    public record Properties(String event, String areaDesc,
                             String severity, String description,
                             String instruction) {
    }
}
