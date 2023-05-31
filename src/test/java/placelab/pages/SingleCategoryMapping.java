package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SingleCategoryMapping {

    private WebDriver driver;
    private String singleCategoryMappingElement = "//li[@id='singlecategorymapping']";
    private String singleCategoryMappingTitle = "//div[@class='report-header']";
    private String inputCategoryElement = "//li[@id='singlecategorymapping']";
    private String mapElement = "//button[@disabled='disabled']";
    private String outputCategorySystem = "//button[@title='PlaceLab']";
    private String PlaceLabElement = "//label[@class='radio icheck-label icheck[01eav]']";
    private String AppleElement = "//label[@class='radio icheck-label icheck[1469s]']";
    private String inputCategoryName = "Schoole";
    private String outputTitle = "//div[@id='output_cat']";

    public SingleCategoryMapping(WebDriver driver) {
        this.driver = driver;
    }

    public void openSingleCategoryMapping() {
        WebElement openSingleCategoryMapping = driver.findElement(By.xpath(singleCategoryMappingElement));
        openSingleCategoryMapping.click();
    }

    public WebElement getTitle() {
        return driver.findElement(By.xpath(singleCategoryMappingTitle));
    }

    public void inputCategory() {
        WebElement inputCategory = driver.findElement(By.xpath(inputCategoryElement));
//      inputCategory.click();
        inputCategory.sendKeys(inputCategoryName);
    }

    public void OutputCategoryApple() {
        WebElement OutputCategory = driver.findElement(By.xpath(outputCategorySystem));
        OutputCategory.click();
        WebElement Apple = driver.findElement(By.xpath(AppleElement));
        Apple.click();
    }

    public void OutputCategoryPlaceLab() {
        WebElement OutputCategory = driver.findElement(By.xpath(outputCategorySystem));
        OutputCategory.click();
        WebElement PlaceLab = driver.findElement(By.xpath(PlaceLabElement));
        PlaceLab.click();
    }

    public void clickMap() {
        WebElement map = driver.findElement(By.xpath(mapElement));
        map.click();
    }

    public WebElement outputResultTitle() {
        return driver.findElement(By.xpath(outputTitle));
    }














}
