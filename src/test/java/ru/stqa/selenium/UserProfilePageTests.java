package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;

public class UserProfilePageTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa4AutoBoard;
    UserProfilePageHelper userProfile;

    @BeforeMethod
    public void initTest(){
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa4AutoBoard = PageFactory.initElements(driver,CurrentBoardPageHelper.class);
        qa4AutoBoard.setName("QA4 Auto");
        userProfile = PageFactory.initElements(driver,UserProfilePageHelper.class);


        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        //----Open 'QA 4 Auto' board
        boardsPage.openBoard("QA4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();

        //----Open 'User Profile' page ----
        qa4AutoBoard.openUserProfile();
        userProfile.waitUntilPageIsLoaded();

    }

    @Test
    public void initialsVerification(){
        String initials = userProfile.getInitialsFromInitialsField();
        Assert.assertTrue(userProfile
                .verifyIfInitialsDisplayedCrrectly(initials), "Not all elements contains correct initials. It should be two elements");
    }

    @Test
    public void profileChangingVerification() {
        userProfile.changeInitials("V.V.");
//        userProfile.changeUserName("ksyshenjkateplova");
//        userProfile.changeBio("some text");
        userProfile.saveProfile();

    }

}