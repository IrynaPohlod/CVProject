package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.SideBarElements;

public class DashboardPage extends ParentPage{

    public SideBarElements getSideBarElements() {
        return sideBarElements;
    }

    private SideBarElements sideBarElements = new SideBarElements(webDriver);

    @FindBy(xpath = "//img[@alt=\"profile picture\"]")
    private WebElement profilePicture;

    public DashboardPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/dashboard/index";
    }

    @Step
    public DashboardPage openDashboardPage(){
        if(!isProfileIconDisplayed()){
            LoginPage loginPage = new LoginPage(webDriver);
            loginPage.loginWithValidCredentials()
                     .checkIsRedirectToDashboardPage();
        }
        return this;
    }

    @Step
    public DashboardPage checkIsRedirectToDashboardPage(){
        checkUrl();
        Assert.assertTrue("Dashboard page is not opened", isProfileIconDisplayed());
        return this;
    }

    @Step
    public boolean isProfileIconDisplayed(){
       try{
           return profilePicture.isDisplayed();
       } catch (Exception e) {
           return false;
       }
    }


}
