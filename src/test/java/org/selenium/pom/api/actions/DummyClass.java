package org.selenium.pom.api.actions;

import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

public class DummyClass {

    public static void main(String[] args){
        String username = "demouser" + new FakerUtils().generateRandomNumber();

        User user = new User().
                setUsername(username).
                setPassword("demowpd").
                setEmail(username + "@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();
        System.out.println(signUpApi.register(user));
        System.out.println(signUpApi.getCookies());
    }
}
