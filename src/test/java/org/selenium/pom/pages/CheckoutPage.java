package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;

public class CheckoutPage extends BasePage {
    private final By firstNameFld   = By.id("billing_first_name");
    private final By lastNameFld    = By.id("billing_last_name");
    private final By addresFld      = By.id("billing_address_1");
    private final By cityFld        = By.id("billing_city");
    private final By postcodeFld    = By.id("billing_postcode");
    private final By emailFld       = By.id("billing_email");
    private final By placeOrderBtn  = By.id("place_order");
    private final By successNotice  = By.cssSelector(".woocommerce-notice.woocommerce-notice--success.woocommerce-thankyou-order-received");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName){
        driver.findElement(firstNameFld).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String LastName){
        driver.findElement(lastNameFld).sendKeys(LastName);
        return this;
    }

    public CheckoutPage enterAddress(String address){
        driver.findElement(addresFld).sendKeys(address);
        return this;
    }

    public CheckoutPage enterCity(String city){
        driver.findElement(cityFld).sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode){
        driver.findElement(postcodeFld).sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email){
        driver.findElement(emailFld).sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        return  enterFirstName(billingAddress.getFirstname()).
                enterLastName(billingAddress.getLastname()).
                enterAddress(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                enterPostCode(billingAddress.getPostalCode()).
                enterEmail(billingAddress.getEmail());
    }

    public CheckoutPage placeOrder(){
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice(){
        return driver.findElement(successNotice).getText();
    }

}
