package Selenium.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;



/*
 * TODO:
 * 	- UI API
 */
public class HomePage extends WebPage {
	
	
	public HomePage(){}

	public void openPage()
	{
		driver.get(home);
	}
    
    public void clickRegisterBtn()
    {
    	String reg_path = "/html/body/div/header/div[1]/div/button[2]";
    	driver.findElement(By.xpath(reg_path)).click();
    }

    public void clickHomeMenu()
    {
    	String home_menu_path = "//*[@id=\"bs-example-navbar-collapse-1\"]/ul[1]/li[1]/a";
    	driver.findElement(By.xpath(home_menu_path)).click();
    }
    
    public void clickAllBooksMenu()
    {
    	String all_books_id = "idMybooks";
    	WebElement element = driver.findElement(By.id(all_books_id));
    	WebPage.wait.until(ExpectedConditions.elementToBeClickable(element));

    	element.click();

        boolean flag = WebPage.wait.until(ExpectedConditions.urlContains("elibrary/books"));
        Assert.assertTrue(flag);
    }
   
    public BooksPage loginAs(String name, String password)
    {
        WebElement element;

        element = driver.findElement(By.id("modal_signin"));
        boolean res = WebPage.wait.until(ExpectedConditions.attributeToBe(element, "aria-hidden", "false"));
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

        return new BooksPage();
    }

    /**
     * Expects
     * */
    public void registerAs(String firstName, String lastName, String email, 
    		String password, String passConfirm) {

        WebElement element;

        element = driver.findElement(By.id("modal_register"));
        boolean res = WebPage.wait.until(ExpectedConditions.attributeToBe(element, "aria-hidden", "false"));
        Assert.assertTrue(res, "Probably expected to press \"Registation\" button");

        driver.findElement(By.id("reg_first_name"))   .sendKeys(firstName);
        driver.findElement(By.id("reg_last_name"))    .sendKeys(lastName);
        driver.findElement(By.id("reg_email"))        .sendKeys(email);
        driver.findElement(By.id("reg_password"))     .sendKeys(password);
        driver.findElement(By.id("reg_password_conf")).sendKeys(passConfirm);

        driver.findElement(By.id("btnRegister")).click();

    }
    public String getRegistrationResult(){

        WebElement element;
        element = driver.findElement(By.xpath("//*[@id=\"reg_request_form\"]/div[1]"));
        WebPage.wait.until(ExpectedConditions.textToBePresentInElement(element, " ")); //ждем появления текста в теге <span>

        return element.getText();
    }
}
