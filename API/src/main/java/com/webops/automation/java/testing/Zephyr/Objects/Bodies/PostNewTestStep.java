package com.webops.automation.java.testing.Zephyr.Objects.Bodies;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostNewTestStep {
    private String mode;
    private Items [] items;


    public PostNewTestStep() {
        this.mode = "APPEND";
        this.items = new Items[1];
        this.items[0] = new Items();
    }

    public PostNewTestStep setMode(String mode) {
        this.mode = mode;
        return this;
    }
}
