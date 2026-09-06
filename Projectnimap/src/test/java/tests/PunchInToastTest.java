package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;

public class PunchInToastTest extends BaseTest {

    @Test(description = "Verify user can reach Dashboard after login")
    public void punchInToastTest() {

        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail("YOUR_VALID_EMAIL");
        loginPage.enterPassword("YOUR_VALID_PASSWORD");

        boolean loginClicked = loginPage.clickSignIn();

        Assert.assertTrue(
                loginClicked,
                "Sign In button was not enabled"
        );

        // Verify Dashboard
        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(
                dashboardPage.isAtDashboard(),
                "User was not redirected to Dashboard"
        );

        System.out.println("Dashboard reached successfully.");
    }
}