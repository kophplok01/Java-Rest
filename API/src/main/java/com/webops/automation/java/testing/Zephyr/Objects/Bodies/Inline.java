package com.webops.automation.java.testing.Zephyr.Objects.Bodies;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Inline {

    private String description;
    private String testData;
    private String expectedResult;
    private String reflectRef;
    public Inline() {
        this.description = System.getProperty("stepName");
        this.testData = System.getProperty("stepDescription");
        this.expectedResult = System.getProperty("stepExpectedResult");
        this.reflectRef = "Not available yet";
    }

}
