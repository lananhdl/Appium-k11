package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.driver.DriverFactory;
import src.driver.Platform;

import java.time.Duration;
import java.util.List;

public class Homework1 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try{
            //Navigate to login
            MobileElement navFormBtnElement = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormBtnElement.click();

            // wait untik we are  on the new screen after navigating
            WebDriverWait wait = new WebDriverWait(appiumDriver,5L);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Form Components\")")));

            //selector fields
            MobileElement inputField =appiumDriver.findElement(MobileBy.xpath("//android.widget.EditText[@content-desc=\"text-input\"]"));
            inputField.sendKeys("InputTest");
           /* MobileElement youHaveTypedField =appiumDriver.findElement(MobileBy.xpath("//android.widget.TextView[@content-desc=\"input-text-result\"]"));
            youHaveTypedField.clear();
            youHaveTypedField.sendKeys("Type Test");*/


            MobileElement switchField =appiumDriver.findElement(MobileBy.AccessibilityId("switch"));
            switchField.click();
            MobileElement dropDownField =appiumDriver.findElement(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Dropdown\"]/android.view.ViewGroup/android.widget.EditText"));
            dropDownField.click();
            List<MobileElement> listTestDropdown = appiumDriver.findElements(MobileBy.id("android:id/text1"));
            if (listTestDropdown.isEmpty()){
                throw new RuntimeException("ERR: There no element");
            }
            else {
                /*for (MobileElement dropdownText: listTestDropdown
                     ) {
                    MobileElement selectAnItemText = dropdownText.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"webdriver.io is awesome\")"));
                    selectAnItemText.click();
                }*/
                final  int WEBDRICERIO_INDEX =1;
                listTestDropdown.get(WEBDRICERIO_INDEX).click();
            }

            // Get Mobile Screen
            Dimension windownSize= appiumDriver.manage().window().getSize();
            int screenHeight= windownSize.getHeight();
            int screenWidth =windownSize.getWidth();

            //caculator touch points
            int xStartPoin = 50* screenWidth/100;
            int xEndPoint = 50*screenWidth/100;
            int yStartPoint = 50* screenHeight/100;
            int yEndPoint = 10*screenHeight/100;
// Convert poin to coordinate

            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoin,yStartPoint);
            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint,yEndPoint);

            //TouchAction

            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();
            // Find Active btn and click
            appiumDriver.findElement(MobileBy.AccessibilityId("button-Active")).click();
            // Debug purpose Only
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
appiumDriver.quit();
    }
    }

