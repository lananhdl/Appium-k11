package src;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class AppimLaunchTest {
    public static void main(String[] args) throws InterruptedException {
        AppiumDriver<MobileElement> driver = null;
        //Desired caps
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName","android");
        desiredCapabilities.setCapability("automationName","uiautomator2");
        desiredCapabilities.setCapability("udid","emulator-5554");
        desiredCapabilities.setCapability("appPackage","com.wdiodemoapp");
        desiredCapabilities.setCapability("appActivity","com.wdiodemoapp.MainActivity");
    // Specify Appium Server URL
        URL appiumServer = null;
    try {
        appiumServer = new URL("http://localhost:4723/wd/hub");
    }
    catch (Exception e){
        e.printStackTrace();
    }
    if(appiumServer== null){
        throw new RuntimeException("[Error] somehow, we couln't contrucst Appium Server URL");
    }
    driver = new AndroidDriver<>(appiumServer, desiredCapabilities);
    //Debug Purpose Only
        Thread.sleep(3000);
        driver.quit();
    }
}
