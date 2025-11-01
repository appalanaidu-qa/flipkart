package test_one;
import java.io.File;

import javax.mail.MessagingException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utility_library.ComplexReportFactory;
import utility_library.GlobalVariables;

public class EmailReporter  {

    
	private String generateReport(ITestContext context) {
	    StringBuilder report = new StringBuilder();
	    report.append("<html>");
	    report.append("<head><style>");
	    report.append("body { font-family: Arial, sans-serif; }");
	    report.append("h1 { color: #2c3e50; }");
	    report.append("h2 { color: #27ae60; }");
	    report.append("h3 { color: #c0392b; }");
	    report.append("p { font-size: 14px; }");
	    report.append("</style></head>");
	    report.append("<body>");
	    
	    report.append("<h1>Test Execution Report</h1>");
	    report.append("<p><strong>Total Tests Run:</strong> ").append(context.getAllTestMethods().length).append("</p>");
	    report.append("<p><strong>Passed Tests:</strong> <span style='color: #27ae60;'>").append(context.getPassedTests().size()).append("</span></p>");
	    report.append("<p><strong>Failed Tests:</strong> <span style='color: #c0392b;'>").append(context.getFailedTests().size()).append("</span></p>");
	    report.append("<p><strong>Skipped Tests:</strong> <span style='color: #f39c12;'>").append(context.getSkippedTests().size()).append("</span></p>");
	    
	    report.append("</body>");
	    report.append("</html>");
	    return report.toString();
	}


    public void sendExecutionReport(ITestContext context) {
    	String report = generateReport(context);
        // Call your existing email sending method here
        String smtpServer = "appsmtp-north.internal.abc.com"; // Replace with your SMTP server
        String smtpUser = "appala.naidu@abc.com"; // Replace with your SMTP username
        String smtpPassword = "abcd"; // Replace with your SMTP password
        String from = "appala.naidu@abc.com"; // Replace with your "from" email
        String to = "appala.naidu@abc.com"; // Receiver's email
        String cc = "appala.naidu@abc.com"; // CC email
        String subject = "Test Execution Report"; // Subject of the email
//        String path = "D:\\Projects\\app\\src\\Results\\Run_2024_10_06_16_31_15_pm\\RegressionReport.html";
        String resultsPath = GlobalVariables.resultsFolder+"\\RegressionReport.html";
        try {
        	File file = new File(resultsPath);
            if (!file.exists()) {
                System.out.println("The file does not exist at the specified path: " + resultsPath);
            }
            SendMailWithAttachment.sendEmail(smtpServer, smtpUser, smtpPassword, from, to, cc, subject, report, resultsPath);
            System.out.println("Email sent....");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}