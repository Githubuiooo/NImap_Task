package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCustomerPage {

    WebDriver driver;
    WebDriverWait wait;

    // Add Customer fields
    By customerNameField = By.xpath(
            "//input[@name='customerName' or @id='customerName' or @placeholder='Customer Name']");

    By mobileField = By.xpath(
            "//input[@name='mobile' or @id='mobile' or @placeholder='Mobile Number']");

    By emailField = By.xpath(
            "//input[@name='email' or @id='email' or @placeholder='Email']");

    By addressField = By.xpath(
            "//textarea[@name='address' or @id='address'] | //input[@name='address']");

    By saveButton = By.xpath(
            "//button[contains(translate(normalize-space(.),'SAVE','save'),'save') " +
            "or contains(translate(normalize-space(.),'SUBMIT','submit'),'submit')]");

    // Toast
    By successToast = By.cssSelector("[role='status']");

    // Validation message
    By validationMessage = By.xpath(
            "//*[contains(@class,'error') or " +
            "contains(@class,'invalid-feedback') or " +
            "contains(@class,'text-danger')]");

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterCustomerName(String name) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(customerNameField));

        element.clear();
        element.sendKeys(name);
    }

    public void enterMobile(String mobile) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(mobileField));

        element.clear();
        element.sendKeys(mobile);
    }

    public void enterEmail(String email) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailField));

        element.clear();
        element.sendKeys(email);
    }

    public void enterAddress(String address) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(addressField));

        element.clear();
        element.sendKeys(address);
    }

    public void addCustomer(String name, String mobile,
                             String email, String address) {

        enterCustomerName(name);
        enterMobile(mobile);
        enterEmail(email);
        enterAddress(address);

        wait.until(
                ExpectedConditions.elementToBeClickable(saveButton))
                .click();
    }

    public boolean isSuccessToastDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(successToast))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessToastText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(successToast))
                .getText()
                .trim();
    }

    public boolean isValidationMessageDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(validationMessage))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}