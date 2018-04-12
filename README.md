# Messenger CDK Spring Boot Starter


### 1. [*Getting started*] (https://gitlab.com/bots-crew/messenger-cdk-spring-boot-starter/blob/develop/README.md#getting-started)
### 2. [*Customization*] (https://gitlab.com/bots-crew/messenger-cdk-spring-boot-starter/blob/develop/README.md#customization)
[*Page access token*] (https://gitlab.com/bots-crew/messenger-cdk-spring-boot-starter/blob/develop/README.md#page-access-token)

[*Verification token*] (https://gitlab.com/bots-crew/messenger-cdk-spring-boot-starter/blob/develop/README.md#verification-token)

[*Messaging url*] (https://gitlab.com/bots-crew/messenger-cdk-spring-boot-starter/blob/develop/README.md#messaging-url)

[*Executor*] (https://gitlab.com/bots-crew/messenger-cdk-spring-boot-starter/blob/develop/README.md#executor)

[*Webhook*] (https://gitlab.com/bots-crew/messenger-cdk-spring-boot-starter/blob/develop/README.md#subscribe-webhook)

[*Page profile*] (https://gitlab.com/bots-crew/messenger-cdk-spring-boot-starter/blob/develop/README.md#update-your-page-profile)

[*User*] (https://gitlab.com/bots-crew/messenger-cdk-spring-boot-starter/blob/develop/README.md#user)

[*Event handlers*] (https://gitlab.com/bots-crew/messenger-cdk-spring-boot-starter/blob/develop/README.md#event-handlers)

[*Rest template*] (https://gitlab.com/bots-crew/messenger-cdk-spring-boot-starter/blob/develop/README.md#rest-template)
### 3. [*Sending messages*] (https://gitlab.com/bots-crew/messenger-cdk-spring-boot-starter/blob/develop/README.md#sending-messages)
### 4. [*Development*] (https://gitlab.com/bots-crew/messenger-cdk-spring-boot-starter/blob/develop/README.md#development)



Messenger CDK is integration with Facebook Messenger chatbots based on Bot Framework

If you would like to know more about working with Bot Framework read it here:

https://gitlab.com/bots-crew/bot-framework/blob/boot-starter/README.md

# Getting Started

* Add repository path to your build configuration

```
<repositories>
		<repository>
			<id>MyGet</id>
			<url>https://www.myget.org/F/bots-crew/maven</url>
		</repository>
	</repositories>
```
* Add dependency

```
<dependency>
    <groupId>com.botscrew</groupId>
    <artifactId>messenger-cdk-spring-boot-starter</artifactId>
    <version>${messenger-cdk.version}</version>
</dependency>
```

Messenger CDK already have dependency on bot framework so you don't need to add
it to project by yourself

* Define `facebook.messenger.access-token` property

# Customization

#### Page access token

You can set `facebook.messenger.access-token`. It is required if you want to use `Sender` 
#### Verification token

You can set `facebook.messenger.verify-token`. It is `test` by default
#### Messaging  url

For testing purposes you may want to change endpoint where your messages will be sent.
You can change next properties: 

`facebook.messenger.graph-host`

`facebook.messenger.graph-port`

`facebook.messenger.graph-protocol`

`facebook.messenger.messaging-path` to update it for your needs.
#### Executor

All messages from Facebook Messenger are processed in Spring's TaskExecutor.
You are able to change next executor properties:

`facebook.messenger.executor.core-pool-size`

`facebook.messenger.executor.max-pool-size`

`facebook.messenger.executor.queue-capacity`

`facebook.messenger.executor.keep-alive-seconds`

Also you can change executor properties for Sender Executor:

`facebook.messenger.sender-executor.core-pool-size`

`facebook.messenger.sender-executor.max-pool-size`

`facebook.messenger.sender-executor.queue-capacity`

`facebook.messenger.sender-executor.keep-alive-seconds`

#### Subscribe webhook

By default path for events from Messenger is `/messenger/events`.

You can change it in property `facebook.messenger.events-path`

#### Update your page profile
There a few page profile properties which you can edit(f.e. Get started button, persistent menu, etc.)

You can do it via `Messenger` component. Here is an example:
```
@Autowired
private Messenger messenger;

@PostConstruct
public void initMessengerProfile() {
    messenger.setGetStartedButton(new GetStartedButton("GET_STARTED"));

        messenger.setGreeting(new Greeting("HI!"));

        PersistentMenu menu = new PersistentMenu(
                Arrays.asList(
                        new PostbackMenuItem("Postback", "MENU_POSTBACK"),
                        new WebMenuItem("Web", "https://google.com"),
                        NestedMenuItem.builder()
                                .title("Nested")
                        .addMenuItem(PostbackMenuItem.builder()
                                .title("Postback")
                                .payload("PAYLOAD")
                                .build())
                        .build()
                )
        );

        messenger.setPersistentMenu(menu);
        
        List<String> domains = new ArrayList<>();
        domains.add("https://facebook.com/");

        messenger.setWhitelistedDomains(domains);
}
```

### User

You can implement MessengerUser interface to define your own user.

Also if you need to get user profile information, you can use `Messenger` component.

```
@Autowired 
private Messenger messenger;

@Text
public void handleTextDefault(User user) {
    ...
    Profile userProfile = messenger.getProfile(user.getChatId());
    ...
}
```

###### UserProvider

To take control over users who are writing to your bot you can implement
`UserProvider` interface and define it as Spring Bean. It will pass user chat id
and page id to your implementation(you will be able to get this user in your 
method handlers).

```
@Component
public class UserService implements UserProvider {
    @Override
    public MessengerUser getByChatIdAndPageId(Long chatId, Long botId) {
        return ...
    }
}
```

### Event handlers

Each component responsible for processing some type of event from Facebook Messenger implement `EventHandler` interface.

Messenger CDK contains default implementations which trigger Bot Framework.

If you need to take care for processing some type of event, you can define your own implementation of `EventHandler` and define it as Spring Bean.
Be careful with this feature, in this case you're not adding logic to the existing one, but overriding it.

### Rest template
Messenger CDK depends on Spring's `RestTemplate` and has its own configurations for `RestTemplate` and `ObjectMapper`. 
In case you define your own configurations Messenger CDK will not create own and will use yours.


# Sending messages 
* You can autowire `com.botscrew.messengercdk.service.Sender` 
    to send messages with default access token from properties.

* You can autowire `com.botscrew.messengercdk.service.TokenizedSender`
    to send messages with your custom access token

Facebook sender is asynchronous, but also provides correct order of your messages(so they will be sent in the order you passed them).
Also it contains logic for delayed messages. Below you can find examples for sending different types of messages(We use `Sender` 
in these examples which expect your access token in properties)

* Simple text
```
Request request = TextMessage.builder()
                .text("Hi there!")
                .user(user)
                .build();

        sender.send(request);
        
sender.send(user, "Hi there!");
```

* Text with quick replies
```
Request request = QuickReplies.builder()
                .user(user)
                .text("Quick replies are here")
                .postback("Title", "qr_payload")
                .location() //adds Location quick reply to the list
                .email() // adds email quick reply to the list
                .phone() //adds phone quick reply to the list
                .build();
        
sender.send(request);
```

* Generic template
```
TemplateElement element = TemplateElement.builder()
            .title("Title")
            .subtitle("Subtitle")
            .imageUrl(IMAGE_URL)
            .build();

Request request = GenericTemplate.builder()
                .addElement(element)
                .user(user)
                .build();

sender.send(request);
```

* List template
```
TemplateElement element = TemplateElement.builder()
                .title("Title")
                .subtitle("Subtitle")
                .imageUrl(IMAGE_URL)
                .build();

Request request = ListTemplate.builder()
                .addElement(element).addElement(element).addElement(element)
                .user(user)
                .build();

sender.send(request);
```

* Button template
```
Request request = ButtonTemplate.builder()
                .addButton(new PostbackButton("Title", "button_postback"))
                .text("Button template")
                .user(user)
                .build();

sender.send(request);
```

* Media template
```
Request request = MediaTemplate.builder()
                .user(user)
                .element(new ImageElement(ATTACHMENT_ID))
                .build();

        sender.send(request);
```

* Attachment
```
Request request = Attachment.builder()
                .user(user)
                .attachmentId(ATTACHMENT_ID)
                .type(IMAGE)
                .build();

sender.send(request);

request = Attachment.builder()
                .user(user)
                .url(IMAGE_URL)
                .isReusable(true)
                .type(IMAGE)
                .build();

sender.send(request);
```

* Sender actions
```
sender.send(SenderAction.typingOn(user));
sender.send(SenderAction.typingOff(user));
```


# Development
*Messenger CDK is under development and for now it supports next types of events from Facebook Messenger:*
* Text
* Postback
* Location
* Referral
* Read
* Echo
* Delivery

