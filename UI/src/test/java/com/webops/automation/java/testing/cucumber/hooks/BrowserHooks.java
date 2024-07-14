package com.webops.automation.java.testing.cucumber.hooks;

import com.webops.automation.java.testing.config.TestingConfig;
import com.webops.automation.java.testing.helpers.BrowserHelper;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import objects.Url;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@ContextConfiguration
@SpringBootTest(classes = {TestingConfig.class})
public class BrowserHooks {

    @Before("@login")
    public void InitiateWebOpsTestRun(Scenario scenario) {
        if (System.getProperty("environment") == null) {
            System.out.println("Environment not set, defaulting to dev");
            System.setProperty("environment", "dev");
        }
        if (System.getProperty("tenant") == null) {
            System.setProperty("tenant", "test8");
        }
        if (System.getProperty("browser") == null) {
            System.setProperty("browser", "chrome");
        }
        if (System.getProperty("headless") == null) {
            System.setProperty("headless", "true");
        }
        BrowserHelper.createPage(scenario).navigate(Url.getBaseUrl());
    }

    @After()
    public void AfterCloseBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                BrowserHelper.createPage(scenario).screenshot(new Page.ScreenshotOptions()
                        .setPath(Paths.get(getScreenshotLocation(scenario)))
                        .setFullPage(true));
            } catch (PlaywrightException ignored) {
                //Catch exception in case page has been closed when error occurs - otherwise this will fail all following scenarios
            }
        }
        BrowserHelper.close();
    }

    private static String getScreenshotLocation(Scenario scenario) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String fileName = LocalDateTime.now().format(myFormatObj) + "_" +
                scenario.getName().toLowerCase(Locale.ROOT).replace(' ', '_') + ".png";

        if (System.getProperties().containsKey("screenshotFolder")) {
            fileName = System.getProperty("screenshotFolder") + File.separator + fileName;
        } else {
            fileName = "screenshots" + File.separator + fileName;
        }

        return fileName;
    }
}
