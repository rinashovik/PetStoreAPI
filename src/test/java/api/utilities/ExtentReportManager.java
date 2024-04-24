package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener {
	
	
	
	ExtentReports extent;
	ExtentSparkReporter sparkReporter;
	ExtentTest test;
	
	String repName;


	public void onStatrt(ITestContext testContext) {
		
		repName = "Test-Report.html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); 
		
//		System.out.print(repName);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	
	
	}
	
public void onTestSuccess(ITestResult result) {
	
	
		test = extent.createTest(result.getName());
	
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
	
	
}

public void onTestFail(ITestResult result) {
	
}
public void onTestSkipped(ITestResult result) {

}

public void onFinish(ITestContext testContest) {


	extent.flush();
	
}



	}


