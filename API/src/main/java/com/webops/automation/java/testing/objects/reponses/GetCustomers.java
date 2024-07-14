package com.webops.automation.java.testing.objects.reponses;
import lombok.Getter;

@Getter
public class GetCustomers {

    private String timestamp;
    private Customers[] customers;

}