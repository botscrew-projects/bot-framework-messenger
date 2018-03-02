package com.botscrew.messenger.cdk.model.incomming;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Den Boyko
 * @version 1.0
 */
@Getter
@Setter
public class PaymentSummary {
    private String currency;
    @JsonProperty("payment_type")
    private String type;
    @JsonProperty("is_test_payment")
    private Boolean isTest;
    @JsonProperty("merchant_name")
    private String merchantName;
    @JsonProperty("requested_user_info")
    private List<String> requestedUserInfo;
    @JsonProperty("price_list")
    private List<Price> priceList;
}
