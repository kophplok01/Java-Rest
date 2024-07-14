package com.webops.automation.java.testing.objects.bodies;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostMultipleCustomers {
    private String messageId;
    private String timestamp;
    private int manufacturerId;
    private CustomerMasters[] customerMasters;


    public PostMultipleCustomers() {
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2023-10-11T13:15:30Z";
        this.manufacturerId = 1015;
        this.customerMasters = new CustomerMasters[2];
        this.customerMasters[0] = new CustomerMasters();
        this.customerMasters[1] = new CustomerMasters();
    }
}