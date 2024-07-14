package com.webops.automation.java.testing.objects.reponses;
import lombok.Getter;

@Getter
public class GetBranches {

    private String timestamp;
    private String message;
    private Branch[] branches;

}