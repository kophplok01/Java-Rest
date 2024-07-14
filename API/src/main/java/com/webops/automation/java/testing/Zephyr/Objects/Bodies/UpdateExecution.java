package com.webops.automation.java.testing.Zephyr.Objects.Bodies;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UpdateExecution {
    private String statusName;

    public UpdateExecution(String statusName) {
        this.statusName = statusName;
    }
}
