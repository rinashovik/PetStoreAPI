package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener {



	ExtentReports extent;
	ExtentSparkReporter sparkReporter;
	ExtentTest test;

	String repName;


	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());

		repName = "Test-Report-"+"timeStamp"+".html";
		sparkReporter = new ExtentSparkReporter(".\\extent-reports\\"+repName); 

//		System.out.print(repName);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Host Name", "local Host");
		extent.setSystemInfo("Envoronment", "QA");
		extent.setSystemInfo("User", "Rina S.");

	}

public void onTestSuccess(ITestResult result) {


		test = extent.createTest(result.getName());

		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		//test.log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
		test.log(Status.PASS,"Test Passed");

}


public void onTestFailure(ITestResult result) {
	test = extent.createTest(result.getName());
	test.createNode(result.getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.FAIL,"Test Failed");
	test.log(Status.FAIL, result.getThrowable().getMessage());
	test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));

	

}
public void onTestSkipped(ITestResult result) {
	test = extent.createTest(result.getName());
	test.createNode(result.getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.SKIP, "Test Skipped");
	test.log(Status.SKIP, result.getThrowable().getMessage());


}

public void onFinish(ITestContext testContest) {


	extent.flush();

}



	}