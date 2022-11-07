package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.contexts.Contexts;
import src.contexts.WaitMoreThanOneContext;
import src.driver.DriverFactory;
import src.driver.Platform;

import java.util.List;

public class HybridContext {
    public static void main(String[] args) {

            AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try{

            // Navigate to webview
            appiumDriver.findElement(MobileBy.AccessibilityId("Webview")).click();

            WebDriverWait wait = new WebDriverWait(appiumDriver,10L);
            wait.until(new WaitMoreThanOneContext(appiumDriver));

            // Print all available contexts
            for (String contextHandle : appiumDriver.getContextHandles()) {
                System.out.println("Context:" + contextHandle);

            }
            
            // switch webview context
            appiumDriver.context(Contexts.WEB_VIEW);
            WebElement navToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtnElem.click();
            List<MobileElement> menuItemElem =appiumDriver.findElementsByCssSelector(".menu__list li a");
            if(menuItemElem.isEmpty())
                throw new RuntimeException("[ERR] There no lis items in list");
            for (MobileElement menuItemEle: menuItemElem)
                 {
                     String itemText= menuItemEle.getText();
                     String itemhref = menuItemEle.getAttribute("href");
                if (itemText.isEmpty()){
                    System.out.println("Github: " + itemhref);
                }
                else{
                    System.out.println(itemText.contains(":  "+ itemhref));
                }
            }
                


            // switch back to Native context
            appiumDriver.context(Contexts.NATIVE);
            appiumDriver.findElement(MobileBy.AccessibilityId("Home")).click();

            //debug purpose only
            Thread.sleep(3000);

        }catch (Exception e) {
            e.printStackTrace();
        }
        appiumDriver.quit();
    }

}
