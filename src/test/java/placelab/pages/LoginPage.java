package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {

    private WebDriver driver;
    private String submitElement = "//input[@name='commit']";
    private String passwordElement = "//input[@id='password']";
    private String emailElement = "//input[@id='email']";
    private String logoElement = "//*[@id='login']/img";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyLoginPage(String host) {
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
        getPlaceLabLogo().isDisplayed();
    }

    public WebElement getPlaceLabLogo() {
        WebElement logo = driver.findElement(By.xpath(logoElement));
        return logo;
    }

    public void enterPassword(final String password) {
        WebElement passwordInput = driver.findElement(By.xpath(passwordElement));
        passwordInput.sendKeys(System.getProperty("password"));
    }

    public void enterUsername(final String username) {
        WebElement emailInput = driver.findElement(By.xpath(emailElement));
        emailInput.sendKeys(System.getProperty("username"));
    }

    public void submit() {
        WebElement submit = driver.findElement(By.xpath(submitElement));
        submit.click();
    }
}
