package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CurrentBoardPageHelper extends PageBase {
    String name;
    @FindBy (css = ".placeholder")
    WebElement addButton;

    @FindBy (xpath = "//span[@dir='auto']")
    WebElement fieldNameList;

    @FindBy (xpath = "//h2")
    List<WebElement> listHeaders;

    @FindBy (css = ".list-name-input")
    WebElement listInputNameField;

    @FindBy (xpath = "//a[@class='js-close-list']")
    WebElement archiveButton;

    @FindBy (xpath = "//a[@class='list-header-extras-menu dark-hover js-open-list-menu icon-sm icon-overflow-menu-horizontal']")
    List<WebElement> menuListButtons;

    @FindBy (xpath = "//a[@class='js-copy-list']")
    WebElement copyListButton;

    @FindBy (css = ".js-submit")
    WebElement createCopyListButton;

    @FindBy (xpath = "//span[@class = 'js-add-a-card']")
    List<WebElement> addCardButtons;

    @FindBy (xpath = "//h2/../textarea")
    List<WebElement> nameOfLists;

    @FindBy (xpath = "//input[@type='submit']")
    WebElement addListButton;

    @FindBy (css = "a.js-cancel-edit")
    WebElement cancelButton;

    @FindBy (xpath = "//input[@class='primary confirm mod-compact js-add-card']")
    WebElement addCard;

    @FindBy (xpath = "//textarea[@placeholder='Enter a title for this card…']")
    WebElement nameCardField;

    @FindBy (css = "a.js-cancel")
    WebElement cancelAddCardButton;

    @FindBy (xpath = "//span[@class= 'js-add-another-card']")
    List<WebElement> addAnotherCardButtons;

    @FindBy(xpath = "//button[@data-test-id = 'header-member-menu-button']")
    WebElement initialsUpperRightIcon;

    @FindBy(xpath = "//span[contains(text(),'Profile and Visibility')]")
    WebElement profileAndVisibilityMenu;

    public CurrentBoardPageHelper(WebDriver driver) {
        super(driver);
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(addButton, 30);
    }

    public boolean titleVerification() {
        return fieldNameList.getText().equals(name);
    }

    public void createNewList(String name) {
        addButton.click();
        waitUntilElementIsClickable(listInputNameField, 30);
        listInputNameField.sendKeys(name);
        addListButton.click();
        waitUntilElementIsClickable(cancelButton, 10);
        cancelButton.click();
    }

    public String getAddButtonName() {
        return addButton.getText();
    }

    public boolean existList(String nameList) {
        boolean exitName = false;
        for (WebElement element : nameOfLists) {
            if (element.getText().equals(nameList)) {
                exitName = true;
            }
        }
        return exitName;
    }

    public int getQuantityLists() {
        return listHeaders.size();
    }

    public void openMenuListActions() {
        menuListButtons.get(0).click();
    }

    public void deleteList() {
        waitUntilElementIsClickable(archiveButton, 30);
        archiveButton.click();
        waitUntilElementIsClickable(addButton, 30);
    }

    public int getQuantityListsWithNameOfList1(String name) {
        int countAtFirst = 0;
        for(WebElement element: nameOfLists) {
            if(element.getText().equals(name)) {
                countAtFirst++;
            }
        }
        return countAtFirst;
    }

    public String getNameOfList1() {
        return nameOfLists.get(0).getText();
    }

    public void createCopyOfTheList() {
        waitUntilElementIsClickable(menuListButtons.get(0), 30);
        menuListButtons.get(0).click();
        waitUntilElementIsClickable(copyListButton, 20);
        copyListButton.click();
        waitUntilElementIsClickable(createCopyListButton, 30);
        createCopyListButton.click();
        waitUntilElementIsClickable(addButton, 30);
    }

    public void clickLastAddCardButton() {
        waitUntilAllElementsAreVisible(addCardButtons,15);
        int sizeListAddCardButtons = addCardButtons.size();
        addCardButtons.get(sizeListAddCardButtons-1).click();
    }

    public void addFirstCard() {
        waitUntilElementIsClickable(addCard, 10);
        nameCardField.sendKeys("text");
        addCard.click();
        waitUntilElementIsClickable(cancelAddCardButton, 10);
        cancelAddCardButton.click();
    }

    public int getQuantityAddAnotherCardButtons() {
        waitUntilAllElementsAreVisible(addAnotherCardButtons, 10);
        return addAnotherCardButtons.size();
    }

    public void openUserProfile() {
        initialsUpperRightIcon.click();
        waitUntilElementIsClickable(profileAndVisibilityMenu,10);
        profileAndVisibilityMenu.click();
    }
}
