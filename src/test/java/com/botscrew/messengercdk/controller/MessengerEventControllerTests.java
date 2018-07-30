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

package com.botscrew.messengercdk.controller;

import com.botscrew.messengercdk.config.CustomExceptionHandler;
import com.botscrew.messengercdk.config.MessengerCDKConfiguration;
import com.botscrew.messengercdk.config.MockContainersConfiguration;
import com.botscrew.messengercdk.model.incomming.*;
import com.botscrew.messengercdk.model.outgoing.element.TemplateElement;
import com.botscrew.messengercdk.model.outgoing.element.button.Button;
import com.botscrew.messengercdk.model.outgoing.element.button.PostbackButton;
import com.botscrew.messengercdk.model.outgoing.element.button.ShareButton;
import com.botscrew.messengercdk.model.outgoing.element.button.WebButton;
import com.botscrew.messengercdk.service.ReportHandler;
import com.botscrew.messengercdk.service.SubscriptionReviewer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(value = {MessengerEventController.class, CustomExceptionHandler.class})
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        MessengerCDKConfiguration.class,
        MockContainersConfiguration.class,
        CustomExceptionHandler.class
})
public class MessengerEventControllerTests {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @MockBean
    SubscriptionReviewer subscriptionReviewer;
    @MockBean
    ReportHandler reportHandler;

    @Test
    public void shouldParseReportWithPostbackButton() throws Exception {
        PostbackButton button = new PostbackButton("Payload", "payload");
        String body = objectMapper.writeValueAsString(getReport(messageWithButtonAttachmentPayload(button)));
        Mockito.doAnswer(invocation -> {
            Report report = invocation.getArgument(0);
            PostbackButton actualButton = (PostbackButton) report.getEntry().get(0).getMessaging()
                    .get(0).getMessage().getAttachments().get(0).getPayload().getButtons().get(0);
            assertEquals(button.getTitle(), actualButton.getTitle());
            assertEquals(button.getPayload(), actualButton.getPayload());
            return null;
        }).when(reportHandler).handle(any());

        makeRequest(body);
        verify(reportHandler, times(1)).handle(any());
    }

    @Test
    public void shouldParseReportWithShareButton() throws Exception {
        ShareButton button = new ShareButton();
        String body = objectMapper.writeValueAsString(getReport(messageWithButtonAttachmentPayload(button)));
        Mockito.doAnswer(invocation -> {
            Report report = invocation.getArgument(0);
            ShareButton actualButton = (ShareButton) report.getEntry().get(0).getMessaging()
                    .get(0).getMessage().getAttachments().get(0).getPayload().getButtons().get(0);
            assertEquals(button.getType(), actualButton.getType());
            return null;
        }).when(reportHandler).handle(any());

        makeRequest(body);
        verify(reportHandler, times(1)).handle(any());
    }

    @Test
    public void shouldParseReportWithWebButton() throws Exception {
        WebButton button = new WebButton("Title", "url");
        String body = objectMapper.writeValueAsString(getReport(messageWithButtonAttachmentPayload(button)));
        Mockito.doAnswer(invocation -> {
            Report report = invocation.getArgument(0);
            WebButton actualButton = (WebButton) report.getEntry().get(0).getMessaging()
                    .get(0).getMessage().getAttachments().get(0).getPayload().getButtons().get(0);
            assertEquals(button.getType(), actualButton.getType());
            assertEquals(button.getTitle(), actualButton.getTitle());
            assertEquals(button.getUrl(), actualButton.getUrl());
            return null;
        }).when(reportHandler).handle(any());

        makeRequest(body);
        verify(reportHandler, times(1)).handle(any());
    }

    @Test
    public void shouldParseReportWithTemplateElement() throws Exception {
        TemplateElement element = TemplateElement.builder()
                .title("Title")
                .subtitle("Subtitle")
                .button(new WebButton("ButtonTitle", "url"))
                .build();
        String body = objectMapper.writeValueAsString(getReport(messageWithTemplateAttachmentPayload(element)));
        Mockito.doAnswer(invocation -> {
            Report report = invocation.getArgument(0);
            TemplateElement actualElement = report.getEntry().get(0).getMessaging()
                    .get(0).getMessage().getAttachments().get(0).getPayload().getElements().get(0);
            assertEquals(element.getTitle(), actualElement.getTitle());
            assertEquals(element.getSubtitle(), actualElement.getSubtitle());
            assertEquals(element.getButtons().get(0).getTitle(), actualElement.getButtons().get(0).getTitle());
            return null;
        }).when(reportHandler).handle(any());

        makeRequest(body);
        verify(reportHandler, times(1)).handle(any());
    }

    private void makeRequest(String body) throws Exception {
        mockMvc.perform(post("/messenger/events").content(body)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
    }

    private Report getReport(Message message) {
        return new Report(
                Collections.singletonList(
                        new MessagingBundle(
                                Collections.singletonList(
                                        new Messaging(message)
                                )
                        )
                )
        );
    }

    private Message messageWithButtonAttachmentPayload(Button button) {
        Message message = new Message();
        Attachment attachment = new Attachment();
        message.setAttachments(Collections.singletonList(attachment));
        Payload payload = new Payload();
        payload.setButtons(Collections.singletonList(button));
        attachment.setPayload(payload);
        return message;
    }

    private Message messageWithTemplateAttachmentPayload(TemplateElement element) {
        Message message = new Message();
        Attachment attachment = new Attachment();
        message.setAttachments(Collections.singletonList(attachment));
        Payload payload = new Payload();
        payload.setElements(Collections.singletonList(element));
        attachment.setPayload(payload);
        return message;
    }
}
