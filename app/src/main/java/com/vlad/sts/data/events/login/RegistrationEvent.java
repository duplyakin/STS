package com.vlad.sts.data.events.login;

public class RegistrationEvent extends AbstractLoginEvent{

    public RegistrationEvent(boolean isSucceed, String username) {
        super(isSucceed, username);
    }
}
