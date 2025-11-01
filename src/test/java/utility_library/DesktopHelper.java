package utility_library;


import java.io.File;
import java.io.IOException;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class DesktopHelper extends BaseHelper {
	public static Screen screen; 
	public String basePath = "D:\\New folder\\Screenshots\\";
	//public String customPath = "T:\\SGD Z Drive\\MadhukarK";
	public static ExtentTest logger;
	
	@BeforeMethod
    public void setUp() throws IOException  {
        // Set up SikuliX
        screen = new Screen();
       System.out.println("execution starting...");
 		
	}


	public String capturePageScreenshotDesktop() {
	
		String image=null;
		try {
		ScreenImage screenshot = screen.capture();
		
		int iScreenshotsCnt = new File(GlobalVariables.sScreenshotsFolderName).listFiles().length;
		String sFileName = "Screenshot"+(iScreenshotsCnt+1);
		
		String destFilePath=screenshot.getFile(GlobalVariables.sScreenshotsFolderName, sFileName);
		
		
		//String destFilePath = GlobalVariables.sScreenshotsFolderName;
		//destFilePath=screenshot.save(destFilePath);
		
		image= logger.addScreenCapture(destFilePath);
		return image;
		}catch(Exception e) {
			System.out.println("Screenshot execption: " +e.getMessage());
					
		}
		return image;
	}
	
	public void findPath(String imageName) {
	    try {
	        long startTime = System.currentTimeMillis();
	        long timeout = 10000;  // 10 seconds
	        Match match = null;

	        // Keep checking until the image is found or the timeout is reached
	        while (System.currentTimeMillis() - startTime < timeout) {
	            // Try to find the image with a smaller timeout (say, 1 second)
	            match = screen.exists(basePath + imageName, 1.0);
	            if (match != null) {
	                break;
	            }
	        }

	        // Check if the image was found within the timeout period
	        if (match != null) {
	            // Log success
	            logger.log(LogStatus.PASS, "Image Found: " + imageName);
	        } else {
	            // Log failure and throw assertion error
	            logger.log(LogStatus.FAIL, "Find", "Image not found: " + imageName);
	            throw new AssertionError("Image not found: " + imageName);
	        }
	    } catch (Exception e) {
	        // Log failure for other types of exceptions
	        logger.log(LogStatus.FAIL, "Find", e.getMessage());
	        e.printStackTrace();
	    }
	}

	public void click(String imageName) {
	    try {
	        long startTime = System.currentTimeMillis();
	        long timeout = 10000;  // 10 seconds
	        Match match = null;

	        // Keep checking until the image is found or timeout is reached
	        while (System.currentTimeMillis() - startTime < timeout) {
	            // Try to find the image with a smaller timeout (say, 1 second)
	            match = screen.exists(basePath + imageName, 1.0);
	            if (match != null) {
	                break;
	            }
	        }

	        // Check if the image was found within the timeout period
	        if (match != null) {
	            // Click on the image using its full path
	            screen.click(match);
	            
	            // Log success
	            logger.log(LogStatus.PASS, "Click", "Click Successful");
	        } else {
	            // Log failure and throw assertion error
	            logger.log(LogStatus.FAIL, "Click", "Image not found: " + imageName);
	            throw new AssertionError("Image not found: " + imageName);
	        }
	    } catch (FindFailed e) {
	        // Log failure
	        logger.log(LogStatus.FAIL, "Click", e.getMessage());
	    }
	}


		
	
	public void doubleClick()  
	{
		try {
		screen.doubleClick();
//        logger.log(LogStatus.PASS,"Double Click Successful");
		}catch (Exception e) {
			logger.log(LogStatus.FAIL,"Double Click",e);
		}
    }
	public void waitForImage(String imageName)
	{
		try {
		screen.wait(basePath + imageName, 2);
		}catch (FindFailed e) {
			logger.log(LogStatus.FAIL,"Wait",e.getMessage());
		}
		
	}
	public void clickEnter() {
	    // Simulate pressing the "Enter" key
	    screen.type(Key.ENTER);
	}

	public void clickTab() {
	    // Simulate pressing the "Tab" key
	    screen.type(Key.TAB);
	}

	public void type(String text) {
	    try {
	        // Type the given text using SikuliX
	        screen.type(text);
	        
	        // If typing is successful, log success
//	        logger.log(LogStatus.PASS, "Text Typed Successfully");
	    } catch (Exception e) {
	        // If any exception occurs during typing, log failure
	        logger.log(LogStatus.FAIL, "Type", e.getMessage());
	    }
	}

		
	
	
	public void search(String path)throws FindFailed
	{
		screen.type(path);
	}
	
	public void dynamicScroll(String imageName) throws FindFailed {
	    boolean targetFound = false;

	    try {
	        while (!targetFound) {
	            // Perform scrolling down
	            screen.wheel(1, 3); // Scroll down by 3 steps
	            System.out.println("Scrolling down...");

	            // Check if the target image exists on the screen after scrolling
	            if (screen.exists(basePath +imageName) != null) {
	                // If the target image is found, set the flag to true
	                targetFound = true;
	                screen.click(basePath +imageName); // Click on the target image
	                screen.doubleClick();
	            }

	            // You can add a delay here if needed before the next scroll
	            Thread.sleep(1000); // Wait for 1 second (1000 milliseconds)
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public void dynamicWait(String imageName)
	{
		        // Maximum time to wait for the image (in seconds)
		        int timeoutSeconds = 10; // Example: wait for up to 30 seconds

		        // Timestamp when the waiting started
		        long startTime = System.currentTimeMillis();

		        try {
		            while (true) { // Loop indefinitely until the image is found or timeout is reached
		                // Check if the target image exists on the screen
		                Match match = screen.exists(basePath+imageName);

		                if (match != null) {
		                    // If the target image is found, print a message and break out of the loop
		                    System.out.println("Target image found!");
		                    break;
		                }

		                // If the image is not found, check if timeout is reached
		                long elapsedTime = System.currentTimeMillis() - startTime;
		                if (elapsedTime >= timeoutSeconds * 30) {
		                    // If timeout is reached, print a message and break out of the loop
		                    System.out.println("Timeout reached. Target image not found.");
		                    break;
		                }

		                // Wait for a short duration before the next check
		                Thread.sleep(1000); // Wait for 1 second (1000 milliseconds)
		            }
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
	}

	public static void buttonDynamicClick() {
	    // Path to the image files of the enabled and disabled states of the button
	    String enabledButtonImagePath = "D:\\New folder\\Screenshots\\Delete.png";
	    String disabledButtonImagePath = "D:\\New folder\\Screenshots\\disabledDelete.png";
	    String cancelPopupImagePath = "D:\\New folder\\Screenshots\\cancel.png"; // Assuming this is the cancel button image

	    try {
	        while (true) {
	            // Check for the enabled button
	            Match enabledMatch = screen.exists(enabledButtonImagePath, 1.0); // Check for 1 second
	            // Check for the disabled button
	            Match disabledMatch = screen.exists(disabledButtonImagePath, 1.0); // Check for 1 second

	            if (enabledMatch != null) {
	                // If the enabled button is found, click it
	                screen.click(enabledMatch);
	                screen.type(Key.ENTER); // Simulate ENTER press after clicking
	                System.out.println("Enabled button clicked.");
	                break; // Exit the loop after clicking the enabled button
	            } else if (disabledMatch != null) {
	                // If the disabled button is found, wait and then cancel
	                screen.type(Key.ENTER);
	                screen.wait(2.0); // Wait for 2 seconds
	                screen.click(cancelPopupImagePath); // Click the cancel button
	                System.out.println("Disabled button found. Cancel clicked.");
	                break; // Exit the loop after handling the disabled button
	            } else {
	                // If neither button is found, print a message and wait before trying again
	                System.out.println("Button not found, retrying...");
	            }

	            // Add a small delay to avoid overloading the system with constant checks
	            screen.wait(1.0); // Wait for 1 second
	        }
	    } catch (FindFailed e) {
	        // Handle Sikuli image finding exceptions
	        System.err.println("Failed to find button: " + e.getMessage());
	    } catch (Exception e) {
	        // Handle other general exceptions
	        System.err.println("Error occurred: " + e.getMessage());
	    }
	}



	public void deleting_dup_udr() {
	    // Path to the image files of the enabled and disabled states of the button
	    String enabledButtonImagePath = "D:\\New folder\\Screenshots\\Delete";
	    String disabledButtonImagePath = "D:\\New folder\\Screenshots\\disabledDelete";
	    @SuppressWarnings("unused")
		String cancelPopup = "D:\\New folder\\Screenshots\\cancelPopup";

	    try {
	        while (true) { // Loop indefinitely
	            // Check if the enabled button exists on the screen
	            Match enabledMatch = screen.exists(enabledButtonImagePath);

	            // Check if the disabled button exists on the screen
	            Match disabledMatch = screen.exists(disabledButtonImagePath);

	            if (enabledMatch != null) {
	                // If the enabled button is found, click on it
	                screen.click(enabledMatch);
	                screen.click(basePath + "Yes");
	                screen.click(basePath + "SaveButton");
	                screen.wait(1.0);
//	                type(Key.ENTER);
	                System.out.println("Button clicked.");
	            } else if (disabledMatch != null) {
	                // If the disabled button is found, handle appropriately
	                // Press the Enter key to interact with the disabled button
	                type(Key.ENTER);
	                // Wait for a short duration
	                screen.wait(1.0);
	                // Click on the cancel popup
	                screen.click(basePath+"cancelButton");
	                System.out.println("Button is disabled. Handled.");
	            } else {
	                // If neither button is found, print a message
	                System.out.println("Button not found.");
	            }

	            // You can add a delay here to control the frequency of checks
	            Thread.sleep(1000); // Wait for 1 second (1000 milliseconds)
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void scrolling_to_find() {
		try{
		Pattern targetImage = new Pattern("D:\\New folder\\Screenshots\\VFUK_FIXED_CDR_SEARCH");
        
        // Set a timeout for finding the image (e.g., 60 seconds)
        int timeout = 120;
        boolean imageFound = false;
        
        // Start the loop to press down arrow until the image is found or timeout occurs
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < timeout * 1000) {
            // Check if the image exists on the screen
            if (screen.exists(targetImage) != null) {
                imageFound = true;
                screen.doubleClick();
                System.out.println("Image found!");
                break;
            }
            
            // Press the down arrow key
            screen.type(Key.DOWN);
            
            // Optional: Add a small delay between key presses to avoid overwhelming the system
            Thread.sleep(1);
        }
        
        if (!imageFound) {
            System.out.println("Image not found within the timeout period.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
	

		


		public void closeApplication()
		{
			try {
				type(Key.ALT + Key.F4);
				type(Key.ENTER);
				type("C");
				type(Key.ENTER);
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		

		@Override
		public void tearDown() {
			// TODO Auto-generated method stub
			
		}

		
	}
	
	
	
//	public void customPath(String fileName) throws FindFailed
//	{
//		screen.find(customPath +fileName);
//		logger.log(LogStatus.PASS,"Path Find Successful");
//	}
	



 
