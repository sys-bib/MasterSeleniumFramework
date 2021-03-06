package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductThumbnail;

public class StorePage extends BasePage {
    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchButton = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    private ProductThumbnail productThumbnail;


    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public StorePage enterTextInSearchField(String txt){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(txt);
        //driver.findElement(searchField).sendKeys(txt);
        return this;
    }

    public StorePage load(){
        load("/store");
        return this;
    }

    public StorePage search(String txt){
        enterTextInSearchField(txt).clickSearchBtn();
        //driver.findElement(searchField).sendKeys(txt);
        //driver.findElement(searchButton).click();
        return this;
    }

    public StorePage clickSearchBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        //driver.findElement(searchButton).click();
        return this;
    }

    public String getTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
        //return driver.findElement(title).getText();
    }

}
