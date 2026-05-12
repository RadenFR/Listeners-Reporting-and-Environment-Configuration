package tests;

import core.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.core.DriverManager;
import org.example.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @Test(priority = 1, groups = {"Smoke"}, description = "Test Login Success")
    public void testLogin() {
        logger.info("Mulai test login dengan credential standard user");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        logger.info("User login dengan standard user");
        loginPage.login(config.getProperty("standardUser"), config.getProperty("password"));

        logger.info("Verify user berhasil login dan menampilkan halaman product");
        Assert.assertTrue(loginPage.isUserLoggedInSuccessfully(),
                "User diarahkan pada halaman produk");

        logger.info("Test berhasil dilakukan");
    }
}
