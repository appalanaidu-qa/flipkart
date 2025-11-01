package test_one;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility_library.ComplexReportFactory;
import utility_library.DesktopHelper;

public class Second_Test extends DesktopHelper{
	
	@Test
	public void second() {
		
		logger = ComplexReportFactory.getTest();
		logger.log(LogStatus.PASS, "Passed");
		
	}

}
