package Selenium;

import Selenium.Pages.HomePage;
import Selenium.Pages.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by User on 23.02.2017.
 */

public class TestingTests {

    @Test(description = "LALALALALALLALALALALALALLALAL")
    public void test() {
        HomePage p = new HomePage();

        WebPage.driver.get(WebPage.home);

        p.clickSignInBtn();

        WebElement element;

        element = WebPage.driver.findElement(By.id("modal_signin"));
        boolean res = WebPage.wait.until(ExpectedConditions.attributeToBe(element, "aria-hidden", "false"));
        Assert.assertTrue(res, "Probably expected to press \"Login\" button");

        element = WebPage.driver.findElement(By.id("signin_email"));
        element.click();
        element.clear();
        element.sendKeys("worker@alliedtesting.com");

        element = WebPage.driver.findElement(By.id("signin_password"));
        element.click();
        element.clear();
        element.sendKeys("123");

        element.submit();


    }
}

