# Messenger CDK Spring Boot Starter

Messenger CDK is quick integration with Facebook Messenger chatbots based on Bot Framework

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

#### Subscribe webhook

By default path for events from Messenger is `/messenger/events`.

You can change it in property `facebook.messenger.events-path`


### User

You can implement MessengerUser interface to define your own user.

### UserProvider

To take control over users who are writing to your bot you can implement
`UserProvider` interface and define it as Spring Bean. It will pass user chat id
and page id to your implementation(you will be able to get this user in your 
method handlers).

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

# Development
*Messenger CDK is under development and for now it supports next types of events from Facebook Messenger:*
* Text
* Postback
* Location

