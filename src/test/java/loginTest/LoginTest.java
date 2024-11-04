package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.CommonActionsWithElements;

import java.io.IOException;
import java.util.Map;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTest extends BaseTest {

    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Test
  //  @Category(SmokeTestFilter.class)
    public void validLogin(){
        loginPage.openLoginPage()
                .enterUsername(TestData.VALID_LOGIN)
                .enterPassword(TestData.VALID_PASSWORD)
                .clickButtonLogin()
             .checkIsRedirectToDashboardPage();
    }


    @Ignore
    @Test
    @Category(SmokeTestFilter.class)
    public void invalidLoginWithExel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(CommonActionsWithElements.configProperties.DATA_FILE(),"validLogOn");
        System.out.println("Зчитані дані з Excel: " + dataForValidLogin);

        loginPage.openLoginPage()
                .enterUsername(dataForValidLogin.get("login"))
                .enterPassword(dataForValidLogin.get("pass"))
                .clickButtonLogin();
        loginPage.checkErrorMessage(dataForValidLogin.get("error"));
    }
}
