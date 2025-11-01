package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlipkartLoginSteps {

    WebDriver driver;

    @Given("user navigates to Flipkart login page")
    public void user_navigates_to_flipkart_login_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com");
        // Close the initial popup if present
        try {
            driver.findElement(By.xpath("//button[contains(text(),'✕')]")).click();
        } catch (Exception e) {
            // Ignore if not present
        }
        driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
    }

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys("your_username"); // Replace with valid
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("your_password"); // Replace with valid
    }

    @When("clicks on login button")
    public void clicks_on_login_button() {
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
    }

    @Then("user should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        // Wait and validate login success - this is just a sample
        try {
            Thread.sleep(3000); // Replace with WebDriverWait in real projects
            boolean loggedIn = driver.findElements(By.xpath("//div[contains(text(),'My Account')]")).size() > 0;
            if (loggedIn) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
