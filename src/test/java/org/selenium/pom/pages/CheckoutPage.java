package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

import java.time.Duration;

public class CheckoutPage extends BasePage {
    private final By showLogin      = By.className("showlogin");
    private final By usernameFld    = By.id("username");
    private final By passwordFld    = By.id("password");
    private final By loginBtn       = By.name("login");

    private final By firstNameFld   = By.id("billing_first_name");
    private final By lastNameFld    = By.id("billing_last_name");
    private final By addresFld      = By.id("billing_address_1");
    private final By cityFld        = By.id("billing_city");
    private final By postcodeFld    = By.id("billing_postcode");
    private final By emailFld       = By.id("billing_email");
    private final By placeOrderBtn  = By.id("place_order");
    private final By successNotice  = By.cssSelector(".woocommerce-notice.woocommerce-notice--success.woocommerce-thankyou-order-received");
    private final By overlay =  By.cssSelector(".blockUI.blockOverlay"); //digunakan jika terjadi element click interception exception

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFld));
        e.clear();
        e.sendKeys(firstName);
        //driver.findElement(firstNameFld).clear();
        //driver.findElement(firstNameFld).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String LastName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFld));
        e.clear();
        e.sendKeys(LastName);
        return this;
    }

    public CheckoutPage enterAddress(String address){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addresFld));
        e.clear();
        e.sendKeys(address);
        return this;
    }

    public CheckoutPage enterCity(String city){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(cityFld));
        e.clear();
        e.sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(postcodeFld));
        e.clear();
        e.sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(emailFld));
        e.clear();
        e.sendKeys(email);
        return this;
    }

    public CheckoutPage enterUsername(String username){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFld));
        e.clear();
        e.sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld));
        e.clear();
        e.sendKeys(password);
        return this;
    }

    public CheckoutPage clickHereToLoginLink(){
        wait.until(ExpectedConditions.elementToBeClickable(showLogin)).click();
        //driver.findElement(showLogin).click();
        return this;
    }

    public CheckoutPage clickLoginBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        //driver.findElement(loginBtn).click();
        return this;
    }

    public CheckoutPage login(User user){
        return enterUsername(user.getUsername()).
               enterPassword(user.getPassword()).
               clickLoginBtn();
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        return  enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                enterAddress(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                enterPostCode(billingAddress.getPostalCode()).
                enterEmail(billingAddress.getEmail());
    }

    public CheckoutPage placeOrder(){
        waitForOverlaysToDisappear(overlay); //Jika terjadi element click interception exception
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
        //return driver.findElement(successNotice).getText();
    }

}
