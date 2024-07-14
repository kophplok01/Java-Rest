package com.webops.automation.java.testing.stepDefinitions;
import com.webops.automation.java.testing.helpers.BrowserHelper;
import com.webops.automation.java.testing.pages.AbstractBasePage;
import com.webops.automation.java.testing.pages.Auth0Page;
import lombok.SneakyThrows;
import java.util.ArrayList;

public abstract class AbstractPages {
    private final ArrayList<AbstractBasePage> pages = new ArrayList<>();

    @SneakyThrows
    protected void Sleep(double waitInSeconds) {
        Thread.sleep((long) waitInSeconds * 1000);
    }

    protected Auth0Page auth0Page() {
        if (pageNotPresent(Auth0Page.class))
            pages.add(new Auth0Page(BrowserHelper.getPage()));
        return getPageObject(Auth0Page.class);
    }

    @SuppressWarnings("unchecked")
    private <T extends AbstractBasePage> T getPageObject(Class<T> pageObjectClass) {
        return (T) pages
                .stream()
                .filter(pageObjectClass::isInstance)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    private <T extends AbstractBasePage> boolean pageNotPresent(Class<T> PageObjectClassType) {
        return pages.stream()
                .noneMatch(PageObjectClassType::isInstance);
    }
}