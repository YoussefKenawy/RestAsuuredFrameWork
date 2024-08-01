package reporting;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.util.Arrays;

public class Setup implements ITestListener
{
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static ExtentReports extentReports;

    @Override
    public void onStart(ITestContext context) {
        // Initialize ExtentReports instance
        String reportName = ExtentReportsManager.getReportName();
        String reportPath = System.getProperty("user.dir") + "\\REPORTS\\" + reportName;
        extentReports = ExtentReportsManager.createInstance(reportPath, "Test API Automation Report", "Test ExecutionReport");
    }

    @Override
    public void onTestStart(ITestResult result)
    {
        ExtentTest test = extentReports.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName(), result.getMethod().getDescription());
        ExtentReportsManager.setTest(test);
    }

    public void onTestFailure(ITestResult result)
    {
        if (extentReports != null) {
            ExtentReportsManager.failureView(result.getThrowable().getMessage());
            String failureLogs = Arrays.toString(result.getThrowable().getStackTrace());
            failureLogs = failureLogs.replaceAll(",", "<br>");
            String formmatedTrace = "<details>\n" +
                    "    <summary>Click Here To See Exception Logs</summary>\n" +
                    "    " + failureLogs + "\n" +
                    "</details>\n";
            ExtentReportsManager.logExceptionDetails(formmatedTrace);
        }
    }

    @Override
    public void onFinish(ITestContext context)
    {
        if (extentReports != null) {
            extentReports.flush();
        }

    }
}
