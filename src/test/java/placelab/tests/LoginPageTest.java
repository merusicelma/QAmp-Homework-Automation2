package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import placelab.utilities.WebDriverSetup;

public class LoginPageTest {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String emailElement = "//input[@id='email']";
    private String passwordElement = "//input[@id='password']";
    private String buttonElement = "//input[@name='commit']";
    private String userLogElement = "//div[@id = 'user-name' ]";
    private String userLogName = "Elma Merušić";
    private String dropdownMenuElement = "//a[@id='actions-nav-item']";
    private String signOutElement = "//a[text()='Sign out']";
    private String incorrectPassword = "incorrectpassword";
    private String invalidCredentialsElement = "//div[@class='error-area']";
    private String incorrectEmail = "incorrectemail";
    private String forgotYourPasswordElement = "//div[@ id = 'password-area']/a";
    private String changeYourPasswordElement = "//p[@class='bold headline']";


    @Parameters({"browser"})

//    @BeforeSuite(alwaysRun = true)
//    public void initDriver(String browser) {
//
//        driver = WebDriverSetup.getWebDriver(browser);
//        driver.navigate().to(host);
//    }

    @BeforeTest(alwaysRun = true, groups = {"Positive, Negative"}, description = "Verify that user is able to open " +
            "PlaceLab App.")
    public void openApp(String browser) {

        driver = WebDriverSetup.getWebDriver(browser);
        driver.navigate().to(host);

        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        WebElement logo = driver.findElement(By.xpath("//div[@id='login']/img"));
        boolean logoPresent = logo.isDisplayed();
        Assert.assertTrue(logoPresent);
    }

    @Test(priority = 5, groups = ("Positive"), description = "This test verifies that user is able to login to PlaceLab, and logout with valid credentials.", suiteName = "Login page test")
    public void LoginLogout() {

        WebElement emailInput = driver.findElement(By.xpath(emailElement));
        emailInput.sendKeys(System.getProperty("username"));

        WebElement passwordInput = driver.findElement(By.xpath(passwordElement));
        passwordInput.sendKeys(System.getProperty("password"));

        WebElement button = driver.findElement(By.xpath(buttonElement));
        button.click();

        WebElement name = driver.findElement(By.xpath(userLogElement));
        String nameText = name.getText();
        Assert.assertEquals(nameText.trim(), userLogName);

        WebElement groupAdminMenu = driver.findElement(By.xpath(dropdownMenuElement));
        groupAdminMenu.click();
        WebElement signOut = driver.findElement(By.xpath(signOutElement));
        signOut.click();
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

    }

    @Test(priority = 1, groups = ("Negative"), description = "This test verifies that user can not login with invalid password.")
    public void LoginInvalidCredentialsInvalidPassword() {

        WebElement emailInput = driver.findElement(By.xpath(emailElement));
        emailInput.sendKeys(System.getProperty("username"));

        WebElement passwordInput = driver.findElement(By.xpath(passwordElement));
        passwordInput.sendKeys(incorrectPassword);

        WebElement buttonInput = driver.findElement(By.xpath(buttonElement));
        buttonInput.click();

    }

    @Test(priority = 2, groups = ("Negative"), description = "This test verifies that user can not login with invalid email.")
    public void LoginInvalidCredentialsInvalidEmail() {

        WebElement emailInput = driver.findElement(By.xpath(emailElement));
        emailInput.sendKeys(incorrectEmail);

        WebElement passwordInput = driver.findElement(By.xpath(passwordElement));
        passwordInput.sendKeys(System.getProperty("password"));

        WebElement buttonInput = driver.findElement(By.xpath(buttonElement));
        buttonInput.click();

    }

    @Test(priority = 3, groups = ("Negative"), description = "This test verifies that user can not login with empty password.")
    public void LoginInvalidCredentialsEmptyPassword() {

        WebElement emailInput = driver.findElement(By.xpath(emailElement));
        emailInput.sendKeys(System.getProperty("username"));

        WebElement buttonInput = driver.findElement(By.xpath(buttonElement));
        buttonInput.click();

    }

    @Test(priority = 4, groups = ("Negative"), description = "This test verifies that user can not login with empty email.")
    public void LoginInvalidCredentialsEmptyEmail() {

        WebElement passwordInput = driver.findElement(By.xpath(passwordElement));
        passwordInput.sendKeys(System.getProperty("password"));

        WebElement buttonInput = driver.findElement(By.xpath(buttonElement));
        buttonInput.click();

    }

    @Test(priority = 6, groups = ("Positive"), description = "This test verifies that user can open page 'Forgot your password'", suiteName = "Login page test")
    public void LoginForgotYourPassword() {

        WebElement forgotYourPasswordLabel = driver.findElement(By.xpath(forgotYourPasswordElement));
        String forgotYourPasswordLabelText = forgotYourPasswordLabel.getText();
        Assert.assertEquals(forgotYourPasswordLabelText, "Forgot your password?");
        forgotYourPasswordLabel.click();

        WebElement changeYourPasswordLabel = driver.findElement(By.xpath(changeYourPasswordElement));
        String changeYourPasswordLabelText = changeYourPasswordLabel.getText();
        Assert.assertEquals(changeYourPasswordLabelText, "Change your password\nLet's find your account");
    }

    @AfterTest(dependsOnGroups = {"Negative"}, description = "Verify that user is not logged in.")
    public void failedLogin() {
        Assert.assertEquals(driver.getCurrentUrl(), host);

        WebElement invalidCredentialsLabel = driver.findElement(By.xpath(invalidCredentialsElement));
        String invalidCredentialsLabelText = invalidCredentialsLabel.getText();
        Assert.assertEquals(invalidCredentialsLabelText, "Invalid credentials!");
    }


    @AfterSuite(alwaysRun = true)
    public void quitDriver() {
        driver.close();
    }
}