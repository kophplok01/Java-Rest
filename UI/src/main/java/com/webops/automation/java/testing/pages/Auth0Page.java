package com.webops.automation.java.testing.pages;
import com.webops.automation.java.testing.helpers.BrowserHelper;
import objects.User;
import com.microsoft.playwright.Page;
import lombok.SneakyThrows;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Auth0Page extends AbstractPage {

    private static final String txtUserName = "input[name='loginName']";
    private static final String txtPassword = "input[name='password']";
    private static final String btnStubSubmit = "button[type='submit']";

    public Auth0Page(Page page) {
        super(page);
    }

    public void isDisplayed() {
        page.waitForLoadState();
        assertThat(Locator(txtUserName)).isVisible();
        assertThat(Locator(txtPassword)).isVisible();
        assertThat(Locator(btnStubSubmit)).isVisible();
    }

    public void login(User user) {
        login(user.getProvider(), user.getAccountname(), user.getPassword());
    }
    public void login(String provider, String accountName, String password) {
        switch (provider) {
            case "test8":
                    Fill(txtUserName, accountName);
                    Fill(txtPassword, password);
                    Click(btnStubSubmit);
                break;
            case "test4":
                //tbd
            default:
                throw new IllegalArgumentException("Unsupported provider");
        }
    }

    @SneakyThrows
    public void saveCookies() {
        Sleep(2);
        com.microsoft.playwright.options.Cookie playwrightCookie = BrowserHelper.getContext().cookies().stream()
                .filter(c -> c.name.equalsIgnoreCase("tbd"))
                .findFirst().orElseThrow(NoSuchFieldException::new);
        playwrightCookie = BrowserHelper.getContext().cookies().stream()
                .filter(c -> c.name.equalsIgnoreCase("tbd"))
                .findFirst().orElseThrow(NoSuchFieldException::new);
    }
}