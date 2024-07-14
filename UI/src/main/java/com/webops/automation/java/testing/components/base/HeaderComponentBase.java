package com.webops.automation.java.testing.components.base;

import com.microsoft.playwright.Locator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HeaderComponentBase {
    protected static Locator component;
    protected final static String lblLogo = "//img[@src='../images/logos/webops-logo-header41px.png']";

    protected void isDisplayed() {
        assertThat(component).isVisible();
        assertThat(component.locator(lblLogo)).isVisible();
    }
}