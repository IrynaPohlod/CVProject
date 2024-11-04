package pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/auth/login";
    }

    @FindBy(xpath = "//input[@name=\"username\"]")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@name=\"password\"]")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement buttonLogin;

    @FindBy(xpath = "//p[@class=\"oxd-text oxd-text--p oxd-alert-content-text\"]")
    private WebElement actualErrorMessage;

    @Step
    public LoginPage openLoginPage() {
        try{
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
            logger.info(baseUrl);
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
        return this;
    }

    @Step
    public DashboardPage loginWithValidCredentials() {
                 openLoginPage()
                         .enterUsername(TestData.VALID_LOGIN)
                         .enterPassword(TestData.VALID_PASSWORD)
                         .clickButtonLogin();
        return new DashboardPage(webDriver);
    }

    @Step
    public LoginPage enterUsername(String userName) {
        enterTextIntoElement(inputUserName, userName);
        return this;
    }

    @Step
    public LoginPage enterPassword(String password) {
        enterTextIntoElement(inputPassword, password);
        return this;
    }

    @Step
    public DashboardPage clickButtonLogin() {
        clickOnElement(buttonLogin);
        return new DashboardPage(webDriver);
    }

    @Step
    public void checkErrorMessage(String errorMessage) {
        Assert.assertEquals("Wrong error message", errorMessage, actualErrorMessage.getText());
    }
}
