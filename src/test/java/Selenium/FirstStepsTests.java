package Selenium;

import Selenium.Pages.BooksPage;
import Selenium.Pages.HomePage;
import Selenium.Pages.WebPage;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

/**
 * Created by User on 24.02.2017.
 */
@Listeners(CustomListener.class)
@Test
public class FirstStepsTests {

    private HomePage page;

    @BeforeClass
    public void onStart(ITestContext context){
        page = new HomePage();
        context.setAttribute("WebDriver", WebPage.driver);
    }

        @DataProvider
        public static Object[][] positiveLoginParams(){
            return new Object[][] {
                    {"worker@alliedtesting.com", "123"},
                    {"user@alliedtesting.com", "123"},
            };
        }
    @DataProvider
    public static Object[][] negativeLoginParams(){
        return new Object[][] {
                {"worker@alliedtesting.com", "231"},
                {"user@alliedtesting.com", "321"}
        };
    }

    @Test(dataProvider = "positiveLoginParams", description = "Logins to worker@ and user@ profiles. " +
            "Success expected, correct params")
    public void positiveLogin(String name, String password) throws InterruptedException {

        page.openPage();
        page.clickSignInBtn();
        BooksPage booksPage = page.loginAs(name, password);

        Assert.assertTrue(page.isLogined(), "Login failed");
        page = booksPage.clickSignOutBtn();

        Assert.assertFalse(page.isLogined(), "Signout failed");
    }
    @Test(dataProvider = "negativeLoginParams",  description = "Logins to worker@ and user@ profiles. " +
            "Expecting error msg, wrong params")
    public void negativeLogin(String name, String password){

        page.openPage();
        page.clickSignInBtn();
        page.loginAs(name, password);

        if (page.isLoginFalse()) Assert.fail("Logged in with wrong params. Although it shouldn`t!");
    }

    //DataProvider
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

    @Test(description = "Fail demonstration")
    public void fail(){
        page.openPage();
        Assert.fail("Fail demo");
    }

    @AfterClass
    public void terminate(){
        page.Quit();
    }
}
