package com.webops.automation.java.testing.cucumber.hooks;

import com.webops.automation.java.testing.Zephyr.Helpers.*;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.*;
import com.webops.automation.java.testing.Zephyr.Objects.Bodies.*;
import com.webops.automation.java.testing.Zephyr.Objects.Responses.*;
import io.restassured.response.Response;
import objects.PropertiesFile;
import objects.User;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;

public class Hooks {
    private static Scenario scenario;
    private static FoldersValues mainFolder;
    private static Folders folders;
    private static String mainFolderName;
    private static String tagName;
    private static FoldersValues scenarioFeature;
    private static TestCaseValues testCase;
    private static FoldersValues mainRegressionFolder;
    private static FoldersValues scenarioRegressionFeature;
    private static String keyProject;
    private static String testCycleKey;
    private static ProjectsValues project;
    private static final String scenarios = "@Cases or @OpsTerritories or @Customers or @Materials or @Catalogs or @Procedures or " +
            "@Branches";

    @Before(scenarios)
    public void InitiateApiTestRun(Scenario scenario) {
        try {
            Hooks.scenario = scenario;
            setupEnvironment();
            keyProject = PropertiesFile.Data.readProperties("zephyr.project.key")[0];
            project = ProjectsApi.getAllProjects().body().as(Projects.class)
                    .getProjectByName(keyProject);
            if (project == null) {
                throw new RuntimeException("The project with the key was not found: " + keyProject);
            }

            createFolderIfNeeded();

            createFeatureFolderIfNeeded();

            createTestCaseIfNeeded();

            createTestCycleMainFolderIfNeeded();

            createTestCycleFeatureFolderIfNeeded();

            createTestCycleIfNeeded();

            createTextExecutionIfNeeded("In Progress");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test initialization failed: " + e.getMessage());
        }
    }

    public static String getFirstTagNameWithoutAt() {
        String[] tagsArray = scenario.getSourceTagNames().toArray(new String[0]);
        if (tagsArray.length > 0) {
            return tagsArray[0].substring(1);
        } else {
            return null;
        }
    }

    @After(order = 999)
    public void clearSession(Scenario scenario) {
        if (!Objects.requireNonNull(getFirstTagNameWithoutAt()).equals("Closure")) {
            if (!scenario.getSourceTagNames().contains("@AfterIgnore")) {
                if (scenario.isFailed()) {
                    createTextExecutionIfNeeded("Fail");
                } else {
                    createTextExecutionIfNeeded("Pass");
                }
                User.setActiveUser(null);
            }
        }
    }

    private void setupEnvironment() {
        if (System.getProperty("environment") == null) {
            System.setProperty("environment", "dev");
        }
        if (System.getProperty("tenant") == null) {
            System.setProperty("tenant", "test");
        }
    }

    private void createFolderIfNeeded() {
        HashMap<String, String> params = new HashMap<>();
        params.put("projectId", String.valueOf(project.getId()));
        folders = FoldersApi.getFoldersByProjectId(params).body().as(Folders.class);
        mainFolderName = PropertiesFile.Data.readProperties("zephyr.main.api.folder")[0];
        mainFolder = folders.getFolderByNameAndType(mainFolderName, "TEST_CASE");
        if (mainFolder == null) {
            Response response = FoldersApi.createNewFolder(new PostNewFolder(null, mainFolderName, keyProject, "TEST_CASE"));
            if (response.getStatusCode() != 201) {
                throw new RuntimeException("Error creating parent folder: " + response.getStatusCode());
            }
            mainFolder = response.body().as(FoldersValues.class);
        }
    }

    private void createFeatureFolderIfNeeded() {
        tagName = getFirstTagNameWithoutAt();
        HashMap<String, String> params = new HashMap<>();
        params.put("projectId", String.valueOf(project.getId()));
        scenarioFeature = FoldersApi.getFoldersByProjectId(params).body().as(Folders.class).findNameInParentIdFolders(mainFolder.getId(), tagName);
        if (scenarioFeature == null) {
            Response response = FoldersApi.createNewFolder(new PostNewFolder(String.valueOf(mainFolder.getId()), tagName, keyProject, "TEST_CASE"));
            if (response.getStatusCode() != 201) {
                throw new RuntimeException("Error creating features folder: " + response.getStatusCode());
            }
            scenarioFeature = response.body().as(FoldersValues.class);
        }
    }

    private void createTestCaseIfNeeded() {
        HashMap<String, String> testParams = new HashMap<>();
        testParams.put("folderId", String.valueOf(scenarioFeature.getId()));
        TestCases testCases = TestCasesApi.getTestCasesByFolder(testParams).body().as(TestCases.class);
        testCase = null;
        if (testCases.getTotal() == 0) {
            Response response = TestCasesApi.createNewCase(new PostNewTestCase(
                    keyProject, scenario.getName(), scenarioFeature.getId(), "Approved"));
            TestCaseValues testCase = response.body().as(TestCaseValues.class);
            if (response.getStatusCode() != 201) {
                throw new RuntimeException("Error creating test case: " + response.getStatusCode());
            }
            testCase = response.body().as(TestCaseValues.class);
        } else {
            testCase = testCases.getTestCaseByName(scenario.getName());
            if (testCase == null) {
                Response response = TestCasesApi.createNewCase(new PostNewTestCase(
                        keyProject, scenario.getName(), scenarioFeature.getId(), "Approved"));
                testCase = response.body().as(TestCaseValues.class);
                if (response.getStatusCode() != 201) {
                    throw new RuntimeException("Error creating test case: " + response.getStatusCode());
                }
            }
        }
    }

    public void createTestCycleMainFolderIfNeeded() {
        HashMap<String, String> params = new HashMap<>();
        params.put("projectId", String.valueOf(project.getId()));
        mainRegressionFolder = FoldersApi.getFoldersByProjectId(params).body().as(Folders.class).getFolderByNameAndType(mainFolderName, "TEST_CYCLE");
        if (mainRegressionFolder == null) {
            Response response = FoldersApi.createNewFolder(new PostNewFolder(null, mainFolderName, keyProject, "TEST_CYCLE"));
            if (response.getStatusCode() != 201) {
                throw new RuntimeException("Error creating parent Test Cycle folder: " + response.getStatusCode());
            }
            mainRegressionFolder = response.body().as(FoldersValues.class);
        }
    }

    public void createTestCycleFeatureFolderIfNeeded() {
        HashMap<String, String> params = new HashMap<>();
        params.put("projectId", String.valueOf(project.getId()));
        scenarioRegressionFeature = FoldersApi.getFoldersByProjectId(params).body().as(Folders.class).findNameInParentIdFolders(mainRegressionFolder.getId(), tagName);
        if (scenarioRegressionFeature == null) {
            Response response = FoldersApi.createNewFolder(new PostNewFolder(String.valueOf(mainRegressionFolder.getId()), tagName, keyProject, "TEST_CYCLE"));
            if (response.getStatusCode() != 201) {
                throw new RuntimeException("Error creating features Test Cycle folder: " + response.getStatusCode());
            }
            scenarioRegressionFeature = response.body().as(FoldersValues.class);
        }
    }

    public void createTestCycleIfNeeded() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String testCyCleName = "Regression " + tagName + " " + date.format(formatter);
        TestCyclesValues getTestCycle = TestCycleApi.getTestCycles().body().as(TestCycles.class).getTestCycleByNameAndFolder(testCyCleName, scenarioRegressionFeature.getId());
        testCycleKey = null;
        if (getTestCycle == null) {
            Response response = TestCycleApi.createNewTestCycle(new PostNewTestCycle(keyProject, testCyCleName, scenarioRegressionFeature.getId()));
            if (response.getStatusCode() != 201) {
                throw new RuntimeException("Error creating test cycle: " + response.getStatusCode());
            }
            TestCycle testCycle = response.body().as(TestCycle.class);
            testCycleKey = testCycle.getKey();
        } else {
            testCycleKey = getTestCycle.getKey();
        }
    }

    public void createTextExecutionIfNeeded(String status) {
        HashMap<String, String> testParams = new HashMap<>();
        testParams.put("testCase", testCase.getKey());
        testParams.put("testCycle", testCycleKey);
        TestExecutions testExecutions = TestExecutionApi.getTestExecutions(testParams).body().as(TestExecutions.class);
        if (testExecutions.getTotal() > 0) {
            Response response = TestExecutionApi
                    .updateTestExecution(
                            new UpdateExecution(status), String.valueOf(testExecutions.getValues()[0].getId()));
            if (response.getStatusCode() != 200) {
                throw new RuntimeException("Error updating test execution: " + response.getStatusCode());
            }
        } else {
            Response response = TestExecutionApi.createNewTestExecutions(new PostNewExecution(keyProject, testCase.getKey(), testCycleKey, status));
            if (response.getStatusCode() != 201) {
                throw new RuntimeException("Error creating test execution: " + response.getStatusCode());
            }
        }
    }

    @AfterStep
    public void afterStep() {
        TestSteps testSteps = TestCasesApi.getTestSteps(testCase.getKey()).body().as(TestSteps.class);
        if (testSteps.getTotal() > 0 && testSteps.getValues()[0].getInline().getDescription() == null) {
            Response response = TestCasesApi.createTestStep(new PostNewTestStep().setMode("OVERWRITE"), testCase.getKey());
            Assert.assertEquals("Error creating test step", 201, response.getStatusCode());
        } else {
            boolean found = false;
            String stepName = System.getProperty("stepName");
            for (Values value : testSteps.getValues()) {
                if (stepName.equals(value.getInline().getDescription())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                Response response = TestCasesApi.createTestStep(new PostNewTestStep(), testCase.getKey());
                Assert.assertEquals("Error creating test step", 201, response.getStatusCode());
            }
        }
    }
}