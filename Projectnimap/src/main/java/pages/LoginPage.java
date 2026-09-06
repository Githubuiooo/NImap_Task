package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    By emailField = By.cssSelector(
            "input[placeholder='Enter Email or Mobile Number']");

    By passwordField = By.cssSelector(
            "input[name='password']");

    By signInButton = By.xpath(
            "//button[@type='submit' and .//span[normalize-space()='Sign In']]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterEmail(String email) {

        WebElement emailBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailField));

        emailBox.click();
        emailBox.clear();
        emailBox.sendKeys(email);
        emailBox.sendKeys(Keys.TAB);
    }

    public void enterPassword(String password) {

        WebElement passwordBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField));

        passwordBox.click();
        passwordBox.clear();
        passwordBox.sendKeys(password);
        passwordBox.sendKeys(Keys.TAB);
    }

    public boolean clickSignIn() {

        try {
            WebElement button = wait.until(
                    ExpectedConditions.presenceOfElementLocated(signInButton));

            wait.until(driver -> button.isEnabled());

            button.click();

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}