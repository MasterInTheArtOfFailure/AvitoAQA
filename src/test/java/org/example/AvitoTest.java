package org.example;

import junit.framework.TestCase;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;


public class AvitoTest extends TestCase {
    private WebDriver driver;

    @BeforeSuite
    public void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void openLandingPage() {
        driver.get("https://www.avito.ru/nikel/knigi_i_zhurnaly/domain-driven_design_distilled_vaughn_vernon_2639542363");
        //driver.manage().window().maximize();
    }

    @Test
    @Ignore
    public void addToFavoritesFromProductCard() {
        WebDriverWait initialWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement addToFavoritesButton = driver.findElement(By.xpath("//span[contains(text(),'Добавить в избранное')]"));//"//span[contains(text(),'Добавить в избранное')]" для текста
        addToFavoritesButton.click();

        WebDriverWait waitForAddedToFavoritesButton = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForAddedToFavoritesButton.until(ExpectedConditions.textToBePresentInElement
                (addToFavoritesButton, "В избранном"));

        WebElement favoritesHeartIcon = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[3]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/button[1]/*[1]"));

        Assert.assertEquals(favoritesHeartIcon.getAttribute("data-icon"), "favorites-filled");
        Assert.assertEquals(addToFavoritesButton.getText(), "В избранном");


        //сообщение о добавлении в список избранных
        WebDriverWait waitForDisplayConfirmationPopup = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForDisplayConfirmationPopup.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[6]/div/div/div/div[2]/div/p")));//исправить
        WebElement confirmationPopup = driver.findElement(By.xpath("/html/body/div[2]/div[6]/div/div/div/div[2]/div/p"));

        WebDriverWait waitForDissappearConfirmationPopup = new WebDriverWait(driver, Duration.ofSeconds(8));
        waitForDissappearConfirmationPopup.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[6]/div/div/div/div[2]/div/p"))));
        Assert.assertFalse(confirmationPopup.isDisplayed());
    }

    @Test
    public void checkChangeOfColorHovering() {
        WebElement colorOfButton = driver.findElement(By.xpath("//button[@title = 'Добавить в избранное']"));
        String propertyName = "background-color";
        String colorBeforeHover = colorOfButton.getCssValue(propertyName);
        Actions actions = new Actions(driver);
        actions.moveToElement(colorOfButton).perform();
        String colorAfterHover = colorOfButton.getCssValue(propertyName);
        Assert.assertFalse(colorBeforeHover.equals(colorAfterHover));
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

