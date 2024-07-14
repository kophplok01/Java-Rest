package com.webops.automation.java.testing.pages;

import com.webops.automation.java.testing.helpers.BrowserHelper;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import lombok.SneakyThrows;

public abstract class AbstractBasePage {

    protected final Page page;
    protected BrowserContext context = BrowserHelper.getContext();

    public AbstractBasePage(Page page) {
        this.page = page;
    }

    protected void Pause() {
        page.pause();
    }

    @SneakyThrows
    protected void Sleep(double timeoutInSeconds) {
        Thread.sleep((long) seconds(timeoutInSeconds));
    }

    protected double seconds(double seconds) {
        return seconds * 1000;
    }
}