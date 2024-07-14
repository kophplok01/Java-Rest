package com.webops.automation.java.testing.objects.bodies;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class GetOpsTerritories {


   private String messageId;
   private String timestamp;
   private int manufacturerId;

   public GetOpsTerritories() {
      this.messageId = "123456";
      this.timestamp = "2021-07-01T12:00:00Z";
      this.manufacturerId = 1015;
   }
}