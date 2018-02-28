package com.botscrew.messenger.cdk.domain;

public class DefaultInterceptor implements MessengerInterceptor<PreMessageProcessingAction> {
    @Override
    public boolean onAction(PreMessageProcessingAction preMessageProcessingAction) {
        return false;
    }
}
