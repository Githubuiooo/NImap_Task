package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    WebDriver driver;
    WebDriverWait wait;

    // Toast message
    By toastMessage = By.cssSelector("[role='status']");

    // Add Customer navigation
    By addCustomer = By.xpath(
            "//*[contains(normalize-space(.),'Add Customer')]");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Verify user is on Dashboard
    public boolean isAtDashboard() {
        return wait.until(
                ExpectedConditions.urlContains("/dashboard"));
    }

    // Get current URL
    public String getDashboardUrl() {
        return driver.getCurrentUrl();
    }

    // Check whether toast is displayed
    public boolean isToastDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(toastMessage))
                    .isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    // Get toast message
    public String getToastMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(toastMessage))
                .getText()
                .trim();
    }

    // Navigate to Add Customer
    public AddCustomerPage goToAddCustomer() {

        wait.until(
                ExpectedConditions.elementToBeClickable(addCustomer))
                .click();

        return new AddCustomerPage(driver);
    }
}