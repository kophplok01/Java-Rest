package com.webops.automation.java.testing.Zephyr.Objects.Bodies;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Items {
    private Inline inline;

    public Items() {
        this.inline = new Inline();
    }

}
