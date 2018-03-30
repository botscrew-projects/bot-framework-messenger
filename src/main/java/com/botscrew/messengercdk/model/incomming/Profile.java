package com.botscrew.messengercdk.model.incomming;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("profile_pic")
    private String profilePicture;
    private String gender;
    private String locale;
    private Integer timezone;
    @JsonProperty("last_ad_referral")
    private AdvertisementReferral lastAdReferral;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public AdvertisementReferral getLastAdReferral() {
        return lastAdReferral;
    }

    public void setLastAdReferral(AdvertisementReferral lastAdReferral) {
        this.lastAdReferral = lastAdReferral;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", gender='" + gender + '\'' +
                ", locale='" + locale + '\'' +
                ", timezone=" + timezone +
                ", lastAdReferral=" + lastAdReferral +
                '}';
    }
}
