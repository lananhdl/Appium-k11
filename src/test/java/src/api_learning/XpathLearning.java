package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import src.driver.DriverFactory;
import src.driver.Platform;

import java.util.List;

public class XpathLearning {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try{
            //Navigate to login
            MobileElement navLoginBtnElement = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginBtnElement.click();

            // Find all matching elems | Sharing about the trick with findElements
            List<MobileElement> credFileldElem= appiumDriver.findElements(MobileBy.xpath("//android.widget.EditText"));
final  int USERNAME_INDEX =0;
final  int PASSWORD_INDEX =1;
credFileldElem.get(USERNAME_INDEX).sendKeys("lananh@gmail.com");
credFileldElem.get(PASSWORD_INDEX).sendKeys("12345678");

// Neu nhu tim element k thay thi co 2 cach 1 la dung try - cactch, cach thu 2 la tra ra 1 list cac element rong
// Find login info text by UiSelector

MobileElement loginInstructionElem =
        appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"When the device\")"));
            System.out.println(loginInstructionElem.getText());
        }catch (Exception e){
            e.printStackTrace();
        }
appiumDriver.quit();
    }

}
