package testsuite;

/**
 * 2. Create the package ‘testsuite’ and create the
 * following class inside the ‘testsuite’ package.
 * 1. LoginTest
 * 3. Write down the following test into ‘LoginTest’ class
 * 1. userSholdLoginSuccessfullyWithValidCredentials
 * Enter “tomsmith” username
 * Enter “SuperSecretPassword!” password
 * Click on ‘LOGIN’ button
 * Verify the text “Secure Area”
 * 2. verifyTheUsernameErrorMessage
 * Enter “tomsmith1” username
 * Enter “SuperSecretPassword!” password
 * Click on ‘LOGIN’ button
 * Verify the error message “Your username
 * is invalid!”
 * 3. verifyThePasswordErrorMessage
 * Enter “tomsmith” username
 * Enter “SuperSecretPassword” password
 * Click on ‘LOGIN’ button
 * Verify the error message “Your password
 * is invalid!”
 */

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    String baseURL = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseURL);
    }

    @Test
    public void userShouldLogInSuccessfullyWithValidCredentials() {
        // Find username field and Type the email address
        WebElement emailField = driver.findElement(By.id("username"));
        emailField.sendKeys("tomsmith");
        // Find password field and Type the password
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("SuperSecretPassword!");
        // Find the Login button and click on it
        WebElement loginLink = driver.findElement(By.xpath("//i[@class = 'fa fa-2x fa-sign-in']"));
        loginLink.click();
        String expectedText = "Secure Area";
        //Find the actual text element and get the text from element
        String actualText = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(expectedText, actualText);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//i[@class = 'fa fa-2x fa-sign-in']")).click();
        String expectedMessage = "Your username is invalid!\n" + "×";
        String actualMessage = driver.findElement(By.xpath("//div[@class = 'flash error']")).getText();
        Assert.assertEquals("Invalid message",expectedMessage, actualMessage);

    }

    @Test
    public void verifyThePasswordErrorMessage() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        driver.findElement(By.xpath("//i[@class = 'fa fa-2x fa-sign-in']")).click();
        String expectedMessage = "Your password is invalid!\n" +"×";
        String actualMessage = driver.findElement(By.xpath("//div[@class = 'flash error']")).getText();
        Assert.assertEquals("Invalid text",expectedMessage, actualMessage);

    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}

