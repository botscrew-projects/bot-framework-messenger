package com.botscrew.messenger.cdk.model;

import com.botscrew.framework.flow.model.Bot;
import com.botscrew.framework.flow.sender.Platform;

/**
 * @author Den Boyko
 * @version 1.0
 */
public interface MessengerBot extends Bot {
    String getAccessToken();
    String getAppId();
    default Platform getPlatform(){return Platform.FACEBOOK;}
}
