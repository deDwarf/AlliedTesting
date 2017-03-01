package Selenium.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Element;

public class WebPage {

	public static WebDriver driver = null;
	public static WebDriverWait wait;
	public static final String home = "http://81.180.75.144:8080/elibrary/home";

	public WebPage()
	{
		System.setProperty("webdriver.chrome.driver", "C:/Software/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "C:/Software/geckodriver.exe");

		//ChromeOptions options = new ChromeOptions();
		//options.setExperimentalOption("excludeSwitches", Arrays.asList("ignore-certificate-errors"));
		//driver = new ChromeDriver(options);
		if (driver == null){
			driver = new FirefoxDriver();
			WebPage.driver.manage().window().maximize();
			wait = new WebDriverWait(driver, 4);
			WebPage.driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		}
}

/**
 * Ожидает появления элемента на странице
 * */
	public boolean isLoginFalse(){

	WebElement element = driver.findElement(By.id("iddanger"));
	boolean flag = WebPage.wait.until(ExpectedConditions.textToBePresentInElement(element, " "));

	return !flag;
}
	public boolean isLogged(){
	    try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lnk_signout")));
            return element.isEnabled();
	    }
        catch (TimeoutException e){//throws TimeoutException if didnt wait for element
	        return false;
        }
	}

	public void clickSignInBtn()
	{
		String sign_in_path = "//*[@id=\"btn_signin\"]";
    	/* Click sign in button */
		driver.findElement(By.xpath(sign_in_path)).click();
	}

	public HomePage clickSignOutBtn() throws InterruptedException {
		String sign_out_path = "//*[@id=\"lnk_signout\"]";
    	/* Click sign in button */
		WebElement el = driver.findElement(By.xpath(sign_out_path));
		wait.until(ExpectedConditions.elementToBeClickable(el)).submit();

		wait.until(ExpectedConditions.urlToBe(home)); // waiting page to refresh

        return new HomePage();
	}

	public void Quit() {
		if (driver != null){
			WebPage.driver.quit();
		}
	}
}
