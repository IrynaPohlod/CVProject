package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.DashboardPage;
import pages.ViewSystemUsersPage;
import pages.CommonActionsWithElements;


public class SideBarElements extends CommonActionsWithElements {

    public SideBarElements(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//span[text() ='Admin']")
    private WebElement menuItemAdmin;

    @FindBy(xpath = "//span[text() ='Dashboard']")
    private WebElement menuItemDashboard;

    public ViewSystemUsersPage clickOnMenuItemAdmin() {
        clickOnElement(menuItemAdmin);
        return new ViewSystemUsersPage(webDriver);
    }

    public DashboardPage clickOnMenuItemDashboard() {
        clickOnElement(menuItemDashboard);
        return new DashboardPage(webDriver);
    }



}
