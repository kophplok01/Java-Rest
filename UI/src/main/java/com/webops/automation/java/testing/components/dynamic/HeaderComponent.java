package com.webops.automation.java.testing.components.dynamic;

import com.webops.automation.java.testing.components.base.HeaderComponentBase;
import com.microsoft.playwright.Locator;

public class HeaderComponent extends HeaderComponentBase {
    public HeaderComponent(Locator component) {
        HeaderComponent.component = component;
    }

    public void isDisplayed() {
        super.isDisplayed();
    }

}