package org.selenium;

import org.openqa.selenium.By;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        /*System.setProperty("webdriver.chrome.driver","C:\\Users\\USER\\Documents\\automation\\software\\chromedriver.exe");
        driver.get("https://askomdch.com");
        BillingAddress billingAddress = new BillingAddress().
        setFirstname("demo").
        setLastname("user").
        setAddressLineOne("San Fransisco").
        setCity("San Fransisco").
        setPostalCode("94188").
        setEmail("askomdch@gmail.com");
        BillingAddress billingAddress = new BillingAddress("demo","user","San Fransisco",
                "San Fransisco", "94188","askomdch@gmail.com"); */

        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search(searchFor);

                                            /*        StorePage storePage = homePage.navigateToStoreUsingMenu();
                                                      storePage.search("Blue");
                                                    //Jika search tidak digabung dalam satu method
                                                    storePage.
                                                            enterTextInSearchField("Blue").
                                                            clickSearchBtn(); */

        Assert.assertEquals(storePage.getTitle(),"Search results: “"+ searchFor +"”");
        storePage.clickAddToCartBtn(product.getName());
//        Thread.sleep(3000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        CheckoutPage checkoutPage =  cartPage.
                checkout().
                                            //enterFirstName("demo").enterLastName("user").enterAddress("San Fransisco").enterCity("San Fransisco").enterPostCode("94188").enterEmail("askomdch@gmail.com").
                setBillingAddress(billingAddress).
                placeOrder();
//        Thread.sleep(3000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");

    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User("demo","demopwd");

        StorePage storePage = new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search(searchFor);
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+ searchFor +"”");

        storePage.clickAddToCartBtn(product.getName());
//        Thread.sleep(3000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        CheckoutPage checkoutPage =  cartPage.checkout();
        //Differentiator
        checkoutPage.clickHereToLoginLink();
//        Thread.sleep(3000);
        checkoutPage.
                login(user).
                setBillingAddress(billingAddress).
                placeOrder();
//        Thread.sleep(3000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");

    }
}
