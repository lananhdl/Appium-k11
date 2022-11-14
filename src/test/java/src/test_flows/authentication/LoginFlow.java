package src.test_flows.authentication;

import src.test_flows.BaseFlow;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.validator.routines.EmailValidator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.models.components.login.LoginFormComponentMode03;
import src.models.pages.LoginScreenMode03;

public class LoginFlow extends BaseFlow {
    private String email;
    private String password;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, String email, String password) {
        super(appiumDriver);
        this.email = email;
        this.password = password;
    }

    public void login(){
        LoginScreenMode03 loginScreen = new LoginScreenMode03(appiumDriver);
        LoginFormComponentMode03 loginFormComponentMode03 = loginScreen.loginFormComponent();

        if(!email.isEmpty()){
            loginFormComponentMode03.inputEmail(email);
        }
        if (!password.isEmpty()) {

            loginFormComponentMode03.inputPassword(password);
        }
        loginFormComponentMode03.clickOnLoginBtn();
    }

    public void verifyLogin(){
        boolean isEmailValid = isEmailValid();
        boolean isPasswordValid = isPasswordValid();

        LoginFormComponentMode03 loginFormComponent = new LoginScreenMode03(appiumDriver).loginFormComponent();

        if(isEmailValid && isPasswordValid){
            verifyCorrectLoginCredential(loginFormComponent);
        }

        if(!isEmailValid){
            verifyIncorrectEmailLogin(loginFormComponent);
        }

        if(!isPasswordValid){
            verifyIncorrectPasswordLogin(loginFormComponent);
        }
    }

    private boolean isEmailValid() {
        return EmailValidator.getInstance().isValid(email);
    }

    private boolean isPasswordValid() {
        return password.length() >= 8;
    }

    private void verifyCorrectLoginCredential(LoginFormComponentMode03 loginForm) {

        String actualSuccessAlertTitle = loginForm.getAlertTitle();
        String expectedSuccessAlertTitle = "Success";
        System.out.println("Actual: " + actualSuccessAlertTitle);
        System.out.println("Expected: " + expectedSuccessAlertTitle);

        String actualSuccessAlertMessage = loginForm.getAlertMessage();
        String expectedSuccessAlertMessage = "You are logged in!";
        System.out.println("Actual: " + actualSuccessAlertMessage);
        System.out.println("Expected: " + expectedSuccessAlertMessage);
    }

    private void verifyIncorrectEmailLogin(LoginFormComponentMode03 loginForm) {
        String actualInvalidEmailTxt = loginForm.invalidEmailTxt();
        String expectedInvalidEmailTxt = "Please enter a valid email address";
        System.out.println("Actual: " + actualInvalidEmailTxt);
        System.out.println("Expected: " + expectedInvalidEmailTxt);
    }

    private void verifyIncorrectPasswordLogin(LoginFormComponentMode03 loginForm) {
        String actualInvalidPasswordTxt = loginForm.invalidPasswordTxt();
        String expectedInvalidPasswordTxt = "Please enter at least 8 characters";
        System.out.println("Actual: " + actualInvalidPasswordTxt);
        System.out.println("Expected: " + expectedInvalidPasswordTxt);
    }

}
