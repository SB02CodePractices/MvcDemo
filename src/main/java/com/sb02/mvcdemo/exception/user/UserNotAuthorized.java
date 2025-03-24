package com.sb02.mvcdemo.exception.user;

public class UserNotAuthorized extends UserException {
    public UserNotAuthorized(String message) {
        super(message);
    }
}
