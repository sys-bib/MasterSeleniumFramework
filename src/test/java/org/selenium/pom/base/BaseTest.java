package org.selenium.pom.base;


import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.selenium.factory.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    //protected WebDriver driver;


    private void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    protected WebDriver getDriver(){
        return this.driver.get();
    }

    //This using TestNG
    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser){
        //browser = System.getProperty("browser", browser);
        if(browser == null) browser = "Chrome";
        setDriver(new DriverManager().initializeDriver(browser));
        System.out.println("CURRENT THREAD: "+Thread.currentThread().getId() + ", " + "Driver = " + getDriver());
    }

    @AfterMethod
    public void quitDriver()throws InterruptedException{
        Thread.sleep(100);
        System.out.println("CURRENT THREAD: "+Thread.currentThread().getId() + ", " + "Driver = " + getDriver());
        getDriver().quit();
    }

    /*
    //This Using JUnit
    //@Before *JUnit4
    //@BeforeEach //*Junit5
    public void startDriver() {
        String browser = System.getProperty("browser");
        driver = new DriverManager().initializeDriver(browser);
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + "Driver = " + driver);
    }

    //@After *JUnit4
    //@AfterEach //JUnit5
    public void quitDriver()throws InterruptedException{
        Thread.sleep(100);
        System.out.println("CURRENT THREAD: "+Thread.currentThread().getId() + ", " + "Driver = " + driver);
        driver.quit();
    }
    */


}
