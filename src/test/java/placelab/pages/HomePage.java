package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;
    private String userLogElement = "//div[@id = 'user-name' ]";
    private String dropdownMenuElement = "//a[@id='actions-nav-item']";
    private String signOutElement = "//a[text()='Sign out']";
    private String createReportElement = "//a[@class='dropdown-toggle create-dropdown']";


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getUserLog() {
        return driver.findElement(By.xpath(userLogElement));
    }

    public WebElement getGroupAdminMenu() {
        return driver.findElement(By.xpath(dropdownMenuElement));
    }

    public void signOut() {
        getGroupAdminMenu().click();
        WebElement signOut = driver.findElement(By.xpath(signOutElement));
        signOut.click();
    }

    public void createReport() {
        WebElement createReport = driver.findElement(By.xpath(createReportElement));
        createReport.click();
    }


}
