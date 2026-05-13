package tests;

import core.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import core.DriverManager;
import org.example.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @Test(priority = 1, groups = {"smoke"}, description = "Test Login Success", retryAnalyzer = core.RetryAnalyzer.class)
    public void testLogin() {
        logger.info("Mulai test login dengan credential standard user");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        logger.info("User login dengan standard user");
        loginPage.login(config.getProperty("standardUser"), config.getProperty("password"));

        logger.info("Verify user berhasil login dan menampilkan halaman product");
        Assert.assertTrue(loginPage.isUserLoggedInSuccessfully(),
                "User diarahkan pada halaman produk");

        logger.info("Verify tidak ada error message yang ditampilkan setelah login sukses");
        Assert.assertFalse(loginPage.isErrorMessageDisplayed(),
                "User tidak mendapatkan error message setelah berhasil login");

        logger.info("Test login success berhasil dijalankan");
    }

    @Test(priority = 2, groups = {"smoke"}, description = "Test Login Failed", retryAnalyzer = core.RetryAnalyzer.class)
    public void testFailed() {
        logger.info("Mulai test login dengan credential failed user");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        logger.info("User login dengan failed user");
        loginPage.login(config.getProperty("failedUser"), config.getProperty("password"));

        logger.info("Verify user gagal login dan mendapatkan error message");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "User mendapatkan error message");

        logger.info("Test login failed berhasil dijalankan");
    }
}
