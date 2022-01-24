package org.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\USER\\Documents\\automation\\software\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://askomdch.com");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("li[id='menu-item-1227'] a[class='menu-link']")).click();
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement(By.cssSelector("button[value='Search']")).click();
        Assert.assertEquals(
                driver.findElement(By.cssSelector((".woocommerce-products-header__title.page-title"))).getText(),
                "Search results: “Blue”"
        );
        driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();
        Thread.sleep(3000 );
        driver.findElement(By.cssSelector("a[title='View cart']")).click();
        Assert.assertEquals(
                driver.findElement(By.cssSelector("td[class='product-name'] a")).getText(),
                "Blue Shoes"
        );
        driver.findElement(By.cssSelector(".checkout-button.button.alt.wc-forward")).click();
        driver.findElement(By.id("billing_first_name")).sendKeys("demo");
        driver.findElement(By.id("billing_last_name")).sendKeys("user");
        driver.findElement(By.id("billing_address_1")).sendKeys("San Fransisco");
        driver.findElement(By.id("billing_city")).sendKeys("San Fransisco");
        driver.findElement(By.id("billing_postcode")).sendKeys("94188");
        driver.findElement(By.id("billing_email")).sendKeys("askomdch@gmail.com");
        driver.findElement(By.id("place_order")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(
                By.cssSelector(".woocommerce-notice.woocommerce-notice--success.woocommerce-thankyou-order-received")).getText(),
                "Thank you. Your order has been received."
        );
        driver.close();
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\USER\\Documents\\automation\\software\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://askomdch.com");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("li[id='menu-item-1227'] a[class='menu-link']")).click();
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement(By.cssSelector("button[value='Search']")).click();
        Assert.assertEquals(
                driver.findElement(By.cssSelector((".woocommerce-products-header__title.page-title"))).getText(),
                "Search results: “Blue”"
        );
        driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();
        Thread.sleep(3000 );
        driver.findElement(By.cssSelector("a[title='View cart']")).click();
        Assert.assertEquals(
                driver.findElement(By.cssSelector("td[class='product-name'] a")).getText(),
                "Blue Shoes"
        );
        driver.findElement(By.cssSelector(".checkout-button.button.alt.wc-forward")).click();
        //differentiator
        driver.findElement(By.className("showlogin")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("username")).sendKeys("demo");
        driver.findElement(By.id("password")).sendKeys("demopwd");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.id("billing_first_name")).sendKeys("demo");
        driver.findElement(By.id("billing_last_name")).sendKeys("user");
        driver.findElement(By.id("billing_address_1")).sendKeys("San Fransisco");
        driver.findElement(By.id("billing_city")).sendKeys("San Fransisco");
        driver.findElement(By.id("billing_postcode")).clear();
        driver.findElement(By.id("billing_postcode")).sendKeys("94188");
        driver.findElement(By.id("billing_email")).clear();
        driver.findElement(By.id("billing_email")).sendKeys("askomdch@gmail.com");
        driver.findElement(By.id("place_order")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(
                        By.cssSelector(".woocommerce-notice.woocommerce-notice--success.woocommerce-thankyou-order-received")).getText(),
                "Thank you. Your order has been received."
        );
        driver.close();
    }
}
