package org.selenium;

import org.openqa.selenium.By;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\USER\\Documents\\automation\\software\\chromedriver.exe");
        //driver.get("https://askomdch.com");

        BillingAddress billingAddress = new BillingAddress().
        setFirstname("demo").
        setLastname("user").
        setAddressLineOne("San Fransisco").
        setCity("San Fransisco").
        setPostalCode("94188").
        setEmail("askomdch@gmail.com");
        StorePage storePage = new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search("Blue");

                                            //        StorePage storePage = homePage.navigateToStoreUsingMenu();
                                            //        storePage.search("Blue");
                                                    //Jika search tidak digabung dalam satu method
                                            //        storePage.
                                            //                enterTextInSearchField("Blue").
                                            //                clickSearchBtn();

        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");
        storePage.clickAddToCartBtn("Blue Shoes");
        Thread.sleep(3000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),"Blue Shoes");
        CheckoutPage checkoutPage =  cartPage.
                checkout().
                                            //enterFirstName("demo").enterLastName("user").enterAddress("San Fransisco").enterCity("San Fransisco").enterPostCode("94188").enterEmail("askomdch@gmail.com").
                setBillingAddress(billingAddress).
                placeOrder();
        Thread.sleep(3000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");

    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        StorePage storePage = new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");
        storePage.clickAddToCartBtn("Blue Shoes");
        Thread.sleep(3000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),"Blue Shoes");
        CheckoutPage checkoutPage =  cartPage.checkout();
        //Differentiator
        driver.findElement(By.className("showlogin")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("username")).sendKeys("demo");
        driver.findElement(By.id("password")).sendKeys("demopwd");
        driver.findElement(By.name("login")).click();
        checkoutPage.
                enterFirstName("demo").
                enterLastName("user").
                enterAddress("San Fransisco").
                enterCity("San Fransisco").
                enterPostCode("94188").
                enterEmail("askomdch@gmail.com").
                placeOrder();
        Thread.sleep(3000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");

    }
}
