package com.botscrew.messengercdk;

import com.botscrew.botframework.container.LocationContainer;
import com.botscrew.botframework.container.PostbackContainer;
import com.botscrew.botframework.container.TextContainer;
import com.botscrew.botframework.sender.PlatformSender;
import com.botscrew.messengercdk.config.MessengerCDKConfiguration;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.outgoing.builder.TextMessage;
import com.botscrew.messengercdk.model.outgoing.request.Request;
import com.botscrew.messengercdk.service.TokenizedSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
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

    private Queue<Request> requests = new ConcurrentLinkedQueue<>();
    private final Random random = new Random();

    @Test
    public void shouldSendMessagesInTheWayTheyAreComing() {
        when(restTemplate.postForObject(anyString(), any(), any())).then(invocation -> {
            Request argument = invocation.getArgument(1);
            requests.add(argument);
            return "";
        });

        List<MessengerUser> users = new ArrayList<>();
        Map<Long, Integer> userIdAndCurrentMessageIndexes = new HashMap<>();
        for (int i = 0; i < 5000; i++) {
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

        System.out.println(requests.size());
        int size = requests.size();
        for (int i = 0; i < size; i++) {
            Request top = requests.poll();
            Long id = top.getRecipient().getId();
            Integer index = userIdAndCurrentMessageIndexes.get(id);

            assertEquals(index + "", top.getMessage().getText());
            System.out.println(i + " - is ok");
            userIdAndCurrentMessageIndexes.put(id, index + 1);
        }
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
