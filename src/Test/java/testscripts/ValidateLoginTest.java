/**  
* @author Palash Soni
* https://www.linkedin.com/in/Palash9088
* https://github.com/Palash9088
* 
*/
package testscripts;//import java.util.*;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.ArrayList;

public class ValidateLoginTest extends TestBase {
    private static Logger log = Logger.getLogger(ValidateLoginTest.class);

    //Validate that the user is able to navigate to the login page.
    @Test
    public void validateUserIsAbleToAccessUrlAndLandsOnTheLoginPage() {
        LoginPage loginPage = LoginPage.getLoginPage();
        Assert.assertEquals(loginPage.getUrl(),
                "https://sakshingp.github.io/assignment/login.html",
                "Url doesn't match with the given url");
        Assert.assertEquals(loginPage.getLoginPageText(), "Login Form",
                "User is not on Login page");
    }

    //Validate loginPage logo is visible
    @Test
    public void validateLoginPageLogoVisibility() {
        LoginPage loginPage = LoginPage.getLoginPage();
        Assert.assertTrue(loginPage.isLogoPresent(), "Logo not visible");
    }

    //Validate socialmedia login icons are visible
    @Test
    public void validateSocialMediaLoginIcons() {
        LoginPage loginPage = LoginPage.getLoginPage();
        log.info(loginPage.isSocialMediaLoginIcons());
    }

    //Validate username & Password icons are visible
    @Test
    public void validateUsernameAndPasswordIcon() {
        LoginPage loginPage = LoginPage.getLoginPage();
        Assert.assertTrue(loginPage.isUsernameIconPresent());
        Assert.assertTrue(loginPage.isPasswordIconPresent());

    }

    //Validate username & Password field is enabled
    @Test
    public void validateUsernameAndPasswordFieldEnabled() {
        LoginPage loginPage = LoginPage.getLoginPage();
        Assert.assertTrue(loginPage.isUsernameFieldEnabled(),
                "Username field is disabled");
        Assert.assertTrue(loginPage.isPasswordFieldEnabled(),
                "Password field is disabled");

    }

    // Validate Placeholder is visible
    @Test
    public void validatePlaceholderIsVisible() {
        LoginPage loginPage = LoginPage.getLoginPage();
        Assert.assertEquals(loginPage.getUsernamePlaceholder(), "Enter your username",
                "Username placeholder doesn't match");
        Assert.assertEquals(loginPage.getPasswordPlaceholder(), "Enter your password",
                "Password placeholder doesn't match");
    }

    //Validate login without username and password
    @Test
    public void validateLoginWithoutUsernameAndPassword() {
        LoginPage loginPage = LoginPage.getLoginPage();
        loginPage.enterCredentials("", "");
        loginPage.clickOnLoginBtn();
        Assert.assertEquals(loginPage.getErrorMsg(), "Both Username and Password must be present",
                "Wrong error message is showing");
    }

    //Validate login without password
    @Test
    public void validateLoginWithoutPassword() {
        LoginPage loginPage = LoginPage.getLoginPage();
        ArrayList<String> loginPageCred = loginPage.getCred();
        loginPage.enterCredentials(loginPageCred.get(0), "");
        loginPage.clickOnLoginBtn();
        Assert.assertEquals(loginPage.getErrorMsg(), "Password must be present",
                "Wrong error message is showing");
    }

    //Validate login without username
    @Test
    public void validateLoginWithoutUsername() {
        LoginPage loginPage = LoginPage.getLoginPage();
        ArrayList<String> loginPageCred = loginPage.getCred();
        loginPage.enterCredentials("", loginPageCred.get(1));
        loginPage.clickOnLoginBtn();
        Assert.assertEquals(loginPage.getErrorMsg(), "Username must be present",
                "Wrong error message is showing");
    }

    //Validate login with Remember Me Functionality
    @Test
    public void validateRememberMeFunctionality() {
        LoginPage loginPage = LoginPage.getLoginPage();
        ArrayList<String> loginPageCred = loginPage.getCred();
        loginPage.enterCredentials(loginPageCred.get(0), loginPageCred.get(1));
        Assert.assertTrue(loginPage.clickOnRememberMe(),
                "Remember me checkbox is not selected");
        loginPage.clickOnLoginBtn();
        loginPage.goBackInHistory();
        Assert.assertEquals(loginPage.getUsername(), loginPageCred.get(0),
                "Remember me function is not working correctly");
        loginPage.clickOnLoginBtn();
    }

    //Validate login with username & password
    @Test
    public void validateLoginWithWhiteSpaces() {
        LoginPage loginPage = LoginPage.getLoginPage();
        loginPage.enterCredentials("    ", "    ");
        loginPage.clickOnLoginBtn();
        Assert.assertNotEquals(loginPage.getUrl(), "https://sakshingp.github.io/assignment/login.html",
                "User has logged in homepage");
    }

    //Validate login with username & password
    @Test
    public void validateLogin() {
        LoginPage loginPage = LoginPage.getLoginPage();
        ArrayList<String> loginPageCred = loginPage.getCred();
        loginPage.enterCredentials(loginPageCred.get(0), loginPageCred.get(1));
        loginPage.clickOnLoginBtn();
        Assert.assertEquals(loginPage.getUrl(), "https://sakshingp.github.io/assignment/home.html",
                "User is not landed on correct page");
    }

    //Validate Tab & Enter buttons are working in login form
    @Test
    public void validateTabAndEnterBtnWorking() {
        LoginPage loginPage = LoginPage.getLoginPage();
        loginPage.clickTabOnKeyboard();
        loginPage.clickTabOnKeyboard();
        ArrayList<String> loginPageCred = loginPage.getCred();
        loginPage.enterUsername(loginPageCred.get(0));
        loginPage.clickTabOnKeyboard();
        loginPage.enterPassword(loginPageCred.get(1));
        loginPage.clickTabOnKeyboard();
        loginPage.clickEnterKeyboard();
        Assert.assertEquals(loginPage.getUrl(), "https://sakshingp.github.io/assignment/home.html",
                "User is not landed on correct page");
    }

    //Validate amount values given are sorted.
    @Test
    public void validateAmountListSorted() {
        validateLogin();
        HomePage homePage = HomePage.getHomePage();
        log.info("Amount List Before click on Table head -> " + homePage.getAmountList());
        homePage.clickOnAmountHead();
        log.info("Amount List After click on Table head -> " + homePage.getAmountList());
        Assert.assertTrue(homePage.isAmountListSorted(), "List is not sorted");
    }
}
