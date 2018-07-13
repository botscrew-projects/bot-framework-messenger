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

package com.botscrew.messengercdk.service.impl;

import com.botscrew.botframework.container.*;
import com.botscrew.botframework.sender.PlatformSender;
import com.botscrew.messengercdk.config.MessengerCDKConfiguration;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.Response;
import com.botscrew.messengercdk.model.outgoing.builder.TextMessage;
import com.botscrew.messengercdk.model.outgoing.request.MessageRequest;
import com.botscrew.messengercdk.model.outgoing.request.Request;
import com.botscrew.messengercdk.service.TokenizedSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MessengerCDKConfiguration.class})
@TestPropertySource(properties = "facebook.messenger.executor.queue-capacity=10000")
public class TokenizedSenderImplTests {

    @Autowired
    private TokenizedSender tokenizedSenderImpl;
    @MockBean
    RestTemplate restTemplate;
    @MockBean
    LocationContainer locationContainer;
    @MockBean
    PostbackContainer postbackContainer;
    @MockBean
    TextContainer textContainer;
    @MockBean
    PlatformSender platformSender;
    @MockBean
    ReferralContainer referralContainer;
    @MockBean
    ReadContainer readContainer;
    @MockBean
    EchoContainer echoContainer;
    @MockBean
    DeliveryContainer deliveryContainer;

    private final Random random = new Random();

    @Test
    public void shouldSendMessagesInTheWayTheyAreComing() {
        Queue<Request> requests = new ConcurrentLinkedQueue<>();
        when(restTemplate.postForObject(anyString(), any(), any())).then(invocation -> {
            Thread.sleep(10);
            Request argument = invocation.getArgument(1);
            requests.add(argument);
            return new Response();
        });

        List<MessengerUser> users = new ArrayList<>();
        Map<Long, Integer> userIdAndCurrentMessageIndexes = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            users.add(createMockUser(i));
            userIdAndCurrentMessageIndexes.put((long) i, 0);
        }

        Map<MessengerUser, List<Request>> usersRequests = new HashMap<>();
        int amountPerEach = 2;

        for (MessengerUser user : users) {
            List<Request> requestsForUser = new ArrayList<>();
            for (int i = 0; i < amountPerEach; i++) {
                Request request = TextMessage.builder()
                        .user(user)
                        .text(i + "")
                        .build();
                requestsForUser.add(request);
            }
            usersRequests.put(user, requestsForUser);
        }

        for (int i = 0; i < amountPerEach; i++) {
            for (MessengerUser user : users) {
                tokenizedSenderImpl.send("1", usersRequests.get(user).get(i));
            }
        }

        tryToSleep(3000);

        int size = requests.size();
        for (int i = 0; i < size; i++) {
            MessageRequest top = (MessageRequest) requests.poll();
            Long id = top.getRecipient().getId();
            Integer index = userIdAndCurrentMessageIndexes.get(id);

            assertEquals(index + "", top.getMessage().getText());
            userIdAndCurrentMessageIndexes.put(id, index + 1);
        }
    }

    @Test
    public void shouldSendNextMessagesIfSomeFailed() {
        Queue<Request> requests = new ConcurrentLinkedQueue<>();

        when(restTemplate.postForObject(anyString(), any(), any()))
                .then(invocation -> {
                    throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "First request should fail");
                })
                .then(invocation -> {
                    Request argument = invocation.getArgument(1);
                    requests.add(argument);
                    return new Response();
                });

        Request request = TextMessage.builder()
                .user(createMockUser(1L))
                .text("text")
                .build();

        tokenizedSenderImpl.send("token", request);
        tokenizedSenderImpl.send("token", request);

        tryToSleep(500);
        assertEquals(1, requests.size());
    }

    private long random(int from, int to) {
        return random.nextInt(to - from) + from;
    }

    private void tryToSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private MessengerUser createMockUser(long id) {
        return new MessengerUser() {
            @Override
            public Long getChatId() {
                return id;
            }

            @Override
            public String getState() {
                return "default";
            }
        };
    }
}
