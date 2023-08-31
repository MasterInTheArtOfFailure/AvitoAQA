package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ItemPage {
    private final String url = "https://www.avito.ru/nikel/knigi_i_zhurnaly/domain-driven_design_distilled_vaughn_vernon_2639542363";
    private WebDriver driver;

    public By addToFavoritesButton = By.xpath("//span[contains(text(),'Добавить в избранное')]");

    //(//*[name()='svg'][@name='favorites-filled'])[3] for hearts

    public By favoritesHeartIcon = By.xpath("//body/div[@id='app']/div[1]/div[3]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/button[1]/*[1]");
    public By infoPopup = By.xpath("");
    public ItemPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return this.url;
    }

    public WebElement clickOnFavoritesButton() {
        WebElement button = driver.findElement(addToFavoritesButton);
        button.click();

        WebDriverWait waitForAddedToFavoritesButton = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForAddedToFavoritesButton.until(ExpectedConditions.textToBePresentInElement
                (button, "В избранном"));
        return button;
    }

    public void hoverOverFavoritesButton() {
        WebElement button = driver.findElement(addToFavoritesButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(button).build().perform();
    }

    public String getBackgroundColor() {
        WebElement button = driver.findElement(addToFavoritesButton);
        return button.getAttribute("background-color");
    }


}
