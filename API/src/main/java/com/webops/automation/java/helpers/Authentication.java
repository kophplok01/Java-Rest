package com.webops.automation.java.testing.helpers;
import helpers.DBUser;
import objects.User;

public class Authentication extends AbstractRequest {

    public static void authenticate(String actor) {
        actor = actor.replaceAll(" ", "");
        User.setActiveUser(new User(actor, false));
        DBUser.setUserDetails();
        Webops.loginWebops();
    }
}