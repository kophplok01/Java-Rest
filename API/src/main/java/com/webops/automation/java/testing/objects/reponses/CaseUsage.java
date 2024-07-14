package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;

@Getter
public class CaseUsage {

    private String messageId;
    private String  timestamp;
    private String message;
    private int code;
    private DetailsCaseUsage[] details;

}