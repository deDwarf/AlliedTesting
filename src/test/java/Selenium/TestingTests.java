package Selenium;

import Selenium.PagePattern.HomePage;
import Selenium.PagePattern.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Created by User on 23.02.2017.
 */


public class TestingTests {

    @Test
    public void test() throws InterruptedException {
        HomePage page = new HomePage();
        page.openPage();
        page.clickRegisterBtn();
        Thread.sleep(3000);
        page.openPage();
    }
}
