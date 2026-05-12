package org.example.page;

import org.example.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorAlert;

    @FindBy(className = "app_logo")
    private WebElement pageTitle;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        waitForElementToBeVisible(usernameInput);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    public boolean isUserLoggedInSuccessfully() {
        try {
            waitForElementToBeVisible(pageTitle);
            return pageTitle.isDisplayed() && pageTitle.getText().equals("Swag Labs");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            waitForElementToBeVisible(errorAlert);
            return errorAlert.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            waitForElementToBeVisible(errorAlert);
            return errorAlert.getText();
        } catch (Exception e) {
            return "";
        }
    }
}

