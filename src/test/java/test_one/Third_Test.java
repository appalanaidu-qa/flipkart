package test_one;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility_library.BaseHelper;
import utility_library.ComplexReportFactory;

public class Third_Test extends BaseHelper{

		
		@Test
		public void third() {
			logger = ComplexReportFactory.getTest();
			logger.log(LogStatus.PASS, "Passed");
			
		}

		@Override
		public void setUp() throws Exception {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void tearDown() {
			// TODO Auto-generated method stub
			
		}

	}
