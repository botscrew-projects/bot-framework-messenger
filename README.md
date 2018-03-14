## Usage

* add maven dependency to your pom

```
<dependency>
    <groupId>com.botscrew</groupId>
    <artifactId>messenger.cdk</artifactId>
    <version>${messenger.cdk.version}</version>
</dependency>
```

* Define `facebook.messenger.accessToken` property

* You can implement the [UserProvider](src/main/java/com/botscrew/messenger/cdk/service/UserProvider.java) interface
and define this bean in spring configuration to have control over the user

* Annotate your processor class with `@ChatEventsProcessor` and start building the bot :)


## IDEAS
* add caching for messaging url's
