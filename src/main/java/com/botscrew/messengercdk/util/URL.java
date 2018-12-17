/*
 * Copyright 2018 BotsCrew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.botscrew.messengercdk.util;


import com.botscrew.messengercdk.exception.MessengerCDKException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Wrapper for building URL
 */
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

        public com.botscrew.messengercdk.util.URL build() {
            UriComponents uriComponents =
                    UriComponentsBuilder.newInstance()
                            .scheme(protocol)
                            .host(host)
                            .path(path)
                            .port(port)
                            .query(composeParams())
                            .build();

            try {
                return new com.botscrew.messengercdk.util.URL(uriComponents.toUri().toURL().toString());
            } catch (MalformedURLException e) {
                throw new MessengerCDKException("Cannot build messaging url!");
            }
        }

        private String composeParams() {
            if (this.parameters.isEmpty()) return null;

            Optional<String> resultOpt = parameters.entrySet().stream()
                    .map(e -> e.getKey() + "=" + e.getValue())
                    .reduce((p1, p2) -> p1 + "&" + p2);

            if (!resultOpt.isPresent()) {
                throw new MessengerCDKException("Cannot build params: " + parameters);
            }

            return resultOpt.get();
        }

    }
}
