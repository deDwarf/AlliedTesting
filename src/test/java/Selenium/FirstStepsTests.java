package Selenium;

import Selenium.PagePattern.BooksPage;
import Selenium.PagePattern.HomePage;
import Selenium.PagePattern.WebPage;
import org.testng.Assert;
import org.testng.AssertTest;
import org.testng.ITestContext;
import org.testng.annotations.*;

/**
 * Created by User on 24.02.2017.
 */
@Listeners(CustomListener.class)
@Test
public class FirstStepsTests {

    private HomePage page = new HomePage();

    @BeforeClass
    public void onStart(ITestContext context){
        context.setAttribute("WebDriver", WebPage.driver);
    }

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

    @Test(dataProvider = "positiveLoginParams", description = "Logins to worker@ and user@ profiles. " +
            "Success expected, correct params")
    public void positiveLogin(String name, String password){

        page.openPage();
        page.clickSignInBtn();
        BooksPage booksPage = page.loginAs(name, password);

        Assert.assertTrue(page.isLoginFalse(), "Login failed");

        booksPage.clickSignOutBtn();
        Assert.assertEquals(WebPage.driver.getCurrentUrl(), WebPage.home, "Signout failed");
    }
    @Test(dataProvider = "negativeLoginParams",  description = "Logins to worker@ and user@ profiles. " +
            "Fail expected, wrong params")
    public void negativeLogin(String name, String password){

        page.openPage();
        page.clickSignInBtn();
        page.loginAs(name, password);

        if (page.isLoginFalse()) Assert.fail("Logged in with wrong params. Although it shouldn`t!");
    }

    @Test(description = "Registration of an account. " +
            "Success expected, correct params")
    public void Registration() throws InterruptedException {

        page.openPage();
        page.clickRegisterBtn();
        page.registerAs("Username",
                "UserLastname",
                "elmoil5@mail.ru",
                "password",
                "password");

        String answer = page.getRegistrationResult();
        Assert.assertTrue(answer.contains("Thank you!"), "Registration failed with msg : " + answer);
    }

    @Test(description = "Testing 'All books' link on homepage")
    public void gotoBooks(){

        page.openPage();
        page.clickAllBooksMenu();
    }

    @AfterClass
    public void terminate(){
        page.Quit();
    }
}
