package com.webops.automation.java.testing.helpers;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ViewportSize;
import io.cucumber.java.Scenario;
import java.util.Locale;

public final class BrowserHelper {
    private static Playwright playwright;
    private static BrowserType browserType;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;
    private static ViewportSize viewportSize;
    private static Boolean mobile = false;
    private static Scenario scenario;

    public static Page createPage(Scenario scenario) {
        BrowserHelper.scenario = scenario;
        if (scenario.getSourceTagNames().contains("@Mobile") || scenario.getSourceTagNames().contains("@MobileUpgrade")) {
            page = getContext(new ViewportSize(375, 812)).newPage();
        }
        if (page == null || mobile) {
            page = getContext(new ViewportSize(1280, 720)).newPage();
        }
        return page;
    }

    public static Page getPage() {
        return page;
    }

    public static void setFocusOnFirstTab() {
        page = getContext().pages().get(0);
    }

    public static void setFocusOnLastTab() {
        page = getContext().pages().get(getContext().pages().size() - 1);
    }

    public static void closeLastTab() {
        Page page = getContext().pages().get(getContext().pages().size() - 1);
        page.bringToFront();
        page.waitForClose(page::close);
        getContext().pages().get(getContext().pages().size() - 1).bringToFront();
    }

    public static BrowserContext getContext() {
        if (context == null) {
            return getContext(new ViewportSize(1280, 720));
        }
        return context;
    }

    public static boolean hasCookie(String cookieName) {
        return getContext().cookies().stream().anyMatch(cookie -> cookie.name.equalsIgnoreCase(cookieName));
    }

    public static BrowserContext getContext(ViewportSize screenSize) {
        if (viewportSize != null &&
                (viewportSize.width == screenSize.width && viewportSize.height == screenSize.height)) {
            return context;
        }

        if (browser == null) {
            createBrowser();
        }
        if (!browser.contexts().isEmpty()) {
            browser.contexts().forEach(BrowserContext::close);
        }

        if (screenSize.height == 0) {
            context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null).setTimezoneId("Europe/Brussels"));
        } else {
            context = browser.newContext(new Browser.NewContextOptions().setViewportSize(screenSize).setTimezoneId("Europe/Brussels"));
        }
        viewportSize = screenSize;

        return context;
    }

    public static void createBrowser() {
        playwright = Playwright.create();
        switch (System.getProperty("browser").toLowerCase(Locale.ROOT)) {
            case "firefox":
                browserType = playwright.firefox();
                break;
            case "webkit":
                browserType = playwright.webkit();
                break;
            default:
                browserType = playwright.chromium();
                break;
        }

        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(
                Boolean.parseBoolean(System.getProperty("headless"))));
    }

    public static void close() {
        context.clearCookies();
        context.pages().forEach(Page::close);
        page = null;
    }

    public static void clearCookies() {
        context.clearCookies();
    }
}