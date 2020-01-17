package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoardsPageHelper extends PageBase {

    public BoardsPageHelper(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By
                .xpath("//button[@data-test-id='header-boards-menu-button']"),30);
    }

    public boolean verifyIfBoardsIconIsDisplayed() {
        return driver.findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']"))
                .isDisplayed();
    }

    public boolean verifyIfPersonalBoardsHeaderIsDisplayed() {
        return driver.findElement(By.xpath("//h3[@class='boards-page-board-section-header-name']"))
                .getText().equals("Personal Boards");
    }

    public void openBoard(String boardName) {
        waitUntilElementIsVisible(By.xpath("//div[@title='" + boardName +"']/.."), 20);
        driver.findElement(By.xpath("//div[@title='" + boardName +"']/..")).click();
    }
}
