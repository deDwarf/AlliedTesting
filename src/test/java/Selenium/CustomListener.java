package Selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import sun.security.krb5.internal.crypto.Des;
import test.BaseTest;
import test.bug92.TestBase;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by User on 25.02.2017.
 */
public class CustomListener implements ITestListener {

    private final String projectPath = System.getProperty("user.dir") + "\\";
    private final String templatePath = projectPath + "Reports\\READONLY_Template.html";
    private final String resultsPath = projectPath + "Reports\\TestResults\\";
    private final Path path = Paths.get(templatePath);

    private  String currentDir;

    private List<String> templateContent = new LinkedList<>();

    private int testCount = 0;
    private int allTestsIndex, failedTestsIndex;

    //Ignoring /t and spaces
    private boolean cleverEquals(String template, String suspect){
        Pattern pattern = Pattern.compile("^(\\s)*(\\t)*" + template + '$');
        Matcher matcher = pattern.matcher(suspect);
        return matcher.matches();
    }
    private List<String> FillSummaryTable(String status, ITestResult iTestResult){

        List<String> insert = new LinkedList<>();

        String description = iTestResult.getMethod().getDescription();
        long durationSec = (iTestResult.getEndMillis() - iTestResult.getStartMillis());

        String green = "#9FF781";
        String red = "#FE2E2E";
        String color = (iTestResult.getStatus() == iTestResult.SUCCESS)? green : red;

        insert.add("<tr style=\"background-color:" + color + "\">");
        insert.add("<td>" + testCount + "</td>");
        insert.add("<td>" + iTestResult.getMethod().getMethodName() + "</td>");
        insert.add("<td>" + status + "</td>");
        insert.add("<td>" + durationSec + " ms" + "</td>");
        insert.add("<td>" + description + "</td>");
        insert.add("</tr>");

        return insert;
    }
    private List<String> FillFailsTable(ITestResult iTestResult, String screenPath){

        String description = iTestResult.getMethod().getDescription();
        Throwable throwable = iTestResult.getThrowable();
        String methodName = iTestResult.getMethod().getMethodName();
        String insertScreen = "<a href=\"" + screenPath + "\">\n" +
                "  <img src=\"" + screenPath + "\" alt=\"" + methodName + testCount +
                "\" style=\"width:960;height:400pt;border:0;\">\n</a>";

        List<String> insert = new LinkedList<>();

        
        long durationSec = (iTestResult.getEndMillis() - iTestResult.getStartMillis());

        insert.add("<tr>");
        insert.add("<td>" + testCount + "</td>");
        insert.add("<td>" + methodName + "</td>");
        insert.add("<td>" + "Fail" + "</td>");
        insert.add("<td>" + durationSec + " ms" + "</td>");
        insert.add("<td>" + throwable.getMessage() + "<br />" + insertScreen + "</td>");
        insert.add("</tr>");

        return insert;
    }
    private String makeScreenshot(ITestResult iTestResult){
        String path = null;
        Object driver = iTestResult.getTestContext().getAttribute("WebDriver");

        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            path = currentDir + "src\\" + iTestResult.getMethod().getMethodName() + testCount + ".png";
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    public void onTestStart(ITestResult iTestResult) {testCount++;}

    public void onTestSuccess(ITestResult iTestResult) {

        List<String> insert = FillSummaryTable("Success", iTestResult);

        for(String line : insert){
            templateContent.add(++allTestsIndex, line);
            failedTestsIndex++;
        }
    }

    public void onTestFailure(ITestResult iTestResult) {

        String screenPath = makeScreenshot(iTestResult);

        List<String> insertF = FillFailsTable(iTestResult, screenPath);
        List<String> insertA = FillSummaryTable("Fail", iTestResult);

        for(String line : insertA){
            templateContent.add(++allTestsIndex, line);
            failedTestsIndex++;
        }
        for(String line : insertF){
            templateContent.add(++failedTestsIndex, line);
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {

        List<String> insert = FillSummaryTable("Skip", iTestResult);

        for(String line : insert){
            templateContent.add(++allTestsIndex, line);
            failedTestsIndex++;
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {}

    public void onStart(ITestContext iTestContext) {

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd', 'HH.mm");

        currentDir = resultsPath + date.format(new Date()) + "\\";
        boolean mkdirRes = (new File(currentDir + "src")).mkdirs();

        Assert.assertTrue(mkdirRes, "mkdir failed");

        try {
            templateContent = Files.readAllLines(path);
            final String allTestsFlag = "<!--Parser_AllTests-->";
            final String failedTestsFlag = "<!--Parser_FailedTests-->";

            for(int line=0; line < templateContent.size(); line++){
                String str = templateContent.get(line);
                if (cleverEquals(allTestsFlag, str))
                    allTestsIndex = line;
                if (cleverEquals(failedTestsFlag, str)) {
                    failedTestsIndex = line;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Probably template is missing");
        }
    }

    public void onFinish(ITestContext iTestContext) {

        FileWriter file = null;
        try {
            file = new FileWriter(currentDir + "report.html", true);

            for(String str: templateContent){
                file.write(str + '\n');
            }
            file.flush();
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()){
                desktop = Desktop.getDesktop();
                desktop.open(new File(currentDir + "report.html"));
            }

            System.out.println("REPORT SAVED AT " + currentDir);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
