package AssurantExample.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import AssurantExampleHelper.Resource.ExtentReportClass;

public class Listeners extends BaseData implements ITestListener{

	ExtentTest test; //to create an test object
	//to import extent object from ExtentReportClass.java file
	ExtentReports extent = ExtentReportClass.getReportObject();
	//to keep all called test object methods in sequence by storing some id for the  method
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		//to store test result by getting method name, whichever is getting executed.
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //to set test in thread to create each method id
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//below code is to get any particular method id and print pass result, but not necessary to use, as if test passes then system will auto populate pass as result.
		extentTest.get().log(Status.PASS, "Test executed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//if test fails, then to get the failed error
		extentTest.get().fail(result.getThrowable());
		//to print the filed outcome screenshot
		String filepath = null;
		//to initialize particular called method's calls driver instead of this class driver
		//try and catch to print if error if code fails due to any other exception
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		//try and catch to print if error if code fails due to any other exception
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}	
}
