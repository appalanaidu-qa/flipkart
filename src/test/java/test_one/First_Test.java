package test_one;


import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility_library.ComplexReportFactory;
import utility_library.SeleniumHelper;

public class First_Test extends SeleniumHelper {
	@Test (testName = " First test execution")
    public  void first() {
		logger = ComplexReportFactory.getTest();
		logger.log(LogStatus.PASS, "Passed");
      
    }
}

