package utility_library;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;



public class SeleniumHelper extends BaseHelper{
	
	/*
	 * Author: Appala Naidu
	 * Date: 
	 * Description: This is class contains generic methods to lanuch the browser, close the browser, taking screen shots,..etc  
	 *  	
	 */
	
	public static WebDriver driver;
	
	@SuppressWarnings("deprecation")
	public void setUp() throws Exception{
		
        System.out.println("execution starting...");
 
			
		/*
		 * String sGeckoDriverPath ="\\src\\SeleniumDrivers\\geckodriver.exe"; String
		 * sChromeDriverPath =
		 * "D:\\New folder\\Chrome Driver Latest\\ChromeDriver127\\chromedriver-win64\\chromedriver.exe"
		 * ; String sIEDriverPath =
		 * "D:\\Software\\IEDriverServer_Win32_4.2.0\\IEDriverServer.exe";
		 */
            
        //load a properties file        
		if(prop.getProperty("Browser").equals("Firefox")){
		    //System.setProperty("webdriver.gecko.driver",sGeckoDriverPath);
		    driver = new FirefoxDriver();
		}else if(prop.getProperty("Browser").equals("GoogleChrome")){
		    //System.setProperty("webdriver.chrome.driver",sChromeDriverPath);
		    driver = new ChromeDriver();
		}else if(prop.getProperty("Browser").equals("IE")){
		   // System.setProperty("webdriver.ie.driver",sIEDriverPath);
		    driver = new InternetExplorerDriver();
		}
		
		if(prop.getProperty("ENV").equals("QA")){
			driver.get(prop.getProperty("QA_URL"));
		}else if(prop.getProperty("ENV").equals("STG")){
			driver.get(prop.getProperty("STG_URL"));
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
//		GlobalVariables.sProject=prop.getProperty("Project");
//		GlobalVariables.sReleaseName=prop.getProperty("Release");
//		GlobalVariables.sEnvironment=prop.getProperty("ENV");
//		GlobalVariables.sUser=prop.getProperty("User");
		
	}

	   	
	public String capturePageScreenshot() {
		String image=null;
		try {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		int iScreenshotsCnt = new File(GlobalVariables.sScreenshotsFolderName).listFiles().length;
		String sFileName = "Screenshot"+(iScreenshotsCnt+1);
		String destFilePath = GlobalVariables.sScreenshotsFolderName+"\\"+sFileName+".png";
		File destFile = new File(destFilePath);
		FileUtils.copyFile(srcFile, destFile);
		image= logger.addScreenCapture(destFilePath);
		return image;
		}catch(Exception e) {
			System.out.println("Screenshot execption: " +e.getMessage());
					
		}
		return image;
	}
	
	public static void validateBrowserReady(){
		JavascriptExecutor objJS = ((JavascriptExecutor) driver);
		String status = objJS.executeScript("return document.readyState").toString();		
		while(!(status.equals("complete"))){
			try{
				Thread.sleep(1000);
			}catch(Exception e){				
			}
			status = objJS.executeScript("return document.readyState").toString();
		}
	}
	public static String getProperty(String KeyName) throws Exception{
        String sKeyValue="";
        Properties prop = new Properties();
        prop.load(new FileInputStream("Config.Properties"));
        sKeyValue=prop.getProperty(KeyName);
        return sKeyValue;
}




	public void tearDown(){
	    driver.quit();
	    }
}

