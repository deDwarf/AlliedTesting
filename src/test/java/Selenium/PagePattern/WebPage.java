package Selenium.PagePattern;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class WebPage {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static final String home = "http://81.180.75.144:8080/elibrary";

	public WebPage()
	{
		System.setProperty("webdriver.chrome.driver", "C:/Software/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "C:/Software/geckodriver.exe");

		//ChromeOptions options = new ChromeOptions();
		//options.setExperimentalOption("excludeSwitches", Arrays.asList("ignore-certificate-errors"));
		//driver = new ChromeDriver(options);

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
}

/**
 * Ожидает появления элемента на странице
 * */
	public void WaitForElement(By by) {

		wait.until(ExpectedConditions.presenceOfElementLocated(by));

		/*
		try {
	        Thread.sleep(1000);                 //1000 milliseconds
	    } catch(InterruptedException ex) {
	    	System.out.println("Exception in Selenium.PagePattern.WebPage.Wait() method!");
	        Thread.currentThread().interrupt();
	    }
	    */

	}
	public void findAndClick(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	public void Quit() {
		if (driver != null){
			driver.quit();
		}
	}
}
