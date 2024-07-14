package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;

@Getter
public class Procedures {
    private int id;
    private String name;
    private String description;
    private boolean active;
    private Companies[] companies;
    private boolean manufacturerGlobal;

}