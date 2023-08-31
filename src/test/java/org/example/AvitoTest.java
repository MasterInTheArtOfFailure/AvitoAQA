package org.example;

import junit.framework.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ItemPage;

import java.time.Duration;


public class AvitoTest extends TestCase {
    private WebDriver driver;
    private ItemPage page;

    @BeforeSuite
    public void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        page = new ItemPage(driver);
    }

    @BeforeMethod
    public void openLandingPage() {
        driver.get(page.getUrl());
        //driver.manage().window().maximize();
        new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void addToFavoritesFromProductCard() {
        WebElement clickedButton = page.clickOnFavoritesButton();
        Assert.assertEquals(clickedButton.getText(), "В избранном");
    }

    @Test
    @Ignore
    public void checkChangeOfColorHovering() {
        WebElement colorOfButton = driver.findElement(By.xpath("//button[@title = 'Добавить в избранное']"));
        String propertyName = "background-color";
        String colorBeforeHover = colorOfButton.getCssValue(propertyName);
        Actions actions = new Actions(driver);
        actions.moveToElement(colorOfButton).perform();
        String colorAfterHover = colorOfButton.getCssValue(propertyName);
        Assert.assertNotEquals(colorAfterHover, colorBeforeHover);
    }

    @Test
    @Ignore
    public void checkHeartIconChange() {
        page.clickOnFavoritesButton();

        WebElement icon = driver.findElement(page.favoritesHeartIcon);

        Assert.assertEquals(icon.getAttribute("data-icon"), "favorites-filled");
    }

    @Test
    @Ignore
    public void infoPopup() {
        page.clickOnFavoritesButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement popup = wait.until(ExpectedConditions.presenceOfElementLocated(page.infoPopup));
        Assert.assertTrue(popup.isDisplayed());
        Assert.assertEquals(popup.getText(), "Добавлено в избранное");

        wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(page.infoPopup)));
        Assert.assertFalse(popup.isDisplayed());
    }

    @AfterTest
    public void quitChrome() {
        driver.quit();
    }

    @Test
    @Ignore
    public void addToFavouritesFromSearchBar() {
        WebElement searchBar ;
    }

}

