package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.BoardsPageHelper;
import ru.stqa.selenium.pages.CurrentBoardPageHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import util.DataProviders;

public class CurrentBoardPageTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa4AutoBoard;

    @BeforeMethod
    public void initTest() {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa4AutoBoard = PageFactory.initElements(driver, CurrentBoardPageHelper.class);
        qa4AutoBoard.setName("QA4 Auto");

        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
    }

    @Test
    public void verifyIfLoadedBoardIsCorrect() {

        boardsPage.openBoard("QA4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();
        Assert.assertTrue(qa4AutoBoard.titleVerification());
    }

    @Test
    public void createNewList() {

        boardsPage.openBoard("QA4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();
        String nameList = "New List";
        if (qa4AutoBoard.getAddButtonName().equals("Add another list")) {
            nameList = qa4AutoBoard.genRandomString(7);
            if (qa4AutoBoard.existList(nameList)) {
                nameList = qa4AutoBoard.stringWithRandomNumber(1000, nameList);
            }
        }
        int quantityListAtFirst = qa4AutoBoard.getQuantityLists();
        qa4AutoBoard.createNewList(nameList);
        int quantityListAtTheEnd = qa4AutoBoard.getQuantityLists();

        Assert.assertEquals(quantityListAtFirst+1, quantityListAtTheEnd);
        Assert.assertEquals(qa4AutoBoard.getAddButtonName(), "Add another list");
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "createListRandomName")
    public void createNewListRandomName(String nameList) {

        boardsPage.openBoard("QA4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();
//        String nameList = "New List";
        int quantityListAtFirst = qa4AutoBoard.getQuantityLists();
        qa4AutoBoard.createNewList(nameList);
        int quantityListAtTheEnd = qa4AutoBoard.getQuantityLists();

        Assert.assertEquals(quantityListAtFirst+1, quantityListAtTheEnd);
        Assert.assertEquals(qa4AutoBoard.getAddButtonName(), "Add another list");
    }

    @Test
    public void addFirstCardInNewList() {

        boardsPage.openBoard("QA4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();
        int quantityAddAnotherButtonBeg = qa4AutoBoard.getQuantityAddAnotherCardButtons();
        qa4AutoBoard.createNewList("New List");
        qa4AutoBoard.clickLastAddCardButton();
        qa4AutoBoard.addFirstCard();
        int quantityAddAnotherButtonEnd = qa4AutoBoard.getQuantityAddAnotherCardButtons();

        Assert.assertEquals(quantityAddAnotherButtonBeg+1, quantityAddAnotherButtonEnd);
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "createListRandomName")
    public void addFirstCardInNewListRandomName(String nameList) {

        boardsPage.openBoard("QA4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();
        int quantityAddAnotherButtonBeg = qa4AutoBoard.getQuantityAddAnotherCardButtons();
        qa4AutoBoard.createNewList(nameList);
        qa4AutoBoard.clickLastAddCardButton();
        qa4AutoBoard.addFirstCard();
        int quantityAddAnotherButtonEnd = qa4AutoBoard.getQuantityAddAnotherCardButtons();

        Assert.assertEquals(quantityAddAnotherButtonBeg+1, quantityAddAnotherButtonEnd);
    }

    @Test
    public void deleteList() {

        boardsPage.openBoard("QA4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();
        int quantityListAtFirst = qa4AutoBoard.getQuantityLists();
        if (quantityListAtFirst == 0) {
            String nameList = qa4AutoBoard.genRandomString(6);
            qa4AutoBoard.createNewList(nameList);
            quantityListAtFirst++;
        }
        qa4AutoBoard.openMenuListActions();
        qa4AutoBoard.deleteList();
        int quantityListAtTheEnd = qa4AutoBoard.getQuantityLists();

        Assert.assertEquals(quantityListAtFirst - 1, quantityListAtTheEnd);
    }

    @Test
    public void createCopyOfList() {

        boardsPage.openBoard("QA4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();
        int quantityListAtFirst = qa4AutoBoard.getQuantityLists();
        if (quantityListAtFirst == 0) {
            String nameList = qa4AutoBoard.genRandomString(6);
            qa4AutoBoard.createNewList(nameList);
            quantityListAtFirst++;
        }
        String nameOfList1 = qa4AutoBoard.getNameOfList1();
        int countAtFirst = qa4AutoBoard.getQuantityListsWithNameOfList1(nameOfList1);
        qa4AutoBoard.createCopyOfTheList();
        int quantityListAtTheEnd = qa4AutoBoard.getQuantityLists();
        int countAtTheEnd = qa4AutoBoard.getQuantityListsWithNameOfList1(nameOfList1);

        Assert.assertEquals(quantityListAtFirst+1, quantityListAtTheEnd);
        Assert.assertEquals(countAtFirst+1, countAtTheEnd, "copying the first list is not successful");
    }

}
