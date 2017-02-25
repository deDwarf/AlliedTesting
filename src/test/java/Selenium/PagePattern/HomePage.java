package Selenium.PagePattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/*
 * TODO:
 * 	- UI API
 */
public class HomePage extends WebPage {
	
	
	public HomePage()
	{
		super();
	}
	
	public void openPage()
	{
		WebPage.driver.get(home);
	}
	
	
    public void clickSignInBtn()
    {
    	String sign_in_path = "/html/body/div/header/div[1]/div/button[1]";
    	/* Click sign in button */
    	WebPage.driver.findElement(By.xpath(sign_in_path)).click();
    }
    
    public void clickSignOutBtn()
    {
    	String sign_out_path = "/html/body/div/header/div[1]/div/a";
    	/* Click sign in button */
    	WebPage.driver.findElement(By.xpath(sign_out_path)).click();
    }
    
    public void clickRegisterBtn()
    {
    	String reg_path = "/html/body/div/header/div[1]/div/button[2]";
    	driver.findElement(By.xpath(reg_path)).click();
    }

    public void clickHomeMenu()
    {
    	String home_menu_path = "//*[@id=\"bs-example-navbar-collapse-1\"]/ul[1]/li[1]/a";
    	WebPage.driver.findElement(By.xpath(home_menu_path)).click();
    }
    
    public void clickAllBooksMenu()
    {
    	String all_books_id = "idMybooks";
    	WebElement element = driver.findElement(By.id(all_books_id));
    	wait.until(ExpectedConditions.elementToBeClickable(element));

    	element.click();

        boolean flag = wait.until(ExpectedConditions.urlContains("elibrary/books"));
        Assert.assertTrue(flag);
    }
   
    public void loginAs(String name, String password)  
    {
        WebElement element;

        element = driver.findElement(By.id("modal_signin"));
        boolean res = wait.until(ExpectedConditions.attributeToBe(element, "aria-hidden", "false"));
        Assert.assertTrue(res, "Probably expected to press \"Login\" button");

        element = driver.findElement(By.id("signin_email"));
        element.click();
        element.clear();
        element.sendKeys(name);

        element = driver.findElement(By.id("signin_password"));
        element.click();
        element.clear();
        element.sendKeys(password);

        element.submit();
    }
    public boolean isLoginTrue(){
        boolean flag = true;
        return true;
    }
    public boolean isLoginFalse(){

        WebElement element = driver.findElement(By.id("iddanger"));
        boolean flag = wait.until(ExpectedConditions.textToBePresentInElement(element, " "));
        return !flag;
    }

    public void registerAs(String firstName, String lastName, String email, 
    		String password, String passConfirm) {
        WebElement element;

        element = driver.findElement(By.id("modal_register"));
        boolean res = wait.until(ExpectedConditions.attributeToBe(element, "aria-hidden", "false"));
        Assert.assertTrue(res, "Probably expected to press \"Registation\" button");

        driver.findElement(By.id("reg_first_name"))   .sendKeys(firstName);
        driver.findElement(By.id("reg_last_name"))    .sendKeys(lastName);
        driver.findElement(By.id("reg_email"))        .sendKeys(email);
        driver.findElement(By.id("reg_password"))     .sendKeys(password);
        driver.findElement(By.id("reg_password_conf")).sendKeys(passConfirm);

        driver.findElement(By.id("btnRegister")).click();

        element = driver.findElement(By.xpath("//*[@id=\"reg_request_form\"]/div[1]"));
        wait.until(ExpectedConditions.textToBePresentInElement(element, " ")); //ждем появления текста в теге <span>

        String answer = element.getText();
        Assert.assertTrue(answer.contains("Thank you!"), "Registration failed with msg : " + answer);
    }

    }