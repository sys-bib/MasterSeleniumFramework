package org.selenium.factory;

import org.selenium.pom.constants.DriverType;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType driverType){
            switch (driverType){
                case Chrome -> {
                    return new ChromeDriverManager();
                }
                case Firefox -> {
                    return new FirefoxDriverManager();
                }
                default -> throw new IllegalStateException("Invalid Driver: " +driverType);

        }
    }
}
