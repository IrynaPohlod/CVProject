package addUserTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.Util;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AddNewUserTest extends BaseTest {
    private String USER_NAME = "User" + Util.getDateAndTimeFormatted();

    @Category(SmokeTestFilter.class)
    @Test
    public void addNewUser() {
        dashboardPage.openDashboardPage()
                .getSideBarElements().clickOnMenuItemAdmin()
                .clickOnButtonAdd()
            .checkRedirectToSaveSystemUserPage()
                .selectOptionFromUserRoleDropdown("Admin")
                .enterTextIntoEmployeeName("Jobin Mathew Sam")
                .selectOptionFromStatusDropdown("Disabled")
                .enterTextIntoUserName(USER_NAME)
                .enterTestIntoPassword("StrongPass2024!")
                .enterTestIntoConfirmPassword("StrongPass2024!")
                .clickOnButtonSave()
            .checkSuccessToastMessage("Successfully Saved")
            .checkRedirectToViewSystemUserPage()
            .checkUserWasCreated(USER_NAME)

        ;

    }

   /* @After
    public void deleteUser(){
        dashboardPage
                .openDashboardPage()
                .getSideBarElements().clickOnMenuItemAdmin()
             .checkRedirectToViewSystemUserPage()
                .deleteUserWithNameTillPresent(USER_NAME);
    }*/

}
