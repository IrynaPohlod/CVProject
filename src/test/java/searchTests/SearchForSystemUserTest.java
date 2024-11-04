package searchTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class SearchForSystemUserTest extends BaseTest {

    private String USER_NAME = "User" + Util.getDateAndTimeFormatted();

    @Test
    public void searchForSystemUser() {
        dashboardPage.openDashboardPage()
                .getSideBarElements().clickOnMenuItemAdmin()
                .createUser(USER_NAME)
                .searchForCreatedUser(USER_NAME)
        ;
    }

}
