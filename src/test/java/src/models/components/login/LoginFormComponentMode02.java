package src.models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import src.models.components.global.BottomNavCommponent;

public class LoginFormComponentMode02 {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By emailSel = MobileBy.AccessibilityId("input-email");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginFormComponentMode02(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void inputEmail(String email) {
        appiumDriver.findElement(emailSel).sendKeys(email);
    }

    public void inputPassword(String password) {
        appiumDriver.findElement(passwordSel).sendKeys(password);
    }

    public void clickOnLoginBtn() {
        appiumDriver.findElement(loginBtnSel).click();
    }

    public BottomNavCommponent bottomNavCommponent() {
        return new BottomNavCommponent(appiumDriver);
    }
}
