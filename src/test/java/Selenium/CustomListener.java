package Selenium;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Files;

/**
 * Created by User on 25.02.2017.
 */
public class CustomListener implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {
        System.out.println("test start");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("testsuccess");
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("testfailed");
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("testskipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("---");
    }

    public void onStart(ITestContext iTestContext) {
        System.out.println("onStart");
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("onFinish");
    }
}
