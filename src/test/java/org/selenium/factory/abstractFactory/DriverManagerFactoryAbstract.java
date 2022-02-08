package org.selenium.factory.abstractFactory;

import org.selenium.factory.ChromeDriverManager;
import org.selenium.factory.DriverManager;
import org.selenium.factory.FirefoxDriverManager;
import org.selenium.pom.constants.DriverType;

public class DriverManagerFactoryAbstract {

    public static DriverManagerAbstract getManager(DriverType driverType){
            switch (driverType){
                case Chrome -> {
                    return new ChromeDriverManagerAbstract();
                }
                case Firefox -> {
                    return new FirefoxDriverManagerAbstract();
                }
                default -> throw new IllegalStateException("Invalid Driver: " +driverType);

        }
    }
}
