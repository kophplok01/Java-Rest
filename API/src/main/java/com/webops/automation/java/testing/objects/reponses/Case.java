package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class Case {

    private String messageId;
    private String  timestamp;
    private Cases[] cases;

    public Cases getCaseByStatus(String status) {
        return Arrays.stream(cases)
                .filter(cases -> cases.getCaseStatus().equalsIgnoreCase(status))
                .findAny()
                .orElseThrow(NullPointerException::new);
    }



}