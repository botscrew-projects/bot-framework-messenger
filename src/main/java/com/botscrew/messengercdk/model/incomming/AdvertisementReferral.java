package com.botscrew.messengercdk.model.incomming;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdvertisementReferral {
    @JsonProperty("ad_id")
    private Long advertisementId;
    private String source;
    private String type;

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Long advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AdvertisementReferral{" +
                "advertisementId=" + advertisementId +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
