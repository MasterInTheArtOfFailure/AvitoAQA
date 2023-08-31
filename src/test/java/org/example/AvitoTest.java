package org.example;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ItemPage;

import java.time.Duration;

@FixMethodOrder(MethodSorters.JVM)
public class AvitoTest {
    private WebDriver driver;
    private ItemPage page;

    @BeforeSuite
    public void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        page = new ItemPage(driver);
    }

    @BeforeMethod
    public void setUpLandingPage() {
        driver.get(page.getUrl());
        driver.manage().window().maximize();
        new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @AfterMethod
    public void quitChrome() {
        driver.close();
        driver.quit();
    }

    @Test
    public void checkChangeOfColorHovering() {
        String colorBeforeHover = page.getBackgroundColor();
        page.hoverOverFavoritesButton();
        String colorAfterHover = page.getBackgroundColor();
        Assert.assertNotEquals(colorAfterHover, colorBeforeHover);
    }

    @Test
    public void addToFavoritesFromProductCard() {
        WebElement clickedButton = page.clickOnFavoritesButton();
        Assert.assertEquals(clickedButton.getText(), "В избранном");
    }

    @Test
    public void checkHeartIconChange() {
        page.clickOnFavoritesButton();

        WebElement icon = driver.findElement(page.getFavoritesHeartIcon());

        Assert.assertEquals(icon.getAttribute("data-icon"), "favorites-filled");
    }

    @Test
    public void checkInfoPopup() {
        page.clickOnFavoritesButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement popup = wait.until(ExpectedConditions.presenceOfElementLocated(page.getInfoPopup()));
        Assert.assertTrue(popup.isDisplayed());
        Assert.assertEquals(popup.getText(), "Добавлено в избранное");
    }

    @Test
    public void checkLinkInsidePopup() {
        page.clickOnFavoritesButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(page.getInfoPopup()));
        WebElement link = driver.findElement(page.getLinkInsidePopup());
        Assert.assertEquals(link.getAttribute("href"), "https://www.avito.ru/favorites/knigi_i_zhurnaly");
    }
}

    @Test
    public void checkHeartIconChange() {
        page.clickOnFavoritesButton();

        WebElement icon = driver.findElement(page.favoritesHeartIcon);

        Assert.assertEquals(icon.getAttribute("data-icon"), "favorites-filled");
    }

    @Test
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
}

