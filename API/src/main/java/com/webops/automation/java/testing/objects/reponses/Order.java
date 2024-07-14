package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class Order {
    private String messageId;
    private String  timestamp;
    private Orders[] orders;
}