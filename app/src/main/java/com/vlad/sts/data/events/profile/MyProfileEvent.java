package com.vlad.sts.data.events.profile;


import com.vlad.sts.data.Profile;

public class MyProfileEvent {
    final private Profile profile;

    public MyProfileEvent(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }
}