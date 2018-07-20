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

package com.botscrew.messengercdk.model.outgoing;

import com.botscrew.messengercdk.config.MessengerCDKConfiguration;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.outgoing.builder.Attachment;
import com.botscrew.messengercdk.model.outgoing.builder.TextMessage;
import com.botscrew.messengercdk.model.outgoing.request.MessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

public class JacksonTests {
    private JacksonTester<MessageRequest> json;

    @Before
    public void setup() {
        ObjectMapper objectMapper = new MessengerCDKConfiguration().defaultJacksonObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void messageRequestShouldNotContainTextFieldIfIsNull() throws IOException {
        MessageRequest request = TextMessage.builder()
                .user(new MessengerUser() {
                    @Override
                    public Long getChatId() {
                        return 1L;
                    }

                    @Override
                    public String getState() {
                        return "default";
                    }
                })
                .text(null)
                .build();

        String json = this.json.write(request).getJson();
        assert !json.contains("text");
    }

    @Test
    public void attachmentRequestShouldNotContainTextFieldIfIsNull() throws IOException {
        MessageRequest request = Attachment.builder()
                .user(new MessengerUser() {
                    @Override
                    public Long getChatId() {
                        return 1L;
                    }

                    @Override
                    public String getState() {
                        return "default";
                    }
                })
                .url("")
                .type(com.botscrew.messengercdk.model.outgoing.attachment.Attachment.Type.IMAGE)
                .build();

        String json = this.json.write(request).getJson();
        assert !json.contains("text");
    }
}
