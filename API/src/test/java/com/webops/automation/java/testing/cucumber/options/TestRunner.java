package com.webops.automation.java.testing.cucumber.options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/webops/automation/java/testing/features",
        glue = {"com/webops/automation/java/testing/stepDefinitions", "com/webops/automation/java/testing/cucumber/hooks"},
        plugin = {"pretty", "json:target/cucumber-json/webops-automation-tests.json", "html:target/cucumber-html-reports/webops-automation-tests.html"}
)
public class TestRunner {
}
