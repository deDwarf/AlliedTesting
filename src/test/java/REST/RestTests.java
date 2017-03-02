package REST;

import REST.Structures.Author;
import REST.Structures.Book;
import REST.Structures.Category;
import Selenium.FirstStepsTests;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Selenium.FirstStepsTests.*;

/**
 * :: TODO ::
 * 1. Login tests
 *      - positive login test
 *      - negative login test
 * 2. Registration test
 * 3. Get book test
 * 4. Push book test
 * 5. TestNG listener
 */
public class RestTests extends REST{

    @Test
    public void getElibraryHome(){
        try {
            this.getRequest(this.home_uri);
        } catch (Exception e) {
            Assert.fail("Unable to connect to " + this.home_uri);
        }
    }

    @Parameters({"username", "password"})
    @Test( groups = "auth")//, value = {"common_workspace", "extended_workspace"})
    public void auth(String username, String password){
        try {
            HttpResponse response = this.loginRequest(username, password);

            String str = EntityUtils.toString(response.getEntity());
            System.out.println(str);
            Assert.assertTrue(str.contains("true"),
                    "Login failed :: " +
                    "\nusername = " + username +
                    "\npassword = " + password + '\n');
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Unable to execute login request");
        }
    }

    @Test(dataProviderClass = FirstStepsTests.class, dataProvider = "positiveLoginParams")
    public void posLoginTest(String username, String password) throws Exception {

        HttpResponse response = this.loginRequest(username, password);
        String responseContent = this.getResponseData(response);

        Assert.assertTrue(responseContent.contains("true"), "Login failed :: " +
                "\nusername = " + username +
                "\npassword = " + password + '\n');
    }

    @Test(dataProviderClass = FirstStepsTests.class, dataProvider = "negativeLoginParams")
    public void negLoginTest(String username, String password) throws Exception {

        HttpResponse response = this.loginRequest(username, password);
        String responseContent = this.getResponseData(response);

        Assert.assertTrue(responseContent.contains("false"),
                "Unexpected result. Logged in with wrong params :: " +
                "\nusername = " + username +
                "\npassword = " + password + '\n');
    }

    @Test(groups = "guest_workspace")
    public void test_getBook(){
        int id = 10;
        try {
            Book book = this.getBook(id);

            Assert.assertTrue(book != null, "getBook returned null");
            //Ожидаем возврат нужной книги
            Assert.assertTrue(book.getId() == id,
                    "Requested book with id = " + id +" ,received with " + book.getId());

            prettyPrint(book);
            //Кинуть в логи книгу для листенера

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Getting book failed: id = " + id);
        }
    }

    //@Test(groups = "extended_workspace")
    public void test_removeBook(){
        int id = 1;
        try {
            this.removeBook(id);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Deletion failed");
        }
    }


    @DataProvider
    public Object[][] bookGenerator(){
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Falico Pavel Nicolay", new Date()));
        return new Object[][]{
                {new Book("Title",
                        "EN",
                        new Category("Category title"),
                        new Date(),
                        333,
                        authors)}
        };
    }

    @Test(dataProvider = "bookGenerator")
    public void test_addBook(Book book){
        try {
            this.createBook(book);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Cant create book");
        }
    }
}