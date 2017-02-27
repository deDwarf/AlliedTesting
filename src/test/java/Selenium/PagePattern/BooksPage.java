package Selenium.PagePattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by User on 27.02.2017.
 */
public class BooksPage extends WebPage{

    public BooksPage(){}

    public HomePage clickSignOutBtn(){
        String id_signoutBtn = "lnk_signout";

        WebElement element = driver.findElement(By.id(id_signoutBtn));
        WebPage.wait.until(ExpectedConditions.elementToBeClickable(element));

        element.click();

        return new HomePage();
    }
}
