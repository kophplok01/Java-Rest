package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;

@Getter
public class Contact {

    private String phoneNumber;
    private String faxNumber;
    private Email[] emails;

}