package Selenium;

import Selenium.PagePattern.HomePage;
import Selenium.PagePattern.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 23.02.2017.
 */

public class TestingTests {

    @Test(description = "LALALALALALLALALALALALALLALAL")
    public void test() {

    KEK kkeke = new KEK();
    lalala(kkeke);
    }

    class KEK {
        public KEK(){
            System.out.println("kek CONSTR");
        }
    }
    void lalala(KEK ke){
        System.out.println("KEK FUNC");
    }
}

