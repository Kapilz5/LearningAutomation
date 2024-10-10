package AssurantExampleHelper.Resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportClass {

	public static ExtentReports getReportObject()
	{
		//To create report
		String path = System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Selenium Practice");
		reporter.config().setDocumentTitle("Selenium Test Report");
		
		//To make it available in html
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Performed by", "Kapil Jain");
		return extent;
	}
}
