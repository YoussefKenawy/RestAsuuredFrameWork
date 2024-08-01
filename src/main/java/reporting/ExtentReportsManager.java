package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.http.Header;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExtentReportsManager {
    private static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports createInstance(String fileName, String reportName, String documentTitle) {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);
        extentSparkReporter.config().setReportName(reportName);
        extentSparkReporter.config().setDocumentTitle(documentTitle);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setEncoding("UTF-8");

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        return extentReports;
    }

    public static String getReportName() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedTime = dateFormat.format(localDateTime);
        return "Test Report " + formattedTime + ".html";
    }

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }
    public static void logExceptionDetails(String log) {
        ExtentTest test = getTest();
        if (test != null) {
            test.fail(log);
        }
    }
    public static void passView(String log) {
        ExtentTest test = getTest();

        if (test != null) {
            test.pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
        }
    }

    public static void failureView(String log) {
        ExtentTest test = getTest();

        if (test != null) {
            test.fail(MarkupHelper.createLabel(log, ExtentColor.RED));
        }
    }

    public static void logInfoDetails(String log) {
        ExtentTest test = getTest();

        if (test != null) {
            test.info(MarkupHelper.createLabel(log, ExtentColor.GREY));
        }

    }

    public static void logJson(String json) {
        ExtentTest test = getTest();

        if (test != null) {
            test.info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
        }
    }


    public static void warningView(String log) {
        ExtentTest test = getTest();

        if (test != null) {
            test.warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
        }
    }
    public static void printHeaders(List<Header> HeaderList) {
        ExtentTest test = getTest();
       String[][]arrayHeaders= HeaderList.stream().map(header -> new String[]{header.getName(), header.getValue()}).toArray(String[][]::new);

        if (test != null)
        {
            test.info(MarkupHelper.createTable(arrayHeaders));
        }
    }
}
