package com.eragapati.mcp.server.model;

public record Points( Props properties) {
    public record Props(String forecast) {
    }
}