package com.webops.automation.java.testing.components.base;

import com.microsoft.playwright.Locator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FooterComponentBase {
    protected static Locator component;
    protected final static String urlToPro = "//a[@href='http://www.webops.com/']";

    protected void isDisplayed() {
        assertThat(component).isVisible();
        assertThat(component.locator(urlToPro)).isVisible();
    }
}