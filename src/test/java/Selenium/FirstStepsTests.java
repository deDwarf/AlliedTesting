package Selenium;

import Selenium.PagePattern.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by User on 24.02.2017.
 */
@Listeners(CustomListener.class)
@Test
public class FirstStepsTests {

    HomePage page = new HomePage();

    @DataProvider
    public Object[][] positiveLoginParams(){
        return new Object[][] {
                {"worker@alliedtesting.com", "123"},
                {"user@alliedtesting.com", "123"},
        };
    }
    @DataProvider
    public Object[][] negativeLoginParams(){
        return new Object[][] {
                {"worker@alliedtesting.com", "231"},
                {"user@alliedtesting.com", "321"}
        };
    }

    @Test(dataProvider = "positiveLoginParams")
    public void positiveLogin(String name, String password){

        page.openPage();
        page.clickSignInBtn();
        page.loginAs(name, password);

        /*
        * Проверить существование кнопки логаут
        * Нажать логаут
        * Проверить существование кнопки логин
        */
    }
    @Test(dataProvider = "negativeLoginParams")
    public void negativeLogin(String name, String password){

        page.openPage();
        page.clickSignInBtn();
        page.loginAs(name, password);

        if (page.isLoginFalse()) Assert.fail("Logged in with wrong params. Although it shouldn`t be!");
    }

    @Test
    public void Registration() throws InterruptedException {

        page.openPage();
        page.clickRegisterBtn();
        page.registerAs("Username",
                "UserLastname",
                "elmoil2@mail.ru",
                "password",
                "password");
    }

    @Test
    public void gotoBooks(){

        page.openPage();
        page.clickAllBooksMenu();
    }

    @AfterClass
    public void terminate(){
        page.Quit();
    }
}
