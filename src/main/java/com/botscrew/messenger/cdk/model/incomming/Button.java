package com.botscrew.messenger.cdk.model.incomming;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Den Boyko
 * @version 1.0
 */
@Getter
@Setter
public class Button {
    private String type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String payload;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("payment_summary")
    private PaymentSummary paymentSummary;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("webview_height_ratio")
    private String webviewRatio;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("messenger_extensions")
    private Boolean messengerExtensions;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("fallback_url")
    private String fallback;

}
