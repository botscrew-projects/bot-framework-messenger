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

package com.botscrew.messengercdk.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.mockito.internal.util.Checks.checkNotNull;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public @ResponseBody
    ExceptionResponse handle(HttpServletResponse response, Throwable throwable) {
        HttpStatus status = Optional
                .of(AnnotationUtils.getAnnotation(throwable.getClass(), ResponseStatus.class))
                .map(ResponseStatus::value)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setStatus(status.value());
        return new ExceptionResponse(throwable.getMessage());
    }

    @Data
    public static class ExceptionResponse {

        private final long timestamp = System.currentTimeMillis();

        private final String message;

        @JsonCreator
        public ExceptionResponse(String message) {
            checkNotNull(message, "message == NULL");
            this.message = message;
        }

    }

}
