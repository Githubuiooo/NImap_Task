package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AddCustomerPage;
import pages.DashboardPage;
import pages.LoginPage;

public class AddCustomerTest extends BaseTest {

    @DataProvider(name = "customerData")
    public Object[][] customerData() {

        return new Object[][] {
            {"Test Customer One", "9876543210", "customer1@test.com", "Mumbai", "Success"},
            {"Test Customer Two", "9876543211", "customer2@test.com", "Pune", "Success"},
            {"", "9876543212", "customer3@test.com", "Mumbai", "Fail"},
            {"Test Customer Four", "", "customer4@test.com", "Pune", "Fail"}
        };
    }

    @Test(dataProvider = "customerData")
    public void addCustomerTest(String name, String mobile,
                                String email, String address,
                                String expectedResult) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail("biswaz3111@gmail.com");
        loginPage.enterPassword(System.getenv("FFC_PASSWORD"));

        loginPage.clickSignIn();

        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(
                dashboardPage.isAtDashboard(),
                "User was not redirected to Dashboard"
        );

        AddCustomerPage customerPage =
                dashboardPage.goToAddCustomer();

        customerPage.addCustomer(
                name,
                mobile,
                email,
                address
        );

        if (expectedResult.equalsIgnoreCase("Success")) {

            Assert.assertTrue(
                    customerPage.isSuccessToastDisplayed(),
                    "Success toast was not displayed for: " + name
            );

            Assert.assertFalse(
                    customerPage.getSuccessToastText().isEmpty(),
                    "Success toast message was empty"
            );

        } else {

            Assert.assertTrue(
                    customerPage.isValidationMessageDisplayed(),
                    "Expected validation message for invalid data: " + name
            );
        }
    }
}