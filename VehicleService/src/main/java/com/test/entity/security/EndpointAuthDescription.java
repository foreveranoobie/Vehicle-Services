package com.test.entity.security;

import java.util.List;

public class EndpointAuthDescription {
    private String endpoint;
    private List<String> methods;

    public EndpointAuthDescription(String endpoint, List<String> methods) {
        this.endpoint = endpoint;
        this.methods = methods;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public List<String> getMethods() {
        return methods;
    }

    public boolean isMethodAllowed(String method) {
        return methods.contains(method);
    }

    @Override
    public String toString() {
        return "EndpointAuthDescription{" +
                "endpoint='" + endpoint + '\'' +
                ", methods=" + methods +
                '}';
    }
}
