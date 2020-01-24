package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.BoardsPageHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import util.DataProviders;

public class LoginPageTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTests() {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = new BoardsPageHelper(driver);
    }

    @Test
    public void loginToTrelloPositive() {

        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();

        Assert.assertTrue(boardsPage.verifyIfBoardsIconIsDisplayed());
        Assert.assertTrue(boardsPage.verifyIfPersonalBoardsHeaderIsDisplayed());

    }

    @Test
    public void loginIncorrectPassNegative() {

        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN, PASSWORD + "1");
        loginPage.waitPasswordError();

        Assert.assertTrue(loginPage.verifyIfPasswordErrorIsCorrect(), "Error message is not correct");

    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "dataProviderFirst2")
    public void loginIncorrectPassNegative1(String login, String password) {

        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(login, password);
        loginPage.waitPasswordError();

        Assert.assertTrue(loginPage.verifyIfPasswordErrorIsCorrect(), "Error password message is not correct");

    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "dataProviderFirst")
    public void loginIncorrectLoginNegative1 (String login, String password,String message) {

        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
//        loginPage.loginToTrello(LOGIN+"1", PASSWORD);
        loginPage.loginToTrello(login, password);
        loginPage.waitLoginError();

//        Assert.assertTrue(loginPage.verifyIfLoginErrorIsCorrect(), "Error login message is not correct");
        Assert.assertEquals(message, loginPage.getLoginError());
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "dataProviderSecond")
    public void loginIncorrectLoginNegative2 (String login, String password) {

        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        //loginPage.loginToTrelloNotAtlassian(LOGIN+"1",PASSWORD);
        loginPage.loginToTrello(login,password);
        loginPage.waitLoginError();
        Assert.assertEquals("There isn't an account for this email", loginPage.getLoginError());
    }
}
