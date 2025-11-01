package utility_library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import test_one.EmailReporter;

public abstract class BaseHelper {
	public static ExtentReports report;
	public static ExtentTest logger;
	public Properties prop;
	
	
	@BeforeMethod
	public void beforeMethod(Method caller) throws Exception{
		initializeResultsFolders();
		initializeProperites();
		setUp();		
		ComplexReportFactory.getTest(caller.getName(), "Automation Script Execution Summary Report");
	}
	
	public abstract void setUp() throws Exception;
	public abstract void tearDown();
	
	public Properties initializeProperites() throws IOException, FileNotFoundException {
		prop = new Properties();
        prop.load(new FileInputStream("Config.Properties"));
		return prop;
	}
	public void initializeResultsFolders() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_a");
		Date date = new Date();
		String sTimeStamp = "Run_" + dateFormat.format(date).toString();
		String path = new File(".").getCanonicalPath();
		String sResultsPath = path + "\\src\\Results\\" + sTimeStamp;
		String sScreenshotsPath = path + "\\src\\Results\\" + sTimeStamp + "\\Screenshots\\";
		if (GlobalVariables.resultsFolder.equals("")) {
			new File(sResultsPath).mkdirs();
			new File(sScreenshotsPath).mkdirs();
			GlobalVariables.resultsFolder = sResultsPath;
			GlobalVariables.sScreenshotsFolderName=sScreenshotsPath;
		
		}
			
	}

	@AfterMethod
	public void afterMethod(Method caller) {
		tearDown();
		ComplexReportFactory.closeTest(caller.getName());
	
	}
	
	@AfterSuite
	public void afterSuite(ITestContext context) {
		ComplexReportFactory.closeReport();
		if(prop.getProperty("SendEmailReport").equals("True")){
		EmailReporter emailReporter = new EmailReporter();
		emailReporter.sendExecutionReport(context);
	}
	}
	

}
