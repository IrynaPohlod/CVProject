package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ViewSystemUsersPage extends ParentPage{

    SaveSystemUserPage saveSystemUserPage = new SaveSystemUserPage(webDriver);
    
    public ViewSystemUsersPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/admin/viewSystemUsers";
    }

    @FindBy(xpath = "//h5[text() ='System Users']")
    private WebElement titleSystemUsers;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")
    private WebElement inputUsername;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]")
    private WebElement dropdownUserRole;

    @FindBy(xpath = "//input[@placeholder=\"Type for hints...\"]")
    private WebElement inputEmployeeName;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[1]")
    private WebElement dropdownStatus;
    
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")
    private WebElement buttonSearch;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button")
    private WebElement buttonAdd;

    @FindBy(xpath = "//*[@id=\"oxd-toaster_1\"]/div")
    private WebElement toastMessage;

    @FindBy(xpath = "//i[@class=\"oxd-icon bi-trash\"]")
    private WebElement buttonTrash;

    @FindBy(xpath = "//div[@class=\"oxd-sheet oxd-sheet--rounded oxd-sheet--white oxd-dialog-sheet oxd-dialog-sheet--shadow oxd-dialog-sheet--gutters orangehrm-dialog-popup\"]")
    private WebElement popupDialog;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin\"]")
    private WebElement buttonYesDelete;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span")
    private WebElement titleNoRecordsFound;

    @Step
    public ViewSystemUsersPage checkRedirectToViewSystemUserPage(){
        Assert.assertTrue("View System User page is not opened",isElementDisplayed(titleSystemUsers));
        checkUrl();
        return this;
    }

    @Step
    public ViewSystemUsersPage enterTextInInputUserName(String userName){
        enterTextIntoElement(inputUsername, userName);
        return this;
    }

    @Step
    public ViewSystemUsersPage selectUserRoleFromDropdown(String userRole) {
            selectTextInDivDropDown(dropdownUserRole, userRole);
        return this;
    }

    @Step
    public ViewSystemUsersPage enterTextInInputEmployeeName(String employeeName){
        enterTextIntoElementWithOptions(inputEmployeeName, employeeName);
        return this;
    }

    @Step
    public ViewSystemUsersPage selectStatusFromDropdown(String status) {
        selectTextInDivDropDown(dropdownStatus, status);
        return this;
    }

    @Step
    public ViewSystemUsersPage clickOnButtonSearch() {
        clickOnElement(buttonSearch);
    return this;
    }

    @Step
    public SaveSystemUserPage clickOnButtonAdd() {
        clickOnElement(buttonAdd);
        return new SaveSystemUserPage(webDriver);
    }

    public ViewSystemUsersPage createUser(String userName){
        clickOnButtonAdd();
        saveSystemUserPage
                .selectOptionFromUserRoleDropdown("Admin")
                .enterTextIntoEmployeeName("Jobin Mathew Sam")
                .selectOptionFromStatusDropdown("Disabled")
                .enterTextIntoUserName(userName)
                .enterTestIntoPassword("StrongPass2024!")
                .enterTestIntoConfirmPassword("StrongPass2024!")
                .clickOnButtonSave()
                .checkSuccessToastMessage("Successfully Saved")
                .checkRedirectToViewSystemUserPage()
                .checkUserWasCreated(userName);
        return this;
    }




    @Step
    public ViewSystemUsersPage checkUserWasCreated(String userName) {
        List<WebElement> userNames = webDriver.findElements(By.xpath(String.format("//*[text()='%s']", userName)));
        searchForCreatedUser(userName);
        Assert.assertEquals("Number of user names " + userNames, 1, userNames.size());
    return this;
    }


    public ViewSystemUsersPage deleteUserWithName(String userName) {
            searchForCreatedUser(userName);
            clickOnElement(buttonTrash);
            webDriverWaitLow.until(ExpectedConditions.visibilityOf(popupDialog));
            clickOnElement(buttonYesDelete);
            logger.info("User was deleted " + userName);
    return this;
    }

    @Step
    public void searchForCreatedUser(String userName){
        enterTextIntoElement(inputUsername, userName);
        clickOnElement(buttonSearch);
    }

    public void checkFoundedRecords(){
        String actualTitle = titleNoRecordsFound.getText();
        Assert.assertEquals("User still exists", "No Records Found",actualTitle);
    }

    public ViewSystemUsersPage checkSuccessToastMessage(String messageText) {
        checkToastMessage(messageText, toastMessage);
        return this;
    }

}
