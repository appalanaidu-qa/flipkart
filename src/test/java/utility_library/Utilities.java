package utility_library;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	
	private WebDriver driver;
    private static WebDriverWait wait;
	
	
	public static void clickElement(WebElement element2) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(element2));
            element.click();
            System.out.println("Clicked on the element successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click on the element: " + e.getMessage());
        }
    }

}
