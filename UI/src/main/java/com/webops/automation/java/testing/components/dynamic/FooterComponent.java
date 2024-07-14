package com.webops.automation.java.testing.components.dynamic;

import com.microsoft.playwright.Locator;
import com.webops.automation.java.testing.components.base.FooterComponentBase;

public class FooterComponent extends FooterComponentBase {
    public FooterComponent(Locator component) {
        FooterComponent.component = component;
    }

    public void isDisplayed() {
        super.isDisplayed();
    }

}