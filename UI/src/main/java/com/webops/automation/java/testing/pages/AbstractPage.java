package com.webops.automation.java.testing.pages;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import java.util.List;

public abstract class AbstractPage extends AbstractBasePage {
    public AbstractPage(Page page) {
        super(page);
    }

    protected void Fill(String selector, String value) {
        page.fill(selector, value);
    }

    protected void Type(String selector, String value) {
        page.type(selector, value);
    }

    protected void Type(String selector, String value, Page.TypeOptions options) {
        page.type(selector, value, options);
    }

    protected void SelectOption(String selector, String value) {
        page.selectOption(selector, new SelectOption().setLabel(value));
    }

    protected void Click(String selector) {
        page.click(selector);
    }

    protected void Click(String selector, Page.ClickOptions options) {
        page.click(selector, options);
    }

    protected void Click(ElementHandle element) {
        element.click();
    }

    protected void ClickWaitNewPage(Locator locator) {
        Page newPage = context.waitForPage(() -> locator.click());
        newPage.waitForLoadState();
    }

    protected Boolean IsVisible(String selector, double waitInSeconds) {
        return page.isVisible(selector, new Page.IsVisibleOptions().setTimeout(seconds(waitInSeconds)));
    }

    protected Boolean IsVisible(String selector) {
        return page.isVisible(selector, null);
    }

    protected Boolean IsVisible(String selector, String text) {
        return page.isVisible(selector + ":has-text(\"" + text + "\")");
    }

    protected boolean WaitForVisible(String selector) {
        return WaitForVisible(selector, null, null);
    }

    protected boolean WaitForVisible(String selector, double waitInSeconds) {
        return WaitForVisible(selector, null, new Page.WaitForSelectorOptions().setTimeout(seconds(waitInSeconds)));
    }

    protected boolean WaitForVisible(String selector, String text) {
        return WaitForVisible(selector, text, null);
    }

    protected Boolean WaitForVisible(String selector, String text, Page.WaitForSelectorOptions options) {
        text = text == null ? "" : text;
        if (!text.isBlank()) {
            selector = selector + ":has-text(\"" + text + "\")";
        }

        try {
            return page.waitForSelector(selector, options).isVisible();
        } catch (PlaywrightException e) {
            return false;
        }
    }

    protected ElementHandle QuerySelector(ElementHandle parent, String selector) {
        return parent.querySelector(selector);
    }

    protected List<ElementHandle> QuerySelectorAll(String selector) {
        return page.querySelectorAll(selector);
    }

    protected Locator Locator(String selector) {
        return page.locator(selector);
    }

    protected Locator Locator(String selector, String text) {
        return page.locator(selector + ":has-text(\"" + text + "\")");
    }
    //endRegion Common Buttons
}