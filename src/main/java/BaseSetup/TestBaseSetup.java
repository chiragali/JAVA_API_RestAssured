package BaseSetup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.extent.ReportsManager.*;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class TestBaseSetup {

    protected static ExtentReports extentReportLibrary;
    public static ExtentTest extentReportTest;

    /***
     * Launch the Application Under Test
     * @throws MalformedURLException - Thrown when URL is invalid
     * @throws InterruptedException - Thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted
     */

    @BeforeSuite(alwaysRun = true)
    public void setup() throws MalformedURLException, InterruptedException {
        extentReportLibrary = ExtentManager.createInstance("./test-results/apiReports/APITestResults.html");
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
    	System.out.println(""); 
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        System.out.println("");
    }

    @AfterMethod
    public void AfterMethod(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
          	extentReportTest.log(Status.FAIL,MarkupHelper.createLabel(result.getName()+ " Test case FAILED due to below issues:",ExtentColor.RED));
        	//extentReportTest.log(Status.FAIL).build());
        	extentReportTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
        	extentReportTest.log(Status.PASS,MarkupHelper.createLabel(result.getName()+ " Test Case PASSED", ExtentColor.GREEN));
        } else {
        	extentReportTest.log(
                    Status.SKIP,
                    MarkupHelper.createLabel(result.getName()+ " Test Case SKIPPED", ExtentColor.ORANGE));
        	extentReportTest.skip(result.getThrowable());
        }
    }

    @AfterTest
    public void AfterTest() {
    	extentReportLibrary.flush();
    }
    
    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        extentReportLibrary.flush();
    }

}
