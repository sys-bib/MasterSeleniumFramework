package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {

    @Test //Using TestNG
    //@Test *Using JUnit4
    //@org.junit.jupiter.api.Test *Using JUnit5
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu().
                search(searchFor);

                                            /*        StorePage storePage = homePage.navigateToStoreUsingMenu();
                                                      storePage.search("Blue");
                                                    //Jika search tidak digabung dalam satu method
                                                    storePage.
                                                            enterTextInSearchField("Blue").
                                                            clickSearchBtn(); */

        Assert.assertEquals(storePage.getTitle(),"Search results: “"+ searchFor +"”"); //TestNG
        //Assertions.assertEquals("Search results: “"+ searchFor +"”", storePage.getTitle()); //JUnit

        storePage.clickAddToCartBtn(product.getName());
//        Thread.sleep(3000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName()); //TestNG
        //Assertions.assertEquals(product.getName(), cartPage.getProductName()); //JUnit
        CheckoutPage checkoutPage =  cartPage.
                checkout().
                selectDirectBankTransfer().
                setBillingAddress(billingAddress).
                placeOrder();
//        Thread.sleep(3000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");//TestNG
        //Assertions.assertEquals("Thank you. Your order has been received.", checkoutPage.getNotice()); //JUnit

    }

    @Test //Using TestNG
    //@Test *Using JUnit4
    //@Test *Using JUnit5
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(),
                            ConfigLoader.getInstance().getPassword());

        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu().
                search(searchFor);

        Assert.assertEquals(storePage.getTitle(),"Search results: “"+ searchFor +"”");
        //Assertions.assertEquals("Search results: “"+ searchFor +"”", storePage.getTitle()); //JUnit

        storePage.clickAddToCartBtn(product.getName());
//        Thread.sleep(3000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        //Assertions.assertEquals(product.getName(), cartPage.getProductName()); //JUnit

        CheckoutPage checkoutPage =  cartPage.checkout();
        //Differentiator
        checkoutPage.clickHereToLoginLink();
//        Thread.sleep(3000);
        checkoutPage.
                login(user).
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
//        Thread.sleep(3000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
        //Assertions.assertEquals("Thank you. Your order has been received.", checkoutPage.getNotice()); //JUnit

    }
}
