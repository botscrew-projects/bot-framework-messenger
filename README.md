## Messenger CDK Spring Boot Starter

Messenger CDK is quick integration with Facebook Messenger chatbots based on Bot Framework

## Getting Started

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

## Customization

* You can implement MessengerUser interface to define your own user.

* To take control over users who is writing to your bot you can implement
`UserProvider` interface and define it as Spring Bean. It will pass user chat id
and page id to your implementation(you will be able to get this user in your 
method handlers).

* You can change `facebook.messenger.verify-token` property to set your own verification token
(It is 'test' by default)

* You can change `facebook.messenger.executor.*` properties to customize `TaskExecutor` for your needs


## Sending messages 
* You can autowire `com.botscrew.messengercdk.service.Sender` 
    to send messages with default access token from properties.

* You can autowire `com.botscrew.messengercdk.service.TokenizedSender`
    to send messages with your custom access token


## Messenger CDK is under development and for now it supports next types of events from Facebook Messenger:
* Text
* Postback
* Location

