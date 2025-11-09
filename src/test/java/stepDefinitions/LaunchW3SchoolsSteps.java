package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;

import static org.junit.Assert.*;
import utility_library.BaseHelper;
import utility_library.ComplexReportFactory;

import org.testng.ITestContext;

public class LaunchW3SchoolsSteps extends BaseHelper {

    WebDriver driver;

    @Before
    public void beforeScenario() throws Exception {
        // Simulate TestNG’s @BeforeMethod behavior
        initializeResultsFolders();
        initializeProperites();
        setUp();
        System.out.println("=== Before Scenario: Initialized reports and environment ===");
    }

    @Given("user launches the Chrome browser")
    public void user_launches_the_chrome_browser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger = ComplexReportFactory.getTest("LaunchW3Schools", "Verify W3Schools launch");
        logger.log(com.relevantcodes.extentreports.LogStatus.INFO, "Chrome browser launched");
    }

    @When("user opens {string}")
    public void user_opens(String url) {
        driver.get(url);
        logger.log(com.relevantcodes.extentreports.LogStatus.INFO, "Opened URL: " + url);
    }

    @Then("W3Schools homepage should be displayed")
    public void w3schools_homepage_should_be_displayed() {
        String title = driver.getTitle();
        logger.log(com.relevantcodes.extentreports.LogStatus.INFO, "Page Title: " + title);
        assertTrue("Title should contain 'W3Schools'", title.contains("W3Schools"));
        logger.log(com.relevantcodes.extentreports.LogStatus.PASS, "W3Schools homepage displayed successfully");
    }

    @After
    public void afterScenario() {
        // Simulate TestNG’s @AfterMethod behavior
        tearDown();
        ComplexReportFactory.closeTest("LaunchW3Schools");
        driver.quit();
        System.out.println("=== After Scenario: Closed browser and test report ===");
    }

    @AfterAll
    public static void afterAllScenarios() {
        // Simulate TestNG’s @AfterSuite behavior
        ComplexReportFactory.closeReport();
        System.out.println("=== After Suite: Report closed successfully ===");
    }

    @Override
    public void setUp() throws Exception {
        // You can initialize anything here, e.g., environment setup
        System.out.println("Setup complete from BaseHelper.");
    }

    @Override
    public void tearDown() {
        // Clean up code, e.g., quit drivers, DB connections
        System.out.println("Teardown complete from BaseHelper.");
    }
}
