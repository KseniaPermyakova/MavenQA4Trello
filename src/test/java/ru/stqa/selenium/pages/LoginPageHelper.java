package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase {
    @FindBy (id = "user")
    WebElement userField;

    @FindBy (id = "login")
    WebElement loginButton;

    @FindBy (xpath = "//button[@id='login-submit']//span[contains(text(),'Log in')]")
    WebElement theSecondLoginButton;

    @FindBy (xpath = "//button[@id='login-submit']//span[contains(text(),'Continue')]")
    WebElement continueButton;

    @FindBy (id = "password")
    WebElement passwordField;

    @FindBy (xpath = "//p[@class='error-message']")
    WebElement loginError;   

    public LoginPageHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(loginButton,30);
    }

    public void enterAtlLogin(String login) {
        enterValueToTheField(userField, login);
    }

    public void clickLoginAtWithAtlassian() {
        waitUntilElementIsClickable(loginButton, 10);
        loginButton.click();
    }

    public void clickContinueButton() {
        waitUntilElementIsClickable(continueButton,30);
        continueButton.click();
    }

    public void loginToTrelloAsAtlassian(String login, String password) {
        this.enterAtlLogin(login);
        this.clickLoginAtWithAtlassian();
        this.clickContinueButton();
        this.enterAtlPasswordAndLogin(password);
    }

    public void enterAtlPasswordAndLogin(String password) {
        waitUntilElementIsClickable(passwordField, 30);
        passwordField.sendKeys(password);
        waitUntilElementIsClickable(theSecondLoginButton,30);
        theSecondLoginButton.click();
    }

    public void waitPasswordError() {
        waitUntilElementIsVisible(By.xpath("//div[@id='login-error']/span"), 10);
    }

    public boolean verifyIfPasswordErrorIsCorrect() {
        return driver.findElement(By.xpath("//div[@id='login-error']/span"))
                .getText().contains("Incorrect email address and / or password.");
    }

    public void enterLogin(String login) {
        userField.click();
        userField.clear();
        userField.sendKeys(login);
    }

    public void enterPassword(String password) {
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        waitUntilElementIsClickable(loginButton, 3);
        loginButton.click();
    }

    public void loginToTrello(String login, String password) {
        this.enterLogin(login);
        this.enterPassword(password);
        this.clickLogin();
    }

    public void waitLoginError() {
        waitUntilElementIsVisible(By.xpath("//p[@class='error-message']"), 30);
    }

    public boolean verifyIfLoginErrorIsCorrect() {
        return driver.findElement(By.xpath("//p[@class='error-message']"))
                .getText().contains("There isn't an account for this email");
    }

    public String getLoginError(){
        return loginError.getText();
    }
}
