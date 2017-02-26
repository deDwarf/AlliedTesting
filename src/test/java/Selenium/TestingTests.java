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
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 23.02.2017.
 */


public class TestingTests {

    private boolean cleverEquals(String template, String suspect){
        Pattern pattern = Pattern.compile("^(\\s)*(\\t)*" + template + '$');
        Matcher matcher = pattern.matcher(suspect);
        return matcher.matches();
    }

    @Test
    public void test() throws InterruptedException {
        String str = System.getProperty("user.dir");
        System.out.println(str);
    }
}
