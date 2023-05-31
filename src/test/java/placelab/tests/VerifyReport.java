package placelab.tests;

import org.apache.hc.client5.http.impl.async.H2AsyncClientBuilder;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import placelab.pages.HomePage;
import placelab.pages.LoginPage;
import placelab.pages.SingleCategoryMapping;
import placelab.utilities.WebDriverSetup;

public class VerifyReport {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String homePageUrl = System.getProperty("homepage");
    private String GroupAdmin = System.getProperty("groupadmin");
    private String userLogName = "Elma Merušić";
    private String SingleCategoryMappingUrl = "https://demo.placelab.com/content_classification/category_standardizer_reports/sampler?";
    private LoginPage loginPage;
    private HomePage homePage;
    private SingleCategoryMapping singleCategoryMapping;


    @Parameters({"browser"})

    @BeforeSuite(alwaysRun = true)
    public void openApp(String browser) {

        driver = WebDriverSetup.getWebDriver(browser);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        singleCategoryMapping = new SingleCategoryMapping(driver);

        driver.navigate().to(host);
        loginPage.verifyLoginPage(host);
    }

    @BeforeTest(alwaysRun = true, groups = {"Positive"}, description = "User login to the PlaceLab homepage, and validation that the user is successfully logged in to the homepage.")
    public void loginToPlaceLab() {

        loginPage.enterUsername("username");
        loginPage.enterPassword("password");
        loginPage.submit();

        Assert.assertEquals(driver.getCurrentUrl(), homePageUrl);

        String nameText = homePage.getUserLog().getText();
        Assert.assertEquals(nameText.trim(), userLogName);


    }

    @Test(priority = 1, groups = {"Positive"}, description = "Verify that Single Category Mapping is working properly(Apple)")
    public void SingleCategoryMappingApple() {

        homePage.createReport();
        singleCategoryMapping.openSingleCategoryMapping();

        String nameText = singleCategoryMapping.getTitle().getText();
        Assert.assertEquals(nameText.trim(), "Single Category Mapping");
        Assert.assertEquals(driver.getCurrentUrl(), SingleCategoryMappingUrl);

        singleCategoryMapping.inputCategory();
        singleCategoryMapping.OutputCategoryApple();
        singleCategoryMapping.clickMap();

        String nameOutputText = singleCategoryMapping.outputResultTitle().getText();
        Assert.assertEquals(nameOutputText.trim(), "1education.school");

    }

    @Test(priority = 2, groups = {"Positive"}, description = "Verify that Single Category Mapping is working properly(PlaceLab)")
    public void SingleCategoryMappingPlaceLab() {

        homePage.createReport();
        singleCategoryMapping.openSingleCategoryMapping();

        String nameText = singleCategoryMapping.getTitle().getText();
        Assert.assertEquals(nameText.trim(), "Single Category Mapping");
        Assert.assertEquals(driver.getCurrentUrl(), SingleCategoryMappingUrl);

        singleCategoryMapping.inputCategory();
        singleCategoryMapping.OutputCategoryPlaceLab();
        singleCategoryMapping.clickMap();

        String nameOutput2Text = singleCategoryMapping.outputResultTitle().getText();
        Assert.assertEquals(nameOutput2Text.trim(), "1Education > School");

    }


    @AfterTest(alwaysRun = true, groups = {"Positive"}, description = "User logout and verification that the user has successfully logged out.")
    public void singOut() {

        homePage.signOut();
        Assert.assertEquals(driver.getCurrentUrl(), host, "User not logged out!");
    }


    @AfterSuite(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}