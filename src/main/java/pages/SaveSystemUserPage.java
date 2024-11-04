package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SaveSystemUserPage extends ParentPage {

    @FindBy(xpath = "//h6[text()='Add User']")
    private WebElement titleAddUser;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]")
    private WebElement dropdownUserRole;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input")
    private WebElement inputEmployeeName;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]")
    private WebElement dropdownStatus;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input")
    private WebElement inputUsername;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input")
    private WebElement inputPassword;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")
    private WebElement inputConfirmPassword;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement buttonSave;


    public SaveSystemUserPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/admin/saveSystemUser";
    }

    @Step
    public SaveSystemUserPage checkRedirectToSaveSystemUserPage(){
        checkUrl();
        Assert.assertTrue("Save System User page is not opened",isElementDisplayed(titleAddUser));
        return this;
    }

    @Step
    public SaveSystemUserPage selectOptionFromUserRoleDropdown(String role) {
        selectTextInDivDropDown(dropdownUserRole,role);
        return this;
    }

    @Step
    public SaveSystemUserPage enterTextIntoEmployeeName(String employeeName) {
       enterTextIntoElementWithOptions(inputEmployeeName,employeeName);
        return this;
    }

    @Step
    public SaveSystemUserPage selectOptionFromStatusDropdown(String status) {
        selectTextInDivDropDown(dropdownStatus,status);
        return this;
    }

    @Step
    public SaveSystemUserPage enterTextIntoUserName(String userName) {
        enterTextIntoElement(inputUsername,userName);
        return this;
    }

    @Step
    public SaveSystemUserPage enterTestIntoPassword(String password) {
        enterTextIntoElement(inputPassword,password);
        return this;
    }

    @Step
    public SaveSystemUserPage enterTestIntoConfirmPassword(String confirmPassword) {
        enterTextIntoElement(inputConfirmPassword,confirmPassword);
        return this;
    }

    @Step
    public ViewSystemUsersPage clickOnButtonSave(){
        clickOnElement(buttonSave);
        return new ViewSystemUsersPage(webDriver);
    }

}
