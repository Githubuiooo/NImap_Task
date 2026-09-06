package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return new Object[][] {
            {"biswaz3111@gmail.com", "Biswa@339", "Valid"},
            {"biswaz3111@gmail.com", "wrongPassword", "Invalid"},
            {"wrong@email.com", "Biswa@339", "Invalid"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String email, String password,
                           String expectedResult) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);

        boolean signInClicked = loginPage.clickSignIn();

        if (expectedResult.equals("Valid")) {

            Assert.assertTrue(
                    signInClicked,
                    "Sign In button was not enabled for valid credentials"
            );

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(
                    ExpectedConditions.urlContains("/dashboard")
            );

            Assert.assertEquals(
                    driver.getCurrentUrl(),
                    "https://test.fieldforceconnect.com/dashboard",
                    "User was not redirected to Dashboard"
            );

        } else {

            Assert.assertFalse(
                    driver.getCurrentUrl().contains("/dashboard"),
                    "Invalid credentials redirected to Dashboard"
            );
        }
    }
}