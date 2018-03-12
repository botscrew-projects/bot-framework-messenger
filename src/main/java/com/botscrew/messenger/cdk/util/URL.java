package com.botscrew.messenger.cdk.util;

import com.botscrew.messenger.cdk.exception.SystemException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class URL {
    private final String value;

    URL(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static class Builder {
        private String protocol;
        private String host;
        private String path;
        private Integer port;
        private Map<String, String> parameters;

        public Builder() {
            parameters = new HashMap<>();
        }

        public Builder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder port(Integer port) {
            this.port = port;
            return this;
        }

        public Builder param(String name, String value) {
            this.parameters.put(name, value);
            return this;
        }

        public URL build() {
            try {
                URI uri = new URI(protocol, null, host, port, path, composeParams(), null);
                return new URL(uri.toURL().toString());
            } catch (URISyntaxException | MalformedURLException e) {
                throw new SystemException("Cannot build url", e);
            }
        }

        private String composeParams() {
            if (this.parameters.isEmpty()) return null;

            Optional<String> resultOpt = parameters.entrySet().stream()
                    .map(e -> e.getKey() + "=" + e.getValue())
                    .reduce((p1, p2) -> p1 + "&" + p2);

            if (!resultOpt.isPresent()) {
                throw new SystemException("Cannot build params: " + parameters);
            }

            return resultOpt.get();
        }

    }
}
